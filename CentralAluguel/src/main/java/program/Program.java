package program;

import java.util.Scanner;
import central.CentralDeInformacoes;
import central.Persistencia;
import enums.Sexo;
import model.Pessoa;
import model.PropostaDeAluguel;
import service.GeradorDeContratos;
import service.Mensageiro;

public class Program {
    private static CentralDeInformacoes central;
    private static Persistencia persistencia;
    private static final String ARQUIVO_CENTRAL = "central.xml";
    
    public static void main(String[] args) {
        persistencia = new Persistencia();
        central = persistencia.recuperarCentral(ARQUIVO_CENTRAL); 
        
        // Garante que a lista de propostas não comece nula caso o XStream não a recupere
        if (central.getTodasAsPropostas() == null) {
            central.setTodasAsPropostas(new java.util.ArrayList<>());
        }
        
        Scanner scanner = new Scanner(System.in);
        String opcao = "";
        
        while (!opcao.equalsIgnoreCase("S")) {
            System.out.println("1 - Nova pessoa");
            System.out.println("2 - Listar todas as pessoas");
            System.out.println("3 - Exibir informações de uma pessoa específica");
            System.out.println("4 - Nova proposta");
            System.out.println("5 - Informar quantidade de propostas cadastradas");
            System.out.println("6 - Detalhar uma proposta");
            System.out.println("7 - Gerar Contrato e Enviar E-mail de uma Proposta");
            System.out.println("S - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextLine().trim();
            
            switch (opcao.toUpperCase()) {
                case "1":
                    System.out.println("\nCadastro de Pessoa");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    
                    System.out.print("Sexo (M/F): ");
                    String sexoStr = scanner.nextLine().toUpperCase();
                    try {
                        Sexo sexo = Sexo.valueOf(sexoStr);
                        Pessoa pessoa = new Pessoa(nome, sexo, cpf, email);
                        
                        if (central.adicionarPessoa(pessoa)) {
                            System.out.println("Pessoa adicionada com sucesso!");
                            persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                        } else {
                            System.out.println("CPF já cadastrado.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Sexo inválido! Use M ou F.");
                    }
                    break;
                    
                case "2":
                    System.out.println("\n--- Pessoas Cadastradas ---");
                    if (central.getTodasAsPessoas().isEmpty()) {
                        System.out.println("Nenhuma pessoa cadastrada.");
                    } else {
                        for (Pessoa p : central.getTodasAsPessoas()) {
                            System.out.println(p);
                        }
                    }
                    break;
                    
                case "3":
                    System.out.print("\nDigite o CPF da pessoa: ");
                    String cpfBusca = scanner.nextLine();
                    Pessoa pessoaEncontrada = central.recuperarPessoaPorCPF(cpfBusca);
                    
                    if (pessoaEncontrada != null) {
                        System.out.println("\nInformações da Pessoa");
                        System.out.println("Nome: " + pessoaEncontrada.getNome());
                        System.out.println("Email: " + pessoaEncontrada.getEmail());
                        System.out.println("Sexo: " + pessoaEncontrada.getSexo());
                        System.out.println("CPF: " + pessoaEncontrada.getCPF());
                    } else {
                        System.out.println("Pessoa com o CPF informado não foi encontrada.");
                    }
                    break;
                    
                case "4":
                    System.out.println("\nNova Proposta de Aluguel");
                    
                    System.out.print("Digite o CPF do Locador: ");
                    String cpfLocador = scanner.nextLine();
                    Pessoa locador = central.recuperarPessoaPorCPF(cpfLocador);
                    
                    if (locador == null) {
                        System.out.println("Não é possível criar proposta. Locador não cadastrado!");
                        break;
                    }
                    
                    System.out.print("Digite o CPF do Locatário: ");
                    String cpfLocatario = scanner.nextLine();
                    Pessoa locatario = central.recuperarPessoaPorCPF(cpfLocatario);
                    
                    if (locatario == null) {
                        System.out.println("Não é possível criar proposta. Locatário não cadastrado!");
                        break;
                    }
                    
                    System.out.print("Tipo de Espaço: ");
                    String tipoEspaco = scanner.nextLine();
                    
                    System.out.print("Valor total do Aluguel (ex: 250.00): ");
                    double valor = Double.parseDouble(scanner.nextLine());
                    
                    System.out.print("Data do Evento (ex: 12/06/2026): ");
                    String dataEvento = scanner.nextLine();
                    
                    System.out.print("Descrição do Evento: ");
                    String descricaoEvento = scanner.nextLine();
                    
                    try {
                        PropostaDeAluguel proposta = new PropostaDeAluguel(
                            locador, locatario, tipoEspaco, valor, dataEvento, descricaoEvento
                        );
                        
                        if (central.adicionarProposta(proposta)) {
                            System.out.println("Proposta de Aluguel criada com sucesso!");
                            persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                        } else {
                            System.out.println("Erro ao adicionar proposta.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao processar os dados inseridos.");
                    }
                    break;
                    
                case "5":
                    int totalPropostas = central.getTodasAsPropostas().size();
                    System.out.println("\nTotal de propostas cadastradas no sistema: " + totalPropostas);
                    break;
                    
                case "6":
                    System.out.print("\nDigite o número/ID da proposta para detalhar: ");
                    int idBusca = Integer.parseInt(scanner.nextLine());
                    PropostaDeAluguel propDetalhada = central.recuperarPropostaPorId(idBusca);
                    
                    if (propDetalhada != null) {
                        System.out.println("\nDetalhes da Proposta");
                        System.out.println(propDetalhada); 
                    } else {
                        System.out.println("Proposta com o ID informado não existe.");
                    }
                    break;
                    
                case "7":
                    System.out.print("\nDigite o número/ID da proposta para gerar contrato: ");
                    int idAtivar = Integer.parseInt(scanner.nextLine());
                    PropostaDeAluguel propParaAtivar = central.recuperarPropostaPorId(idAtivar);
                    
                    if (propParaAtivar != null) {
                        String nomeDoContratoPDF = "contrato.pdf";
                        System.out.println("Gerando arquivo '" + nomeDoContratoPDF + "'...");
                        
                        GeradorDeContratos gerador = new GeradorDeContratos();
                        boolean pdfGerado = gerador.gerarContrato(propParaAtivar, nomeDoContratoPDF);
                        
                        if (pdfGerado) {
                            String emailDestinatario = propParaAtivar.getLocatario().getEmail();
                            System.out.println("Enviando contrato para: " + emailDestinatario + "...");
                            
                            Mensageiro mensageiro = new Mensageiro();
                            boolean emailEnviado = mensageiro.enviarContratoEmail(
                                emailDestinatario, 
                                "Seu Contrato de Aluguel - Espaço: " + propParaAtivar.getTipoEspaco(), 
                                "Olá, " + propParaAtivar.getLocatario().getNome() + "! Segue em anexo o PDF.", 
                                nomeDoContratoPDF
                            );
                            
                            if (emailEnviado) {
                                System.out.println("Processo concluído com sucesso!");
                            } else {
                                System.out.println("PDF gerado, mas houve uma falha de autenticação/envio no e-mail.");
                            }
                        } else {
                            System.out.println("Falha crítica ao gerar o documento PDF.");
                        }
                    } else {
                        System.out.println("Proposta com o ID informado não foi encontrada.");
                    }
                    break;
                    
                case "S":
                    System.out.println("Saindo do sistema... Até logo!");
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
        
        scanner.close();
    }
}
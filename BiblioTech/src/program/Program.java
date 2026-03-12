package program;
import java.util.Scanner;

import entities.Livro;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Livro[] livros = new Livro[10];
        int opcao = 0; 
     
        while (opcao != 6) {
            System.out.println("\n1 - Cadastrar Livro\n2 - Remover Livro\n3 - Buscar Livro\n4 - Atualizar Livro\n5 - Listar Todos os Livros\n6 - Sair\nEscolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    Livro livro = new Livro();
                    System.out.println("Digite o nome do seu livro: ");
                    livro.setNome(sc.nextLine());
                    System.out.println("Digite a data de publicação do seu livro: ");
                    livro.setDataPublicacao(sc.nextLine());
                    System.out.println("Digite a descrição do seu livro: ");
                    livro.setDescricao(sc.nextLine());
                    
                    for (int i = 0; i < livros.length; i++) {
                    	if (livros[i] == null ) {
                    		livros[i] = livro;
                    		break;
                    	}
                    }
                    break;
                case 2:
                	System.out.println("Digite o nome do livro que quer remover: ");
                    String nomeRemover = sc.nextLine();
                    
                    for (int i = 0; i < livros.length; i++) {
                    	if (livros[i].getNome().equals(nomeRemover) ) {
                    		livros[i] = null;
                    		break;
                    	}
                    }
                    break;
                case 3:
                	System.out.println("Digite o nome do livro que quer pesquisar: ");
                    String livroPesquisado = sc.nextLine();
                    
                    for (int i = 0; i < livros.length; i++) {
                    	if (livros[i].getNome().equals(livroPesquisado) ) {
                    		System.out.println("O livro que você pesquisou está na posição: " + i);
                    		break;
                    	}
                    }
                    break;
                case 4:
                  	System.out.println("Digite o nome do livro que quer atualizar: ");
                    String atualizarLivro = sc.nextLine();
                    
                    for (int i = 0; i < livros.length; i++) {
                    	if (livros[i].getNome().equals(atualizarLivro) ) {
	                	    System.out.println("Digite o nome do seu livro: ");
	                        livros[i].setNome(sc.nextLine());
	                        System.out.println("Digite a data de publicação do seu livro: ");
	                        livros[i].setDataPublicacao(sc.nextLine());
	                        System.out.println("Digite a descrição do seu livro: ");
	                        livros[i].setDescricao(sc.nextLine());
                    		break;
                    	}
                    }
                    break;
                case 5:
                    System.out.println("Listar Todos os Livros");
                    for (int i = 0; i < livros.length; i++) {
                    	if (livros[i] != null ) {
                    		System.out.println(livros[i].exibirInformações());	
                    	}
                    }     
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
        sc.close();
    }
}
package service;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import model.PropostaDeAluguel;

public class GeradorDeContratos {
    
    /**
     * Gera um contrato em PDF baseado na proposta de aluguel
     * @param proposta Proposta de aluguel
     * @param nomeArquivo Nome do arquivo PDF a ser criado
     * @return true se gerado com sucesso, false caso contrário
     */
    public boolean gerarContrato(PropostaDeAluguel proposta, String nomeArquivo) {
        try {
            // Criar documento
            Document document = new Document(PageSize.A4);
            
            // Salvar em arquivo
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            
            // Abrir documento para escrita
            document.open();
            
            // Adicionar cabeçalho
            adicionarCabecalho(document);
            
            // Adicionar informações da proposta
            adicionarInformacoesProposta(document, proposta);
            
            // Adicionar condições
            adicionarCondicoes(document);
            
            // Adicionar rodapé
            adicionarRodape(document);
            
            // Fechar documento
            document.close();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao gerar contrato: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    private void adicionarCabecalho(Document document) throws Exception {
        Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font fonteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 12);
        
        Paragraph titulo = new Paragraph("CONTRATO DE ALUGUEL DE ESPAÇO", fonteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        
        Paragraph subtitulo = new Paragraph("Para Apresentações Artísticas", fonteSubtitulo);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitulo);
        
        Paragraph dataDoc = new Paragraph(
            "Gerado em: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
            FontFactory.getFont(FontFactory.HELVETICA, 10)
        );
        dataDoc.setAlignment(Element.ALIGN_CENTER);
        document.add(dataDoc);
        
        document.add(new Paragraph("\n"));
    }
    
    private void adicionarInformacoesProposta(Document document, PropostaDeAluguel proposta) throws Exception {
        Font fonteSecao = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font fonteNormal = FontFactory.getFont(FontFactory.HELVETICA, 11);
        
        // Seção: Locador
        Paragraph secaoLocador = new Paragraph("1. LOCADOR (PROPRIETÁRIO DO ESPAÇO)", fonteSecao);
        document.add(secaoLocador);
        
        document.add(new Paragraph(
            "Nome: " + proposta.getLocador().getNome(),
            fonteNormal
        ));
        document.add(new Paragraph(
            "CPF: " + proposta.getLocador().getCPF(),
            fonteNormal
        ));
        document.add(new Paragraph(
            "Email: " + proposta.getLocador().getEmail(),
            fonteNormal
        ));
        document.add(new Paragraph(
            "Sexo: " + proposta.getLocador().getSexo(),
            fonteNormal
        ));
        
        document.add(new Paragraph("\n"));
        
        // Seção: Locatário
        Paragraph secaoLocatario = new Paragraph("2. LOCATÁRIO (CONTRATANTE DO ESPAÇO)", fonteSecao);
        document.add(secaoLocatario);
        
        document.add(new Paragraph(
            "Nome: " + proposta.getLocatario().getNome(),
            fonteNormal
        ));
        document.add(new Paragraph(
            "CPF: " + proposta.getLocatario().getCPF(),
            fonteNormal
        ));
        document.add(new Paragraph(
            "Email: " + proposta.getLocatario().getEmail(),
            fonteNormal
        ));
        document.add(new Paragraph(
            "Sexo: " + proposta.getLocatario().getSexo(),
            fonteNormal
        ));
        
        document.add(new Paragraph("\n"));
        
        // Seção: Detalhes do Aluguel
        Paragraph secaoDetalhes = new Paragraph("3. DETALHES DO ALUGUEL", fonteSecao);
        document.add(secaoDetalhes);
        
        document.add(new Paragraph(
            "Tipo de Espaço: " + proposta.getTipoEspaco(),
            fonteNormal
        ));
        document.add(new Paragraph(
            String.format("Valor do Aluguel: R$ %.2f", proposta.getValor()),
            fonteNormal
        ));
        document.add(new Paragraph(
            "Data do Evento: " + proposta.getDataEvento(),
            fonteNormal
        ));
        document.add(new Paragraph(
            "Descrição do Evento: " + proposta.getDescricaoEvento(),
            fonteNormal
        ));
        
        document.add(new Paragraph("\n"));
    }
    
    private void adicionarCondicoes(Document document) throws Exception {
        Font fonteSecao = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font fonteNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);
        
        Paragraph secaoCondicoes = new Paragraph("4. CONDIÇÕES E OBRIGAÇÕES", fonteSecao);
        document.add(secaoCondicoes);
        
        String[] condicoes = {
            "O locatário se compromete a pagar o valor integral do aluguel no prazo combinado.",
            "O espaço deverá ser devolvido em bom estado, sem danos.",
            "O locatário é responsável por qualquer dano causado durante o período de aluguel.",
            "Não é permitido fazer reformas ou alterações no espaço sem consentimento do locador.",
            "O horário de uso do espaço será conforme agendado.",
            "Qualquer atraso no pagamento resultará em multa de 2% ao mês.",
            "Este contrato é válido a partir da data de assinatura pelos dois partidos."
        };
        
        for (int i = 0; i < condicoes.length; i++) {
            document.add(new Paragraph(
                (i + 1) + ". " + condicoes[i],
                fonteNormal
            ));
        }
        
        document.add(new Paragraph("\n\n"));
    }
    
    private void adicionarRodape(Document document) throws Exception {
        Font fonteRodape = FontFactory.getFont(FontFactory.HELVETICA, 10);
        
        Paragraph linhaRodape1 = new Paragraph("Local: ________________________          Data: ________________________", fonteRodape);
        document.add(linhaRodape1);
        
        document.add(new Paragraph("\n"));
        
        Paragraph assinatura1 = new Paragraph("Assinatura do Locador: _____________________", fonteRodape);
        document.add(assinatura1);
        
        Paragraph assinatura2 = new Paragraph("Assinatura do Locatário: _____________________", fonteRodape);
        document.add(assinatura2);
        
        document.add(new Paragraph("\n\n"));
        
        Paragraph rodapeTexto = new Paragraph(
            "Este documento foi gerado automaticamente pelo Sistema de Aluguel de Espaços.",
            FontFactory.getFont(FontFactory.HELVETICA, 8)
        );
        rodapeTexto.setAlignment(Element.ALIGN_CENTER);
        document.add(rodapeTexto);
    }
}
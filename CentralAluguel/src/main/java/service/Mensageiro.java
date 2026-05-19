package service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro {
	
	/**
	 * Guia Importante - Configurar o Email
	 * 
	 * 1. Acesse: https://myaccount.google.com/
	 * 2. Vá em "Segurança" (lado esquerdo)
	 * 3. Ative "Acesso a app menos seguro" (procure por isso)
	 * 4. Ou use "Senhas de app" se tiver autenticação de dois fatores
	 * 
	 * 5. No código acima, substitua:
	 *    - seu_email@gmail.com → seu email real
	 *    - sua_senha_de_aplicativo_gmail → sua senha ou senha de app
	 * 
	 * Se usar outro servidor (não Gmail), mude:
	 * - SMTP_HOST e SMTP_PORT
	 * - Exemplo Outlook:
	 *   SMTP_HOST = "smtp-mail.outlook.com"
	 *   SMTP_PORT = 587
	 **/
    private static final String EMAIL_REMETENTE = "sxsyasuosxs@gmail.com";
    private static final String SENHA = "rbkn xnvx qngd plwj";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    
    /**
     * Envia contrato por e-mail com anexo PDF
     * @param emailDestino E-mail do destinatário
     * @param assunto Assunto do e-mail
     * @param mensagem Corpo da mensagem
     * @param nomeArquivoPDF Nome do arquivo PDF (contrato)
     * @return true se enviado com sucesso, false caso contrário
     */
    public boolean enviarContratoEmail(String emailDestino, String assunto, String mensagem, String nomeArquivoPDF) {
        try {
        	System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        	
            // Criar anexo
            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath(nomeArquivoPDF);
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setDescription("Contrato de Aluguel");
            
            // Criar e-mail com suporte a anexo
            MultiPartEmail email = new MultiPartEmail();
            
            // Configurar servidor SMTP (Gmail)
            email.setHostName(SMTP_HOST);
            email.setSmtpPort(SMTP_PORT);
            email.setAuthenticator(new DefaultAuthenticator(EMAIL_REMETENTE, SENHA));
            email.setTLS(true);
            
            // Configurar remetente
            email.setFrom(EMAIL_REMETENTE, "Sistema de Aluguel de Espaços");
            
            // Configurar destinatário
            email.addTo(emailDestino);
            
            // Configurar assunto e corpo
            email.setSubject(assunto);
            email.setMsg(mensagem + "\n\nSegue em anexo o contrato de aluguel.");
            
            // Adicionar anexo
            email.attach(anexo);
            
            // Enviar
            email.send();
            
            System.out.println("E-mail enviado com sucesso para: " + emailDestino);
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Versão alternativa: Enviar apenas mensagem de texto (sem anexo)
     */
    public boolean enviarMensagemSimples(String emailDestino, String assunto, String mensagem) {
        try {
        	System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        	
            Email email = new SimpleEmail();
            
            // Configurar servidor SMTP (Gmail)
            email.setHostName(SMTP_HOST);
            email.setSmtpPort(SMTP_PORT);
            email.setAuthenticator(new DefaultAuthenticator(EMAIL_REMETENTE, SENHA));
            email.setTLS(true);
            
            // Configurar remetente
            email.setFrom(EMAIL_REMETENTE, "Sistema de Aluguel de Espaços");
            
            // Configurar destinatário
            email.addTo(emailDestino);
            
            // Configurar assunto e corpo
            email.setSubject(assunto);
            email.setMsg(mensagem);
            
            // Enviar
            email.send();
            
            System.out.println("E-mail enviado com sucesso!");
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
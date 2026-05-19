package central;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

import model.Pessoa;

public class Persistencia {
	
	public void salvarCentral(CentralDeInformacoes centralDeInformacoes, String nomeDoArquivo) {
		try {
			XStream xstream = new XStream();
			
			xstream.alias("Central", CentralDeInformacoes.class);
			xstream.alias("Pessoa", Pessoa.class);
			
			String xml = xstream.toXML(centralDeInformacoes);
			
			String caminhoCompleto = "data" + File.separator + nomeDoArquivo;
			FileWriter fileWriter = new FileWriter(caminhoCompleto);
			fileWriter.write(xml);
			fileWriter.close();
			
		} catch (IOException e) {
			System.out.println("Erro ao salvar a central: " + e.getMessage());
		}
	}
	
	public CentralDeInformacoes recuperarCentral(String nomeDoArquivo) {
	    try {
	        String caminhoCompleto = "data" + File.separator + nomeDoArquivo;
	        File arquivo = new File(caminhoCompleto);

	        if (!arquivo.exists()) {
	            System.out.println("Arquivo não encontrado!");
	            return new CentralDeInformacoes();
	        }

	        XStream xstream = new XStream();
	        
	        xstream.addPermission(com.thoughtworks.xstream.security.AnyTypePermission.ANY);

	        xstream.alias("Central", CentralDeInformacoes.class);
	        xstream.alias("Pessoa", Pessoa.class);

	        String conteudo = lerArquivo(caminhoCompleto);
	        CentralDeInformacoes central = (CentralDeInformacoes) xstream.fromXML(conteudo);

	        System.out.println("Central recuperada com sucesso!");
	        return central;
	    } catch (Exception e) {
	        System.out.println("Erro ao recuperar a central: " + e.getMessage());
	        e.printStackTrace();
	        return new CentralDeInformacoes();
	    }
	}
	
	private String lerArquivo(String caminhoCompleto) throws IOException {
	    StringBuilder conteudo = new StringBuilder();
	    
	    try (java.util.Scanner scanner = new java.util.Scanner(new File(caminhoCompleto))) {
	        while (scanner.hasNextLine()) {
	            conteudo.append(scanner.nextLine()).append("\n");
	        }
	    }
	    
	    return conteudo.toString();
	}
}

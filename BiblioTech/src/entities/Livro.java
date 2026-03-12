package entities;

public class Livro {
	private String nome;
	private String anoPublicacao;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDataPublicacao() {
		return anoPublicacao;
	}
	
	public void setDataPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String exibirInformações() {
		return "O livro: " + nome + " foi publicado no ano de: " + anoPublicacao + ". Descrição do livro: " + descricao;
	}
	
	
}

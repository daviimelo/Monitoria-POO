package central;

import java.util.ArrayList;

import model.Pessoa;

public class CentralDeInformacoes {
	private ArrayList<Pessoa> todasAsPessoas;
    
    public CentralDeInformacoes() {
        this.todasAsPessoas = new ArrayList<>();
    }
	
	public boolean adicionarPessoa(Pessoa pessoa) {
		if (pessoa == null) {
			return false;
		}
		
		if (recuperarPessoaPorCPF(pessoa.getCPF()) != null) {
			return false;
		}
		
		todasAsPessoas.add(pessoa);
		return true;
	}
	
	public Pessoa recuperarPessoaPorCPF(String cpf) {
		for (Pessoa pessoa: todasAsPessoas) {
			if (pessoa.getCPF().equals(cpf)) {
				return pessoa;
			}
		}
		return null;
	}
	
	public ArrayList<Pessoa> getTodasAsPessoas() {
		return todasAsPessoas;
	}

	public void setTodasAsPessoas(ArrayList<Pessoa> todasAsPessoas) {
		this.todasAsPessoas = todasAsPessoas;
	}
	
	private ArrayList<model.PropostaDeAluguel> todasAsPropostas = new ArrayList<>();

	public boolean adicionarProposta(model.PropostaDeAluguel proposta) {
	    if (proposta == null) return false;
	    todasAsPropostas.add(proposta);
	    return true;
	}

	public model.PropostaDeAluguel recuperarPropostaPorId(int id) {
	    if (id > 0 && id <= todasAsPropostas.size()) {
	        return todasAsPropostas.get(id - 1);
	    }
	    return null;
	}

	public ArrayList<model.PropostaDeAluguel> getTodasAsPropostas() {
	    return todasAsPropostas;
	}

	public void setTodasAsPropostas(ArrayList<model.PropostaDeAluguel> todasAsPropostas) {
	    this.todasAsPropostas = todasAsPropostas;
	}
}

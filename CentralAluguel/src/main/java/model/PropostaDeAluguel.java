package model;

public class PropostaDeAluguel {
    
    private Pessoa locador;
    private Pessoa locatario;
    private String tipoEspaco;
    private double valor;
    private String dataEvento;
    private String descricaoEvento;
    
    public PropostaDeAluguel() {
    }
    
    public PropostaDeAluguel(Pessoa locador, Pessoa locatario, String tipoEspaco, 
                            double valor, String dataEvento, String descricaoEvento) {
        this.locador = locador;
        this.locatario = locatario;
        this.tipoEspaco = tipoEspaco;
        this.valor = valor;
        this.dataEvento = dataEvento;
        this.descricaoEvento = descricaoEvento;
    }
    
    public Pessoa getLocador() {
        return locador;
    }
    
    public Pessoa getLocatario() {
        return locatario;
    }
    
    public String getTipoEspaco() {
        return tipoEspaco;
    }
    
    public double getValor() {
        return valor;
    }
    
    public String getDataEvento() {
        return dataEvento;
    }
    
    public String getDescricaoEvento() {
        return descricaoEvento;
    }
    
    public void setLocador(Pessoa locador) {
        this.locador = locador;
    }
    
    public void setLocatario(Pessoa locatario) {
        this.locatario = locatario;
    }
    
    public void setTipoEspaco(String tipoEspaco) {
        this.tipoEspaco = tipoEspaco;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }
    
    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Proposta de Aluguel\n" +
            "  Locador: %s (%s)\n" +
            "  Locatário: %s (%s)\n" +
            "  Tipo de Espaço: %s\n" +
            "  Valor: R$ %.2f\n" +
            "  Data do Evento: %s\n" +
            "  Descrição: %s",
            locador.getNome(), locador.getCPF(),
            locatario.getNome(), locatario.getCPF(),
            tipoEspaco, valor, dataEvento, descricaoEvento
        );
    }
}
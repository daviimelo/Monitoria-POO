package setimaLista;

import java.util.Random;

public class JogoDaForca {
    private Palavra[] dicionario;
    private int posicaoSorteada;
    private String gabarito;

    public JogoDaForca(Palavra[] dicionario) {
        this.dicionario = dicionario;
        this.posicaoSorteada = -1; 
        this.gabarito = "";
    }

    public String getGabarito() { return gabarito; }
    public int getPosicaoSorteada() { return posicaoSorteada; }
    public Palavra[] getDicionario() { return dicionario; }
    public void setDicionario(Palavra[] dicionario) { this.dicionario = dicionario; }

    public String pegarDica() {
        if (posicaoSorteada != -1 && dicionario[posicaoSorteada] != null) {
            return dicionario[posicaoSorteada].getDica();
        }
        return "Nenhuma palavra sorteada no momento.";
    }

    public void sortear() {
        Random rand = new Random();
        int numPosicao;
        boolean temPalavraValida = false;

        for (int i = 0; i < dicionario.length; i++) {
            if (dicionario[i] != null) {
                temPalavraValida = true;
                break;
            }
        }
        if (!temPalavraValida) return;

        // Sorteia até achar uma posição ocupada entre 0-9
        do {
            numPosicao = rand.nextInt(dicionario.length);
        } while (dicionario[numPosicao] == null);

        this.posicaoSorteada = numPosicao;
        
        // Monta o gabarito com as interrogações via concatenação
        String palavraSorteada = dicionario[posicaoSorteada].getPalavra();
        String novoGabarito = ""; 
        
        for (int i = 0; i < palavraSorteada.length(); i++) {
            if (palavraSorteada.charAt(i) == ' ') {
                novoGabarito += " "; 
            } else {
                novoGabarito += "?";
            }
        }
        this.gabarito = novoGabarito;
    }

    public boolean testarLetra(char c) {
        if (posicaoSorteada == -1) return false;
        
        String palavraOriginal = dicionario[posicaoSorteada].getPalavra();
        String palavraFormatada = palavraOriginal.toLowerCase();
        char charMinusculo = Character.toLowerCase(c);
        boolean acertou = false;
        
        // Converte para char[] para poder alterar uma letra específica
        char[] letrasGabarito = this.gabarito.toCharArray();
        
        for (int i = 0; i < palavraFormatada.length(); i++) {
            if (palavraFormatada.charAt(i) == charMinusculo) {
                letrasGabarito[i] = palavraOriginal.charAt(i); 
                acertou = true;
            }
        }
        
        this.gabarito = new String(letrasGabarito);
        return acertou;
    }

    public boolean testaSeAcabou() {
        boolean temInterrogacao = false;
        for (int i = 0; i < gabarito.length(); i++) {
            if (gabarito.charAt(i) == '?') {
                temInterrogacao = true;
                break;
            }
        }

        if (!temInterrogacao) {
            if (posicaoSorteada != -1) {
                dicionario[posicaoSorteada] = null;
                posicaoSorteada = -1; 
            }
            return true;
        }
        return false;
    }
}
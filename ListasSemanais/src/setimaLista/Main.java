package setimaLista;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Palavra[] dicionario = new Palavra[10];

        System.out.println("Preencha o dicionário com 10 palavras.");
        
        // Preenchimento manual das palavras
        for (int i = 0; i < 10; i++) {
            System.out.print("Digite a palavra " + (i + 1) + ": ");
            String p = scanner.nextLine();
            System.out.print("Digite a dica para '" + p + "': ");
            String d = scanner.nextLine();
            dicionario[i] = new Palavra(p, d);
        }

        // Início do Jogo
        JogoDaForca jogo = new JogoDaForca(dicionario);
        jogo.sortear();
        
        int letrasErradas = 0;
        int tamanhoPalavra = jogo.getGabarito().length(); 

        System.out.println("\nInicio do Jogo");
        System.out.println("A dica é: " + jogo.pegarDica());

        // Loop de palpites
        while (!jogo.testaSeAcabou()) {
            System.out.println("\nGabarito: " + jogo.getGabarito());
            System.out.print("Qual letra você quer testar? ");
            char letra = scanner.nextLine().charAt(0);

            if (jogo.testarLetra(letra)) {
                System.out.println("Boa! A letra '" + letra + "' faz parte da palavra.");
            } else {
                System.out.println("Que pena! A letra '" + letra + "' não faz parte.");
                letrasErradas++;
            }
        }

        System.out.println("\nResultado!");
        System.out.println("A palavra oculta era: " + jogo.getGabarito());
        
        int pontuacao = (2 * tamanhoPalavra) - letrasErradas;
        System.out.println("Sua pontuação final: " + pontuacao);
        
        scanner.close();
    }
}
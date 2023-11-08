package tabHash;
import java.util.Random;

public class DesempenhoHash {

    public static void main(String[] args) {

        int[] tamanhos = {20000, 100000, 500000, 1000000, 5000000};
        int[] numElementos = {20000, 100000, 500000, 1000000, 5000000};

        for (int tamanho : tamanhos) {
            for (int numElems : numElementos) {
                TabelaHash tabelaResto = new TabelaHash(tamanho, false);
                TabelaHash tabelaDobramento = new TabelaHash(tamanho, false);
                TabelaHash tabelaMultiplicacao = new TabelaHash(tamanho, false);

                System.out.println("Tamanho da tabela: " + tamanho);
                System.out.println("Número de elementos: " + numElems);

                medirDesempenhoInsercao(tabelaResto, "Resto");
                medirDesempenhoInsercao(tabelaDobramento, "Dobramento");
                medirDesempenhoInsercao(tabelaMultiplicacao, "Multiplicacao");

                medirDesempenhoLocalizacao(tabelaResto, "Resto");
                medirDesempenhoLocalizacao(tabelaDobramento, "Dobramento");
                medirDesempenhoLocalizacao(tabelaMultiplicacao, "Multiplicacao");

                System.out.println("Número de colisões (Resto): " + contarColisoes(tabelaResto));
                System.out.println("Número de colisões (Dobramento): " + contarColisoes(tabelaDobramento));
                System.out.println("Número de colisões (Multiplicacao): " + contarColisoes(tabelaMultiplicacao));
                System.out.println();
            }
        }
    }

    private static void inserirRestoAleatorio(TabelaHash tabela, int numElementos) {
        Random random = new Random();
        for (int i = 0; i < numElementos; i++) {
            int nr = random.nextInt(900000000) + 100000000; // números de 100000000 a 999999999
            Registro r = new Registro(nr);
            tabela.insere(r);
        }
    }

    private static void medirDesempenhoInsercao(TabelaHash tabela, String funcaoHash) {
        long startTime = System.currentTimeMillis();

        // inserindo elementos para medir tempo de inserção.
        for (int i = 0; i < 10000; i++) {
            int nr = 100000000 + i; // Simulando mais inserções
            Registro r = new Registro(nr);
            if(funcaoHash.equalsIgnoreCase("Resto")){
                tabela.insere(r);
            }else if(funcaoHash.equalsIgnoreCase("Multiplicacao")){
                tabela.insereMultiplicacao(r);
            }else {
                tabela.insereDobramento(r);
            }

        }

        long endTime = System.currentTimeMillis();
        long tempo = endTime - startTime;
        System.out.println("Tempo de inserção (" + funcaoHash + "): " + tempo + " ms");
    }

    private static void medirDesempenhoLocalizacao(TabelaHash tabela, String funcaoHash) {
        long startTime = System.currentTimeMillis();

        // Localiza os elementos para medir o tempo
        for (int i = 0; i < 10000; i++) {
            int nr = 100000000 + i; // Simulando localização de elementos
            if(funcaoHash.equalsIgnoreCase("Resto")){
                tabela.localiza(nr);
            }else if(funcaoHash.equalsIgnoreCase("Multiplicacao")){
                tabela.localizaMultiplicacao(nr);
            }else {
                tabela.localizaDobramento(nr);
            }
        }

        long endTime = System.currentTimeMillis();
        long tempo = endTime - startTime;
        System.out.println("Tempo de localização (" + funcaoHash + "): " + tempo + " ms");
    }

    private static int contarColisoes(TabelaHash tabela) {
        int totalColisoes = 0;
        for (int i = 1; i < tabela.tabHash.length; i++) {
            if (tabela.tabHash[i] != null) {
                int h = tabela.hash(tabela.tabHash[i].getCodigo());
                if (h != i) {
                    totalColisoes++;
                }
            }
        }
        return totalColisoes;
    }


}

package tabHash;

import java.util.Random;

public class TesteTabelaHash {
    public static void main(String[] args) {
        int[] tamanhos = {10, 100, 1000, 10000, 100000};
        int[] quantidadesDeDados = {20000, 100000, 500000, 1000000, 5000000};

        for (int tamanho : tamanhos) {
            System.out.println("=== Tabela Hash de Tamanho " + tamanho + " ===");
            TabelaHash tabelaHash = new TabelaHash(tamanho, true);

            for (int quantidadeDeDados : quantidadesDeDados) {
                System.out.println("\nInserindo " + quantidadeDeDados + " elementos aleatórios:");

                Registro[] conjuntoDeDados = gerarConjuntoDeDados(quantidadeDeDados);

                for (Registro registro : conjuntoDeDados) {
                    int nr = registro.getCodigo();
                    tabelaHash.insere(registro);
                }

                System.out.println("Tabela Hash (Divisão):\n" + tabelaHash.imprimeTab(true));

                tabelaHash = new TabelaHash(tamanho, true);

                for (Registro registro : conjuntoDeDados) {
                    int nr = registro.getCodigo();
                    tabelaHash.insereDobramento(registro);
                }

                System.out.println("Tabela Hash (Dobramento):\n" + tabelaHash.imprimeTab(true));

                tabelaHash = new TabelaHash(tamanho, true);

                for (Registro registro : conjuntoDeDados) {
                    int nr = registro.getCodigo();
                    tabelaHash.insereMultiplicacao(registro);
                }

                System.out.println("Tabela Hash (Multiplicação):\n" + tabelaHash.imprimeTab(true));
            }
        }
    }

    private static Registro[] gerarConjuntoDeDados(int quantidade) {
        Registro[] conjuntoDeDados = new Registro[quantidade];
        Random random = new Random();

        for (int i = 0; i < quantidade; i++) {
            int numeroAleatorio = random.nextInt(1000000000); // Gere números aleatórios de 0 a 999999999.
            conjuntoDeDados[i] = new Registro(numeroAleatorio);
        }

        return conjuntoDeDados;
    }
}

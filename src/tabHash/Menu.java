package tabHash;

import java.util.Scanner;

public class Menu {
    private TabelaHash th;

    public Menu() {
        System.out.print("Tamanho da tabela -> ");
        Scanner scn = new Scanner(System.in);
        int tam = scn.nextInt();

        System.out.print("Possibilita repeticao (0 -> não possibilita | Outro número -> sim) -> ");
        boolean repete = false;
        int rep = scn.nextByte();
        if (rep != 0) {
            repete = true;
        }

        th = new TabelaHash(tam, repete);
    }

    public static void main(String[] args) {
        Menu main = new Menu();
        main.exibirMenuPrincipal();
    }

    public void exibirMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opc;

        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("0 - Encerrar.");
            System.out.println("1 - Inserir elemento (função de hash de resto).");
            System.out.println("2 - Inserir elemento (função de hash de dobramento).");
            System.out.println("3 - Inserir elemento (função de hash de multiplicação).");
            System.out.println("4 - Localizar elemento (função de hash de resto).");
            System.out.println("5 - Localizar elemento (função de hash de dobramento).");
            System.out.println("6 - Localizar elemento (função de hash de multiplicação).");
            System.out.println("7 - Remover elemento (função de hash de resto).");
            System.out.println("8 - Remover elemento (função de hash de dobramento).");
            System.out.println("9 - Remover elemento (função de hash de multiplicação).");
            System.out.println("10 - Imprimir tabela.");
            System.out.println("======================");
            System.out.print("Opção -> ");

            opc = scanner.nextInt();

            switch (opc) {
                case 0:
                    break;
                case 1:
                    Registro r1 = criarRegistro();
                    if (r1 == null) {
                        System.out.println("Elemento inválido.");
                        continue;
                    }
                    String ret1 = th.insere(r1); // Usando a função de hash de resto.
                    processarResultadoInsercao(ret1);
                    break;
                case 2:
                    Registro r2 = criarRegistro();
                    if (r2 == null) {
                        System.out.println("Elemento inválido.");
                        continue;
                    }
                    String ret2 = th.insereDobramento(r2); // Usando a função de hash de dobramento.
                    processarResultadoInsercao(ret2);
                    break;
                case 3:
                    Registro r3 = criarRegistro();
                    if (r3 == null) {
                        System.out.println("Elemento inválido.");
                        continue;
                    }
                    String ret3 = th.insereMultiplicacao(r3); // Usando a função de hash de multiplicação.
                    processarResultadoInsercao(ret3);
                    break;
                case 4:
                    System.out.print("Informe o número identificador do elemento -> ");
                    int nr1 = scanner.nextInt();
                    int i1 = th.localiza(nr1);
                    processarResultadoLocalizacao(i1);
                    break;
                case 5:
                    System.out.print("Informe o número identificador do elemento -> ");
                    int nr2 = scanner.nextInt();
                    int i2 = th.localizaDobramento(nr2);
                    processarResultadoLocalizacao(i2);
                    break;
                case 6:
                    System.out.print("Informe o número identificador do elemento -> ");
                    int nr3 = scanner.nextInt();
                    int i3 = th.localizaMultiplicacao(nr3);
                    processarResultadoLocalizacao(i3);
                    break;
                case 7:
                    System.out.print("Informe o número identificador do elemento -> ");
                    int nr4 = scanner.nextInt();
                    Registro r4 = th.remove(nr4);
                    processarResultadoRemocao(r4);
                    break;
                case 8:
                    System.out.print("Informe o número identificador do elemento -> ");
                    int nr5 = scanner.nextInt();
                    Registro r5 = th.removeDobramento(nr5);
                    processarResultadoRemocao(r5);
                    break;
                case 9:
                    System.out.print("Informe o número identificador do elemento -> ");
                    int nr6 = scanner.nextInt();
                    Registro r6 = th.removeMultiplicacao(nr6);
                    processarResultadoRemocao(r6);
                    break;
                case 10:
                    String tabelaImprimida = th.imprimeTab(true);
                    System.out.println(tabelaImprimida);
//                    System.out.println(th.imprimeTab(true));
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opc != 0);
    }

    private Registro criarRegistro() {
        try {
            System.out.print("Informe o número identificador(registro) -> ");
            Scanner scnN = new Scanner(System.in);
            int nr = scnN.nextInt();

            System.out.print("Informe um identificador para o registro inserido -> ");
            Scanner scnS = new Scanner(System.in);
            String dd = scnS.nextLine();

            Registro r = new Registro(nr);
            r.setImprecao(dd);

            return r;
        } catch (Exception ex) {
            return null;
        }
    }

    private void processarResultadoInsercao(String ret) {
        System.out.println(ret); // Imprime a mensagem de retorno.
    }

    private void processarResultadoLocalizacao(int i) {
        if (i == -1) {
            System.out.println("Elemento não localizado.");
        } else {
            System.out.println("Localizado na posição " + i);
        }
    }

    private void processarResultadoRemocao(Registro r) {
        if (r == null) {
            System.out.println("ERRO: elemento não localizado.");
        } else {
            System.out.println("Removido o elemento:");
            System.out.println(th.imprimeElem(r, true));
        }
    }
}

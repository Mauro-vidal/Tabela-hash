package hash;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Tamanho da tabela -> ");
        Scanner scn = new Scanner(System.in);
        int tam = scn.nextInt();

        System.out.print("Possibilita repeticao (0 = nao / qqr. outro = sim) -> ");
        boolean repete = false;
        int rep = scn.nextByte();
        if (rep != 0) {
            repete = true;
        }

        TabelaHash th = new TabelaHash(tam, repete);

        while (true) {

            System.out.println("================================================");
            System.out.println("           Tabela hash");
            System.out.println("             (sem encadeamento)");
            System.out.println("================================================");
            System.out.println("0 - encerrar.");
            System.out.println("1 - inserir elemento.");
            System.out.println("2 - localiza elemento.");
            System.out.println("3 - remover elemento da tabela.");
            System.out.println("4 - imprimir tabela.");
            System.out.println("================================================");

            System.out.println("");
            System.out.print("Opcao -> ");

            int opc = scn.nextByte();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                Registro r = _criaElem();
                if (r == null) {
                    System.out.println("Elemento invalido.");
                    continue;
                }
                int ret = th.insere(r);
                switch (ret) {
                    case 1:
                        System.out.println("Inclusao bem sucedida.");
                        break;
                    case 2:
                        System.out.println("ERRO: identificador ja' inserido na tabela.");
                        break;
                    case 3:
                        System.out.println("ERRO: a tabela esta' cheia.");
                        break;
                    default:
                        break;
                }
            } else if (opc == 2) {
                System.out.print("Informe o numero identificador do elemento -> ");
                Scanner scn2 = new Scanner(System.in);
                int nr = scn2.nextInt();
                int i = th.localiza(nr);
                if (i == -1) {
                    System.out.println("Elemento nao localizado.");
                } else {
                    System.out.println("Localizado na posicao " + i);
                }
            } else if (opc == 3) {
                System.out.print("Informe o numero identificador do elemento -> ");
                Scanner scn3 = new Scanner(System.in);
                int nr = scn3.nextInt();
                Registro r = th.remove(nr);
                if (r == null){
                    System.out.println("ERRO: elemento nao localizado.");
                }
                else{
                    System.out.println("Removido o elemento:");
                    System.out.println(th.imprimeElem(r, true));
                }

            } else if (opc == 4) {
                System.out.println(th.imprimeTab(true));
            }

        }

    }

    private static Registro _criaElem() {

        try {
            System.out.print("Informe o numero identificador -> ");
            Scanner scnN = new Scanner(System.in);
            int nr = scnN.nextInt();

//            System.out.print("Informe o nome -> ");
//            Scanner scnS = new Scanner(System.in);
//            String nm = scnS.nextLine();
            System.out.print("Informe os dados -> ");
           Scanner scnS = new Scanner(System.in);
           String dd = scnS.nextLine();

            Registro r = new Registro(nr);
            r.setImprecao(dd);

            return r;
        } catch (Exception ex) {
            return null;
        }

    }


}

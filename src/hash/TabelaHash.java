package hash;

public class TabelaHash {
    public TabelaHash(int tam, boolean rep){
        this.tabHash = new Registro[tam];
        this.repete = rep;
        this.qtd = 0;
    }

    private final Registro[]tabHash;
    private final boolean repete;
    private int qtd;

    /**
     * Funcao de hashing.
     *
     * @param nr
     * @return Resto da divisao do numero (nr) pelo tamanho da tabela.
     */
    private int hash(int nr) {
        return nr % tabHash.length;
    } // resto da divisão


    //multiplicação
    private int multiplicacaoHash(int fator){
        double n = 0.6180339887; // numero entre 0 e 1
//        return tabHash.length * fator;
        return (int)(tabHash.length * (fator * n % 1));
    }

    //dobramento
    private int dobramentoHash(int dobramento){
        int somaPartes = 0;
        int numDivisor = 100000000;

        while (dobramento > 0){
            int parte = dobramento / numDivisor;
            somaPartes+= parte;
            dobramento-= parte * numDivisor;
            numDivisor/= 100;
        }
        return somaPartes % tabHash.length;
    }


    /**
     * Insere um elemento na tabela. Este metodo nao e' <i>thread safe</i>.
     *
     * @param r
     * @return 1 = inclusao bem sucedida.<br>2 = elemento com identificador ja'
     * exixtente;<br>3 = tabela cheia.
     */

    public int insere(Registro r){
        // VERIFICAR SE A TABELA ESTA' CHEIA.
        if (qtd == tabHash.length) {
            return 3;
        }
        int nr = r.getCodigo();  // numero identificador do elemento (salvar para diminuir a quantidade de chamadas ao metodo e.getNumero()).
        int h = hash(nr);

        //=== CASO DE NAO PERMITIR REPETICAO ===================================
        if (!repete) {
            // parte da tabela de h ao final.
            for (int i = h; i < tabHash.length; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return 2;
                }
            }
            for (int i = 0; i < h; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return 2;
                }
            }
        }

        //=== LOCALIZAR UMA POSICAO ============================================
        boolean inserido = false;
        for (int i = h; i < tabHash.length; i++) {
            if (tabHash[i] == null) {
                tabHash[i] = r;
                inserido = true;
                break;
            }
        }
        if (!inserido) {
            for (int i = 0; i < h; i++) {
                if (tabHash[i] == null) {
                    tabHash[i] = r;
                    break;
                }
            }
        }
        qtd++;
        return 1;
        //----------------------------------------------------------------------
    }

    /**
     * Localiza um elemento dado seu identificador.
     *
     * @param nr
     * @return A posicao no vetor ou -1 caso nao seja localizado.
     */
    public int localiza(int nr) {

        int h = hash(nr);

        for (int i = h; i < tabHash.length; i++) {
            if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                return i;
            }
        }
        for (int i = 0; i < h; i++) {
            if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Remove um elemento da tabela dado o seu numero identificador.
     *
     * @param nr
     * @return O elemento, caso seja localizado; caso contrario retorna
     * <i>null</i>.
     */
    public Registro remove(int nr) {
        int i = localiza(nr);
        if (i == -1) {
            return null;
        } else {
            Registro r = tabHash[i];
            tabHash[i] = null;
            qtd--;
            return r;
        }
    }

    /**
     * Gera uma String com a impressao da tabela.
     *
     * @param imp
     * @return
     */
    public String imprimeTab(boolean imp) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tabHash.length; i++) {
            Registro r = tabHash[i];
            if (r == null) {
                sb.append("\n").append(i).append(" | -- vago --");
            } else {
                sb.append("\n").append(i).append(" | ").append(imprimeElem(r, imp));
            }
        }
        return sb.toString();

    }

    /**
     * Retorna uma String com a impressao do elemento.
     *
     * @param r
     * @param imp
     * @return String no seguinte formato: <b>[numero] | [nome]</b>
     * <br>
     * Se "imp" == true, acrescenta <b>| [imprecao]</b>
     */
    public String imprimeElem(Registro r, boolean imp) {
        String ret = r.getCodigo() + " | ";
        if (imp) {
            ret += " | " + r.getImprecao();
        }
        return ret;
    }


}
























//    public TabelaHash(int tamanho, boolean repete){
//        this.tabela = new Registro[tamanho];
//        this.repete = repete;
//        this.qtd = 0;
//    }
//
//    private Registro[] tabela;
//
//    private int tamanho;
//
//    private final boolean repete;
//    private int qtd;
//
//
//    private int hashResto(int resto){
//        return resto % tabela.length;
//    }
//
//    private int hashMul(int fator){
//        return fator * tabela.length;
//    }
//
//
//
//    public void inserir(Registro r){
//        if (qtd == tabela.length){
//            System.out.println("Tabela cheia");
//        }
//        int resto = r.getCodigo();
//        int h = hashResto(resto);
//
//        if (!repete){
//            for (int i = h; i < tabela.length; i++){
//                if (tabela[i] != null && tabela[i].getCodigo() == resto){
//                    System.out.println("Posição ocupada");
//                }
//            }
//            for (int i = 0; i < h; i++){
//                if (tabela[i] != null && tabela[i].getCodigo() == resto){
//                    System.out.println("Posição ocupada");
//                }
//            }
//        }
//
//
//    }
package tabHash;


public class TabelaHash {

    final Registro[]tabHash;
    private final boolean repete;
    private int qtd;
    private int qtdColisoes = 0;

    public TabelaHash(int tam, boolean rep){
        this.tabHash = new Registro[tam];
        this.repete = rep;
        this.qtd = 0;
    }

    public int getQtdColisoes() {
        return qtdColisoes;
    }

    public int hash(int nr) {
        return nr % tabHash.length;
    } // resto da divisão

    //multiplicação
    public int multiplicacaoHash(int fator){
        double n = 0.6180339887; // numero entre 0 e 1
        return (int)(tabHash.length * (fator * n % 1));
    }



    //dobramento
    public int dobramentoHash(int dobramento){
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



    public String insere(Registro r){
        // VERIFICAR SE A TABELA ESTA' CHEIA.
        if (qtd == tabHash.length) {
            return "A tabela está cheia, não é possível inserir elementos";
        }
        int nr = r.getCodigo();  // numero identificador do elemento (salva para diminuir a quantidade de chamadas ao metodo e.getNumero()).
        int h = hash(nr);

        //=== CASO DE NAO PERMITIR REPETICAO ===================================
        if (!repete) {
            // parte da tabela de h ao final.
            for (int i = h; i < tabHash.length; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return "Esse elemento já está na tabela";
                }
            }
            for (int i = 0; i < h; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return "Esse elemento já está na tabela";
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
            }else {
                qtdColisoes++;
            }
        }
        if (!inserido) {
            for (int i = 0; i < h; i++) {
                if (tabHash[i] == null) {
                    tabHash[i] = r;
                    break;
                }else {
                    qtdColisoes++;
                }
            }
        }
        qtd++;
        return "Inclusão bem sucedida";
        //----------------------------------------------------------------------
    }

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

        return -1; // registro não encontrado
    }


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



    public String insereMultiplicacao(Registro r) {
        // VERIFICAR SE A TABELA ESTÁ CHEIA.
        if (qtd == tabHash.length) {
            return "A tabela está cheia, não é possível inserir elementos" ;
        }
        int nr = r.getCodigo();  // Número identificador do elemento.
        int h = multiplicacaoHash(nr); // Usando a função de multiplicação.

        //=== CASO DE NÃO PERMITIR REPETIÇÃO ===================================
        if (!repete) {
            for (int i = h; i < tabHash.length; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return "Esse elemento já está na tabela";
                }
            }
            for (int i = 0; i < h; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return "Esse elemento já está na tabela";
                }
            }
        }

        //=== LOCALIZAR UMA POSIÇÃO ============================================
        boolean inserido = false;
        for (int i = h; i < tabHash.length; i++) {
            if (tabHash[i] == null) {
                tabHash[i] = r;
                inserido = true;
                break;
            }else {
                qtdColisoes++;
            }
        }
        if (!inserido) {
            for (int i = 0; i < h; i++) {
                if (tabHash[i] == null) {
                    tabHash[i] = r;
                    break;
                }else {
                    qtdColisoes++;
                }
            }
        }
        qtd++;
        return "Registro inserido com sucesso";
    }

    public int localizaMultiplicacao(int valor) {
        int h = multiplicacaoHash(valor);

        for (int i = h; i < tabHash.length; i++) {
            if (tabHash[i] != null && tabHash[i].getCodigo() == valor) {
                return i;
            }
        }
        for (int i = 0; i < h; i++) {
            if (tabHash[i] != null && tabHash[i].getCodigo() == valor) {
                return i;
            }
        }

        return -1;
    }

    public Registro removeMultiplicacao(int valor) {
        int i = localizaMultiplicacao(valor);
        if (i == -1) {
            return null;
        } else {
            Registro r = tabHash[i];
            tabHash[i] = null;
            qtd--;
            return r;
        }
    }

    public String insereDobramento(Registro r) {
        // VERIFICAR SE A TABELA ESTÁ CHEIA.
        if (qtd == tabHash.length) {
            return "A tabela está cheia, não é possível inserir elementos";
        }
        int nr = r.getCodigo();  // Número identificador do elemento.
        int h = dobramentoHash(nr); // Usando a função de dobramento.

        //=== CASO DE NÃO PERMITIR REPETIÇÃO ===================================
        if (!repete) {
            for (int i = h; i < tabHash.length; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return "Esse elemento já está na tabela";
                }
            }
            for (int i = 0; i < h; i++) {
                if (tabHash[i] != null && tabHash[i].getCodigo() == nr) {
                    return "Esse elemento já está na tabela";
                }
            }
        }

        //=== LOCALIZAR UMA POSIÇÃO ============================================
        boolean inserido = false;
        for (int i = h; i < tabHash.length; i++) {
            if (tabHash[i] == null) {
                tabHash[i] = r;
                inserido = true;
                break;
            }else {
                qtdColisoes++;
            }
        }
        if (!inserido) {
            for (int i = 0; i < h; i++) {
                if (tabHash[i] == null) {
                    tabHash[i] = r;
                    break;
                } else {
                    qtdColisoes++;
                }
            }
        }
        qtd++;
        return "Registro inserido com sucesso";
    }

    public int localizaDobramento(int nr) {
        int h = dobramentoHash(nr);

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
    public Registro removeDobramento(int nr) {
        int i = localizaDobramento(nr);
        if (i == -1) {
            return null;
        } else {
            Registro r = tabHash[i];
            tabHash[i] = null;
            qtd--;
            return r;
        }
    }



    public String imprimeTab(boolean imp) {
        String result = "";
        for (int i = 0; i < tabHash.length; i++) {
            Registro r = tabHash[i];
            if (r == null) {
                result += i + " | -- vago --\n";
            } else {
                result += i + " | " + imprimeElem(r, imp) + "\n";
            }
        }
        return result;
    }


    public String imprimeElem(Registro r, boolean imp) {
        String ret = r.getCodigo() + " | ";
        if (imp) {
            ret += " | " + r.getImprecao();
        }
        return ret;
    }

    public int getTamanhoTabela(){
        return tabHash.length;
    }
}

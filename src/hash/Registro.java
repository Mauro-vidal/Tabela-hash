package hash;

public class Registro {
    private Integer codigo;
    private String imprecao;

    public Registro(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getImprecao() {
        return imprecao;
    }

    public void setImprecao(String imprecao) {
        this.imprecao = imprecao;
    }
}

package model;

public class Revista extends ItemBiblioteca {

    private String edicao;

    public Revista(String titulo, String autor, String edicao) {
        super(titulo, autor);
        this.edicao = edicao;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String toString() {
        return super.toString() + " | Edição: " + edicao;
    }
}

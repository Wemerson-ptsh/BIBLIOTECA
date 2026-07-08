package model;

public class Jornal extends ItemBiblioteca {

    private String data;

    public Jornal(String titulo, String autor, String data) {
        super(titulo, autor);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getTipo() {
        return "Jornal";
    }

    @Override
    public String toString() {
        return super.toString() + " | Data: " + data;
    }
}

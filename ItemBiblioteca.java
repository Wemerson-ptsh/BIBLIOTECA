package model;

public abstract class ItemBiblioteca {

    private String titulo;
    private String autor;

    public ItemBiblioteca(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("[%s] Título: %s | Autor: %s", getTipo(), titulo, autor);
    }
}

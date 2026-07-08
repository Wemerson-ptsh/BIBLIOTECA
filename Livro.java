package model;

public class Livro extends ItemBiblioteca implements Emprestavel {

    private boolean emprestado;

    public Livro(String titulo, String autor) {
        super(titulo, autor);
        this.emprestado = false;
    }

    @Override
    public void emprestar() {
        if (emprestado) {
            throw new IllegalStateException(
                    "O livro \"" + getTitulo() + "\" já está emprestado.");
        }
        this.emprestado = true;
    }

    @Override
    public void devolver() {
        if (!emprestado) {
            throw new IllegalStateException(
                    "O livro \"" + getTitulo() + "\" não está emprestado.");
        }
        this.emprestado = false;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public String toString() {
        String status = emprestado ? "Emprestado" : "Disponível";
        return super.toString() + " | Status: " + status;
    }
}

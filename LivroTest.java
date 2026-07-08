package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void livroDeveComecarComoDisponivel() {
        Livro livro = new Livro("Título", "Autor");

        assertFalse(livro.isEmprestado());
    }

    @Test
    void deveEmprestarLivroDisponivel() {
        Livro livro = new Livro("Título", "Autor");

        livro.emprestar();

        assertTrue(livro.isEmprestado());
    }

    @Test
    void naoDeveEmprestarLivroJaEmprestado() {
        Livro livro = new Livro("Título", "Autor");
        livro.emprestar();

        assertThrows(IllegalStateException.class, livro::emprestar);
    }

    @Test
    void deveDevolverLivroEmprestado() {
        Livro livro = new Livro("Título", "Autor");
        livro.emprestar();

        livro.devolver();

        assertFalse(livro.isEmprestado());
    }

    @Test
    void naoDeveDevolverLivroQueNaoEstaEmprestado() {
        Livro livro = new Livro("Título", "Autor");

        assertThrows(IllegalStateException.class, livro::devolver);
    }

    @Test
    void getTipoDeveRetornarLivro() {
        Livro livro = new Livro("Título", "Autor");

        assertEquals("Livro", livro.getTipo());
    }

    @Test
    void livroDeveSerInstanciaDeItemBibliotecaEEmprestavel() {
        Livro livro = new Livro("Título", "Autor");

        assertInstanceOf(ItemBiblioteca.class, livro);
        assertInstanceOf(Emprestavel.class, livro);
    }
}

package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RevistaJornalTest {

    @Test
    void revistaDeveArmazenarEdicao() {
        Revista revista = new Revista("Título", "Autor", "Edição 100");

        assertEquals("Edição 100", revista.getEdicao());
    }

    @Test
    void revistaNaoDeveImplementarEmprestavel() {
        Revista revista = new Revista("Título", "Autor", "Edição 1");

        assertFalse(revista instanceof Emprestavel,
                "Revista não deve poder ser emprestada neste sistema.");
    }

    @Test
    void getTipoDaRevistaDeveRetornarRevista() {
        Revista revista = new Revista("Título", "Autor", "Edição 1");

        assertEquals("Revista", revista.getTipo());
    }

    @Test
    void jornalDeveArmazenarData() {
        Jornal jornal = new Jornal("Título", "Autor", "05/07/2026");

        assertEquals("05/07/2026", jornal.getData());
    }

    @Test
    void getTipoDoJornalDeveRetornarJornal() {
        Jornal jornal = new Jornal("Título", "Autor", "05/07/2026");

        assertEquals("Jornal", jornal.getTipo());
    }
}

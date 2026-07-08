package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();
    }

    @Test
    void deveAdicionarItemComSucesso() {
        Livro livro = new Livro("1984", "George Orwell");
        biblioteca.adicionar(livro);

        assertEquals(1, biblioteca.total());
        assertTrue(biblioteca.listar().contains(livro));
    }

    @Test
    void naoDeveAdicionarItemNulo() {
        assertThrows(IllegalArgumentException.class, () -> biblioteca.adicionar(null));
    }

    @Test
    void deveListarDiferentesTiposDeItens() {
        biblioteca.adicionar(new Livro("Livro A", "Autor A"));
        biblioteca.adicionar(new Revista("Revista B", "Autor B", "Ed. 1"));
        biblioteca.adicionar(new Jornal("Jornal C", "Autor C", "01/01/2026"));

        ArrayList<ItemBiblioteca> itens = biblioteca.listar();

        assertEquals(3, itens.size());
    }

    @Test
    void deveBuscarPorTituloParcialIgnorandoCase() {
        biblioteca.adicionar(new Livro("Senhor dos Anéis", "Tolkien"));

        Optional<ItemBiblioteca> resultado = biblioteca.buscarPorTitulo("senhor");

        assertTrue(resultado.isPresent());
        assertEquals("Tolkien", resultado.get().getAutor());
    }

    @Test
    void buscaDeveRetornarVazioQuandoNaoEncontrar() {
        biblioteca.adicionar(new Livro("Senhor dos Anéis", "Tolkien"));

        Optional<ItemBiblioteca> resultado = biblioteca.buscarPorTitulo("inexistente");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRemoverItemPorTitulo() {
        biblioteca.adicionar(new Livro("Livro Removível", "Autor X"));

        boolean removido = biblioteca.remover("Livro Removível");

        assertTrue(removido);
        assertEquals(0, biblioteca.total());
    }

    @Test
    void removerDeveRetornarFalseQuandoTituloNaoExiste() {
        boolean removido = biblioteca.remover("Não existe");

        assertFalse(removido);
    }

    @Test
    void listarDeveRetornarCopiaDefensiva() {
        biblioteca.adicionar(new Livro("Livro Original", "Autor"));

        ArrayList<ItemBiblioteca> copia = biblioteca.listar();
        copia.clear();

        assertEquals(1, biblioteca.total(), "Alterar a lista retornada não deve afetar a biblioteca");
    }
}

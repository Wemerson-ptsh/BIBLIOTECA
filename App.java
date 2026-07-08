package app;

import model.Biblioteca;
import model.ItemBiblioteca;
import model.Jornal;
import model.Livro;
import model.Revista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        carregarDadosIniciais();

        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarRevista();
                case 3 -> cadastrarJornal();
                case 4 -> listarItens();
                case 5 -> buscarItem();
                case 6 -> removerItem();
                case 7 -> emprestarLivro();
                case 8 -> devolverLivro();
                case 0 -> {
                    System.out.println("Encerrando o sistema. Até logo!");
                    executando = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== BIBLIOTECA =====");
        System.out.println("1 - Novo livro");
        System.out.println("2 - Nova revista");
        System.out.println("3 - Novo jornal (desafio extra)");
        System.out.println("4 - Listar itens");
        System.out.println("5 - Buscar item");
        System.out.println("6 - Remover item");
        System.out.println("7 - Emprestar livro");
        System.out.println("8 - Devolver livro");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();

        biblioteca.adicionar(new Livro(titulo, autor));
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void cadastrarRevista() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();

        System.out.print("Edição: ");
        String edicao = scanner.nextLine().trim();

        biblioteca.adicionar(new Revista(titulo, autor, edicao));
        System.out.println("Revista cadastrada com sucesso!");
    }

    private static void cadastrarJornal() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();

        System.out.print("Data (dd/mm/aaaa): ");
        String data = scanner.nextLine().trim();

        biblioteca.adicionar(new Jornal(titulo, autor, data));
        System.out.println("Jornal cadastrado com sucesso!");
    }
      private static void listarItens() {
        ArrayList<ItemBiblioteca> itens = biblioteca.listar();

        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
            return;
        }

        System.out.println("----- Itens cadastrados (" + itens.size() + ") -----");

        for (ItemBiblioteca item : itens) {
            System.out.println(item);
        }
    }

    private static void buscarItem() {
        System.out.print("Digite o título (ou parte dele) para buscar: ");
        String titulo = scanner.nextLine().trim();

        Optional<ItemBiblioteca> encontrado = biblioteca.buscarPorTitulo(titulo);

        if (encontrado.isPresent()) {
            System.out.println("Item encontrado:");
            System.out.println(encontrado.get());
        } else {
            System.out.println("Nenhum item encontrado com esse título.");
        }
    }

    private static void removerItem() {
        System.out.print("Digite o título exato do item a remover: ");
        String titulo = scanner.nextLine().trim();

        boolean removido = biblioteca.remover(titulo);

        System.out.println(removido
                ? "Item removido com sucesso!"
                : "Nenhum item encontrado com esse título.");
    }

    private static void emprestarLivro() {
        System.out.print("Digite o título exato do livro a emprestar: ");
        String titulo = scanner.nextLine().trim();

        Optional<ItemBiblioteca> encontrado = biblioteca.buscarPorTitulo(titulo);

        if (encontrado.isEmpty()) {
            System.out.println("Nenhum item encontrado com esse título.");
            return;
        }

        if (!(encontrado.get() instanceof Livro livro)) {
            System.out.println("Este item não é um livro e não pode ser emprestado.");
            return;
        }

        try {
            livro.emprestar();
            System.out.println("Livro emprestado com sucesso!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void devolverLivro() {
        System.out.print("Digite o título exato do livro a devolver: ");
        String titulo = scanner.nextLine().trim();

        Optional<ItemBiblioteca> encontrado = biblioteca.buscarPorTitulo(titulo);

        if (encontrado.isEmpty()) {
            System.out.println("Nenhum item encontrado com esse título.");
            return;
        }

        if (!(encontrado.get() instanceof Livro livro)) {
            System.out.println("Este item não é um livro.");
            return;
        }

        try {
            livro.devolver();
            System.out.println("Livro devolvido com sucesso!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void carregarDadosIniciais() {
        biblioteca.adicionar(new Livro("Dom Casmurro", "Machado de Assis"));
        biblioteca.adicionar(new Livro("O Cortiço", "Aluísio Azevedo"));
        biblioteca.adicionar(new Revista("Superinteressante", "Editora Abril", "Edição 412"));
        biblioteca.adicionar(new Jornal("Diário de Notícias", "Redação", "01/07/2026"));
    }
}

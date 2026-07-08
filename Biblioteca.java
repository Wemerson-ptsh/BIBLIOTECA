package model;

import java.util.ArrayList;
import java.util.Optional;

public class Biblioteca {

    private final ArrayList<ItemBiblioteca> itens;

    public Biblioteca() {
        this.itens = new ArrayList<>();
    }

    public void adicionar(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        itens.add(item);
    }

    public ArrayList<ItemBiblioteca> listar() {
        return new ArrayList<>(itens);
    }

    public Optional<ItemBiblioteca> buscarPorTitulo(String titulo) {
        if (titulo == null) {
            return Optional.empty();
        }

        String alvo = titulo.trim().toLowerCase();

        return itens.stream()
                .filter(item -> item.getTitulo().toLowerCase().contains(alvo))
                .findFirst();
    }

    public boolean remover(String titulo) {
        if (titulo == null) {
            return false;
        }

        String alvo = titulo.trim().toLowerCase();

        return itens.removeIf(item -> item.getTitulo().equalsIgnoreCase(alvo));
    }

    public int total() {
        return itens.size();
    }
}

package Modelos;

import java.util.List;

public record LivroOpenLibrary(String title, List<Autor> authors, int number_of_pages, List<Publisher> publishers) {
    public record Autor(String name) {}
    public record Publisher(String name) {}
}

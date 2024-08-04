package Modelos;

import java.util.stream.Collectors;

public class Livro implements Classificavel, Comparable<Livro>{
    protected String titulo;
    protected String autor;
    protected String editora;
    protected String categoria;
    protected double avaliacoes;
    protected int idLivro;
    protected int numeroDePaginas;

    public Livro(LivroOpenLibrary livroOpen) {
        this.titulo = livroOpen.title();
        this.autor = livroOpen.authors() != null ?
                livroOpen.authors().stream()
                        .map(LivroOpenLibrary.Autor::name)
                        .collect(Collectors.joining(", ")) : "Desconhecido";
        this.numeroDePaginas = livroOpen.number_of_pages();
        this.editora = livroOpen.publishers() != null ?
                livroOpen.publishers().stream()
                        .map(LivroOpenLibrary.Publisher::name)
                        .collect(Collectors.joining(", ")) : "Desconhecido";
    }

    public double getAvaliacoes() {
        return avaliacoes;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public Livro(String titulo, String autor, String editora, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.categoria = categoria;
        this.avaliacoes = 0;
        this.idLivro = new GeradorId().getId();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroDePaginas=" + numeroDePaginas +
                ", editora='" + editora + '\'' +
                '}';
    }

    @Override
    public double obterMedia() {
        return 0;
    }

    @Override
    public int compareTo(Livro livro) {
        return 0;
    }
}

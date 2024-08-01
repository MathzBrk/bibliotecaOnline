package Modelos;

public class Livro implements Classificavel, Comparable<Livro>{
    protected String titulo;
    protected Autor autor;
    protected String editora;
    protected String categoria;
    protected double avaliacoes;
    protected int idLivro;

    public double getAvaliacoes() {
        return avaliacoes;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public Livro(String titulo, Autor autor, String editora, String categoria) {
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

    public Autor getAutor() {
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
                ", autor='" + autor.getNome() + '\'' +
                ", editora='" + editora + '\'' +
                ", categoria='" + categoria + '\'' +
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

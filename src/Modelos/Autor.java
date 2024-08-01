package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Autor {
    private String nome;
    private List<Livro> livros;
    private int idAutor;

    public Autor(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<Livro>();
        this.idAutor = new GeradorId().getId();
    }

    public int getIdAutor() {
        return idAutor;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }
}

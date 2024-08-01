package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private int numeroIdentificacao;
    private List<Livro> livrosEmEmprestimo;

    public Usuario(String nome) {
        this.nome = nome;
        this.numeroIdentificacao = new GeradorId().getId();
        this.livrosEmEmprestimo = new ArrayList<Livro>();
    }

    public List<Livro> getLivrosEmEmprestimo() {
        return livrosEmEmprestimo;
    }

    public void setLivrosEmEmprestimo(List<Livro> livrosEmEmprestimo) {
        this.livrosEmEmprestimo = livrosEmEmprestimo;
    }

    public int getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao(int numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivroEmprestimo(Livro livro) {
        this.livrosEmEmprestimo.add(livro);
    }
}

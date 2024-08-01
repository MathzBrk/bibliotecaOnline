package Modelos;

import ModeloDeNegocios.Aluguel;
import ModeloDeNegocios.Emprestimo;
import ModeloDeNegocios.Transacao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Autor> autores;
    private List<Usuario> usuarios;
    private List<Aluguel> alugueis;
    private List<Emprestimo> emprestimos;

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.alugueis = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void registrarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void registrarAutor(Autor autor) {
        this.autores.add(autor);
    }
    public void registrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }
    public void registrarAluguel(Aluguel aluguel) {
        this.alugueis.add(aluguel);
        aluguel.getUsuario().adicionarLivroEmprestimo(aluguel.getLivro());
    }

    public boolean verificarDisponibilidade(LivroFisico livro){
        return livro.getQuantidadeDisponivel() > 0;
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getLivro().getQuantidadeDisponivel() > 0) {
            emprestimo.getLivro().setQuantidadeDisponivel( emprestimo.getLivro().getQuantidadeDisponivel() - 1);
            this.emprestimos.add(emprestimo);
            emprestimo.getUsuario().adicionarLivroEmprestimo(emprestimo.getLivro());
        } else {
            System.out.println("Livro indisponível para empréstimo.");
        }
    }

    public void cancelarAssinatura(int idAluguel) {
        Iterator<Aluguel> iterator = alugueis.iterator();
        while (iterator.hasNext()) {
            Aluguel aluguel = iterator.next();
            if (aluguel.getIdTransacao() == idAluguel && aluguel.isAtivo()) {
                aluguel.cancelarAssinatura();
                aluguel.getUsuario().getLivrosEmEmprestimo().remove(aluguel.getLivro());
                iterator.remove();
                System.out.println("Assinatura cancelada");
                return; // Saia do método após cancelar a assinatura
            }
        }
        System.out.println("Assinatura não encontrada");
    }

    public void devolverLivro(int idEmprestimo) {
        Iterator<Emprestimo> iterator = emprestimos.iterator();
        while (iterator.hasNext()) {
            Emprestimo emprestimo = iterator.next();
            if (emprestimo.getIdTransacao() == idEmprestimo && emprestimo.isAtivo()) {
                emprestimo.registrarDevolucao();
                emprestimo.getUsuario().getLivrosEmEmprestimo().remove(emprestimo.getLivro());
                ((LivroFisico) emprestimo.getLivro()).setQuantidadeDisponivel(
                        ((LivroFisico) emprestimo.getLivro()).getQuantidadeDisponivel() + 1
                );
                iterator.remove();
                System.out.println("Livro devolvido com sucesso.");
                return; // Saia do método após registrar a devolução
            }
        }
        System.out.println("Empréstimo não encontrado ou já encerrado.");
    }

    public void buscarLivroNaApi(String titulo){
        System.out.println("123");
    }

    public void listarAluguel(){
        for(Aluguel aluguel : alugueis){
            System.out.println(aluguel);
        }
    }

    public void listarEmprestimos(){
        for(Emprestimo emprestimo : emprestimos){
            System.out.println(emprestimo);
        }
    }

    public void removerLivroDoCatalogo(int idLivro) throws Exception {
        try {
            Iterator<Livro> iterator = livros.iterator();
            while (iterator.hasNext()) {
                Livro livro = iterator.next();
                if (livro.getIdLivro() == idLivro) {
                    iterator.remove();
                    System.out.println("Livro removido com sucesso.");
                    return;
                }
            }
        }catch (Exception e){
            System.out.println("Livro não encontrado no catálogo." + e.getMessage());
        }
    }

    public void removerAutor(int idAutor) {
        Iterator<Autor> iterator = autores.iterator();
        while (iterator.hasNext()) {
            Autor autor = iterator.next();
            if (autor.getIdAutor() == idAutor) {
                iterator.remove();
                System.out.println("Autor removido do catálogo.");
                return; // Saia do método após remover o autor
            }
        }
        System.out.println("Autor não encontrado no catálogo.");
    }

    public void removerUsuario(int idUsuario) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getNumeroIdentificacao() == idUsuario) {
                iterator.remove();
                System.out.println("Usuário removido do sistema.");
                return; // Saia do método após remover o usuário
            }
        }
        System.out.println("Usuário não encontrado no sistema.");
    }

    public void exibirEstado() {
        System.out.println("Livros na biblioteca:");
        for (Livro livro : livros) {
            System.out.println(" - " + livro.getTitulo());
        }

        System.out.println("Autores na biblioteca:");
        for (Autor autor : autores) {
            System.out.println(" - " + autor.getNome());
        }

        System.out.println("Usuários na biblioteca:");
        for (Usuario usuario : usuarios) {
            System.out.println(" - " + usuario.getNome());
        }

        System.out.println("Empréstimos na biblioteca:");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(" - Empréstimo ID: " + emprestimo.getIdTransacao() + ", Livro: " + emprestimo.getLivro().getTitulo() + ", Usuário: " + emprestimo.getUsuario().getNome());
        }

        System.out.println("Aluguéis na biblioteca:");
        for (Aluguel aluguel : alugueis) {
            System.out.println(" - Aluguel ID: " + aluguel.getIdTransacao() + ", Livro: " + aluguel.getLivro().getTitulo() + ", Usuário: " + aluguel.getUsuario().getNome());
        }
    }




}

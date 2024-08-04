package Modelos;

import ModeloDeNegocios.Aluguel;
import ModeloDeNegocios.Emprestimo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Aluguel> alugueis;
    private List<Emprestimo> emprestimos;

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
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
        this.usuarios = new ArrayList<>();
        this.alugueis = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void registrarLivro(Livro livro) {
        this.livros.add(livro);
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

    public void buscarLivroNaApiIsbn(String isbn){

        OpenLibraryApiExample openLibraryApiExample = new OpenLibraryApiExample();
        Livro livro = new Livro(openLibraryApiExample.buscarLivroPorISBN(isbn));
        System.out.println(livro);


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

    public void gerarArquivoLivro(Livro livro) throws Exception {
        GeradorDeArquivo geradorDeArquivo = new GeradorDeArquivo();
        geradorDeArquivo.gerarArquivoDeLivro(livro);
    }

    public void gerarArquivoDeLivros() throws Exception {
        GeradorDeArquivo gerador = new GeradorDeArquivo();
        gerador.gerarArquivoDeLista(getLivros(),"livros.json");
    }




}

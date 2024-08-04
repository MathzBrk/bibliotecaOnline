import ModeloDeNegocios.Aluguel;
import ModeloDeNegocios.Emprestimo;
import Modelos.*;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        Biblioteca biblioteca = new Biblioteca();

        // Criar livros
        LivroFisico livroFisico1 = new LivroFisico( "Harry Potter e a Pedra Filosofal", "Kj", "Warner","Fantasia",  5);
        LivroDigital livroDigital1 = new LivroDigital("O Senhor dos Anéis: A Sociedade do Anel", "autor2", "Amazon", "Fantasia");

        // Adicionar livros à biblioteca
        biblioteca.registrarLivro(livroFisico1);
        biblioteca.registrarLivro(livroDigital1);

        // Adicionar livros aos autores


        // Criar usuários
        Usuario usuario1 = new Usuario("Alice");
        Usuario usuario2 = new Usuario("Bob");

        // Adicionar usuários à biblioteca
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);

        // Criar empréstimos e alugueis
        Emprestimo emprestimo1 = new Emprestimo(usuario1, livroFisico1, LocalDate.now(), 35.00);
        Aluguel aluguel1 = new Aluguel(usuario2, livroDigital1, LocalDate.now(), 19.99);

        // Adicionar transações à biblioteca
        biblioteca.registrarEmprestimo(emprestimo1);
        biblioteca.getAlugueis().add(aluguel1);

        // Adicionar livros emprestados aos usuários
        usuario1.adicionarLivroEmprestimo(livroFisico1);
        usuario2.adicionarLivroEmprestimo(livroDigital1);

        // Exibir estado inicial da biblioteca
        System.out.println("Estado inicial da biblioteca:");
        biblioteca.exibirEstado();

        // Exibir estado final da biblioteca
        System.out.println("Estado final da biblioteca:");
        biblioteca.exibirEstado();

        biblioteca.buscarLivroNaApiIsbn("9780439139595");







    }
}

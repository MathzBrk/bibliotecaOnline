package Modelos;

public class LivroFisico extends Livro {
    private int quantidadeDisponivel;

    public LivroFisico(String titulo, Autor autor, String editora, String categoria, int quantidadeDisponivel) {
        super(titulo, autor, editora, categoria);
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }


    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }
}

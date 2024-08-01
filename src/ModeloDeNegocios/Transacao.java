package ModeloDeNegocios;

import Modelos.GeradorId;
import Modelos.Livro;
import Modelos.Usuario;

import java.time.LocalDate;

public abstract class Transacao {
    protected Usuario usuario;
    protected Livro livro;
    protected LocalDate dataInicio;
    protected LocalDate dataFimPrevista;
    protected double valor;
    private boolean ativo;
    private int idTransacao;

    public Transacao(Usuario usuario, Livro livro, LocalDate dataInicio, LocalDate dataFimPrevista, double valor) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.valor = valor;
        this.ativo = true;
        this.idTransacao = new GeradorId().getId();
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getValor() {
        return valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "usuario=" + usuario +
                ", livro=" + livro +
                ", dataInicio=" + dataInicio +
                ", dataFimPrevista=" + dataFimPrevista +
                ", valor=" + valor +
                ", ativo=" + ativo +
                ", idTransacao=" + idTransacao +
                '}';
    }

    public LocalDate getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(LocalDate dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }
}

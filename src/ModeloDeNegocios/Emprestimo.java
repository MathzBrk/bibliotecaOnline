package ModeloDeNegocios;

import Modelos.Livro;
import Modelos.LivroFisico;
import Modelos.Usuario;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo extends Transacao {
    public Emprestimo(Usuario usuario, LivroFisico livro, LocalDate dataInicio, double valor) {
        super(usuario, livro, dataInicio, dataInicio.plus(14, ChronoUnit.DAYS), valor);
    }

    public double calcularMulta(){
        if(!isAtivo() && LocalDate.now().isAfter(getDataFimPrevista())){
            long diasAtraso =  ChronoUnit.DAYS.between(getDataFimPrevista(), LocalDate.now());
            double multa = diasAtraso * (getValor() / 2);
            return multa;
        }
        return 0;
    }

    @Override
    public LivroFisico getLivro() {
        return (LivroFisico) super.getLivro();
    }

    public void registrarDevolucao() {
        if (isAtivo()) {
            setAtivo(false);
            ((LivroFisico) getLivro()).setQuantidadeDisponivel(((LivroFisico) getLivro()).getQuantidadeDisponivel() + 1);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Livro ja devolvido!");
        }
    }
}


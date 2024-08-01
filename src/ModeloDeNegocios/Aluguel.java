package ModeloDeNegocios;

import Modelos.Livro;
import Modelos.LivroDigital;
import Modelos.Usuario;

import java.time.LocalDate;

public class Aluguel extends Transacao{

    public Aluguel(Usuario usuario, LivroDigital livro, LocalDate dataInicio, double valor) {
        super(usuario, livro, dataInicio, dataInicio.plusDays(30), valor);
    }

    public void cancelarAssinatura(){
        if(isAtivo()){
            setAtivo(false);
        }
    }

    public void extenderAssinatura(int dias){
        if (isAtivo()){
            setDataFimPrevista(getDataFimPrevista().plusDays(dias));
            System.out.println("Aluguel renovado até: " + getDataFimPrevista());
        }else{
            System.out.println("Não foi possivel renovar o aluguel");
        }
    }


}

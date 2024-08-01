package Modelos;

public class GeradorId {
    private static int contador = 0;
    private int id;

    public GeradorId() {
        this.id = ++contador;
    }

    public int getId() {
        return id;
    }
}


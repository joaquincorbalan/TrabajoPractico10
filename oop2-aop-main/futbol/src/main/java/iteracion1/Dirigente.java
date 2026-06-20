package iteracion1;

public class Dirigente {
    private String nombre;

    public Dirigente(String nombre) {
        this.nombre = nombre;
    }

    public void gritarA(Arbitro unArbitro, String frase) {
        unArbitro.escucharGrito(frase);
    }

    // supongamos que esta clase tiene otras responsabilidades
    // si no, no tendria razon de ser
}

package iteracion1;

public class Jugador  {
    private final String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void insularA(Arbitro unArbitro, String insulto) {
        unArbitro.escucharInsulto(insulto);
    }

    // supongamos que esta clase tiene otras responsabilidades
    // si no, no tendria razon de ser
}

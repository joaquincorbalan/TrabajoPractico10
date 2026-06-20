package iteracion2;

public class Dirigente {
    private String nombre;
    private final Registracion registro;

    public Dirigente(String nombre, Registracion registro) {
        this.nombre = nombre;
        this.registro = registro;
    }

    public void gritarA(Arbitro unArbitro, String frase) {
        unArbitro.escucharGrito(frase);
        this.registro.registrar(frase);
    }

    // supongamos que esta clase tiene otras responsabilidades
    // si no, no tendria razon de ser
}

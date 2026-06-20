package iteracion3;

public class Dirigente implements PersonaDelFutbol {
    private String nombre;
    private final Registracion registro;

    public Dirigente(String nombre, Registracion registro) {
        this.nombre = nombre;
        this.registro = registro;
    }

//    public void gritarA(Arbitro unArbitro, String frase) {
//        unArbitro.escucharGrito(frase);
//        this.registro.registrar(frase);
//    }

    @Override
    public void dirigirmeA(Arbitro unArbitro, String mensaje) {
        unArbitro.escucharGrito(mensaje);
    }

    // supongamos que esta clase tiene otras responsabilidades
    // si no, no tendria razon de ser
}

package aop;

public class Arbitro {
    private String nombre;

    public Arbitro(String nombre) {
        this.nombre = nombre;
    }

    public void escucharInsulto(String insulto) {
        //escucha y determina si saca amarilla, roja, etc
    }

    public void escucharGrito(String frase) {
        //escucha y determina si contesta, saca amarilla, roja, etc
    }

    public void sacarTarjetaA(Jugador unJugador, String color) {
        System.out.println("El árbitro " + nombre + " saca tarjeta " + color + " a " + unJugador.nombre());
    }

    public String nombre() {
        return nombre;
    }
}

package iteracion3;

public class Main {
    public static void main(String[] args) {
        var jugador = new ConRegistracion(new Jugador("Diego"), new Registracion());
        jugador.dirigirmeA(new Arbitro("Castrilli"), "lppp...");
    }
}

package aop;

public class Main {
    public static void main(String[] args) {
        var castrilli = new Arbitro("Castrilli");
        var elDiego = new Jugador("Diego");
        elDiego.insultarA(castrilli, "lppp...");

        castrilli.sacarTarjetaA(elDiego, "AMARILLA");
    }
}
package ejercicio;

import ejercicio.Modele.Log;

public class TestLog {
    @Log
    public void metodoDePrueba(String a, String b) {
        System.out.println("Ejecutando metodoDePrueba");
    }

    public static void main(String[] args) {
        new TestLog().metodoDePrueba("hola", "chau");
    }
}


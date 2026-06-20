package iteracion3;

public class ConRegistracion implements PersonaDelFutbol {
    private PersonaDelFutbol personaDelFutbol;
    private final Registracion registro;

    public ConRegistracion(PersonaDelFutbol personaDelFutbol, Registracion registro) {
        this.personaDelFutbol = personaDelFutbol;
        this.registro = registro;
    }

    @Override
    public void dirigirmeA(Arbitro unArbitro, String mensaje) {
        this.personaDelFutbol.dirigirmeA(unArbitro, mensaje);
        this.registro.registrar(mensaje);
    }
}

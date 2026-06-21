package ejercicio.Modele;

import java.time.LocalDate;
import java.util.List;

public class GestorConcursos {
    private List<Concursos> concursos;

    public GestorConcursos(List<Concursos> concursos) {
        this.concursos = concursos;
    }

    public List<Concursos> getConcursosVigentes(LocalDate hoy) {
        return concursos.stream()
                .filter(c -> c.getFechaFinalizacionIns().isAfter(hoy))
                .toList();
    }
}


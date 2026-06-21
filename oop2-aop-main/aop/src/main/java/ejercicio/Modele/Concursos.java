package ejercicio.Modele;

import java.time.LocalDate;

public class Concursos {
    private String idConcurso;
    private String nombre;
    private LocalDate fechaInicioIns;
    private LocalDate fechaFinalizacionIns;

    //faltan restricciones
    public Concursos(String idConcurso, String nombre, LocalDate fechaInicioIns, LocalDate fechaFinalizacionIns) {
        this.idConcurso = idConcurso;
        this.nombre = nombre;
        this.fechaInicioIns = fechaInicioIns;
        this.fechaFinalizacionIns = fechaFinalizacionIns;
    }

    public LocalDate getFechaFinalizacionIns() {
        return fechaFinalizacionIns;
    }

    @Override
    public String toString() {
        return idConcurso + " - " + nombre;
    }


    public String getIdConcurso() {
        return this.idConcurso;
    }

    public String getNombre() {
        return this.nombre;
    }
}
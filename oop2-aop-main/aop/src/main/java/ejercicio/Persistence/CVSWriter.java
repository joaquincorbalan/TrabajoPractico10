package ejercicio.Persistence;

import ejercicio.Modele.EscritorMetod;
import ejercicio.Modele.Participantes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CVSWriter implements EscritorMetod {

    @Override
    public void inscribir(Participantes participante, String path) {
        // Formato: Apellido, Nombre, Teléfono, Email, Dni, IdConcurso
        String linea = String.format("%s, %s, %s, %s, %s, %s",
                participante.getApellido(),
                participante.getNombre(),
                participante.getTelefono(),
                participante.getEmail(),
                participante.getDni(),
                participante.getIdConcurso());
        File file = new File(path);
        file.getParentFile().mkdirs(); // crea la carpeta data si no existe

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al escribir inscripción en archivo: " + e.getMessage());
        }

    }
}
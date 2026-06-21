package ejercicio.Persistence;

import ejercicio.Modele.Concursos;
import ejercicio.Modele.LectorMetod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CVSReader implements LectorMetod {

    @Override
    public List<Concursos> recuperarConcursos(String path) {
        List<Concursos> concursos = new ArrayList<>();
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null) {
            throw new RuntimeException("No se encontró el archivo " + path + " en resources");
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] partes = linea.split(",\\s*");
                String idConcurso = partes[0];
                String nombre = partes[1];

                LocalDate fechaInicioIns = parseFecha(partes[2], dateFormatter, dateTimeFormatter);
                LocalDate fechaFinIns = parseFecha(partes[3], dateFormatter, dateTimeFormatter);

                concursos.add(new Concursos(idConcurso, nombre, fechaInicioIns, fechaFinIns));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return concursos;
    }

    private LocalDate parseFecha(String texto,
                                 DateTimeFormatter dateFormatter,
                                 DateTimeFormatter dateTimeFormatter) {
        try {
            return LocalDate.parse(texto, dateFormatter);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(texto, dateTimeFormatter).toLocalDate();
            } catch (DateTimeParseException ex) {
                throw new RuntimeException("Formato de fecha inválido: " + texto);
            }
        }
    }
}

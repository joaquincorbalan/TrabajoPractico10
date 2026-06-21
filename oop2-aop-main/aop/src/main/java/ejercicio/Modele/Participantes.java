package ejercicio.Modele;

public class Participantes {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String idConcurso;

    public Participantes(String nombre, String apellido, String telefono, String email, String dni, String idConcurso) {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("Debe cargar un nombre");
        }
        if (apellido.isEmpty()) {
            throw new IllegalArgumentException("apellido no puede ser vacio");
        }
        if (dni.isEmpty()) {
            throw new IllegalArgumentException("dni no puede ser vacio");
        }
        if (!checkEmail(email)) {
            throw new IllegalArgumentException(
                    "email debe ser válido");
        }
        if (!checkPhone(telefono)) {
            throw new IllegalArgumentException(
                    "El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");

        }
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.idConcurso = idConcurso;
    }


    private boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean checkPhone(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getIdConcurso() {
        return idConcurso;
    }
}
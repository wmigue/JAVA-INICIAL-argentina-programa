import java.util.ArrayList;

public class Estudiante {
    private int legajo;
    private String nombreApellido;
    private ArrayList<String> materiasAprobadas;

    public Estudiante(int legajo, String nombreApellido) {
        this.legajo = legajo;
        this.nombreApellido = nombreApellido;
        this.materiasAprobadas = new ArrayList<>();
    }

    public int getLegajo() {
        return legajo;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public ArrayList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

        public void agregarMateriaAprobada(String materia) {
            materiasAprobadas.add(materia);
        }

    @Override
    public String toString() {
        StringBuilder materias = new StringBuilder();
        for (String materia : materiasAprobadas) {
            materias.append(materia).append(", ");
        }
        return legajo + " - " + nombreApellido + " - " + materias.toString().trim();
    }
}

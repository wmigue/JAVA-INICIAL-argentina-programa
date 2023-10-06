import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Estudiante> estudiantesHashMap = new HashMap<>();

        //fileReader: lee caracter por caracter.
        //bufferreader: lee linea por linea al pasarle un objeto filereader.
        try (BufferedReader br = new BufferedReader(new FileReader("datos/aprobaciones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int legajo = Integer.parseInt(partes[0]);
                String nombreApellido = partes[1];
                String materia = partes[2];
                //si no existe el alumno en el hashmap, lo agregamos, sino solo le agregamos otra materia aprobada al arrayList interno de la clase estudiante.
                if (estudiantesHashMap.get(legajo) == null) {
                    Estudiante estudianteTemp = new Estudiante(legajo, nombreApellido);
                    estudianteTemp.agregarMateriaAprobada(materia);
                    estudiantesHashMap.put(legajo, estudianteTemp);
                } else {
                    estudiantesHashMap.get(legajo).agregarMateriaAprobada(materia);
                }
            }
        } catch (IOException x) {
            //si hay error, nos muestra ubicación exacta en el código donde tira la excepción.
            x.printStackTrace();
        }


        String entrada;
        do {
            System.out.print("Ingresa un número de LEGAJO (o escribe 'todos' para ver todos los aprobados, o escribe 'salir' para salir del programa): ");
            entrada = scanner.nextLine().trim();
            if ("salir".equals(entrada)) {
                break;
            } else if ("todos".equals(entrada)) {
                verTodosLosAprobados(estudiantesHashMap);
            } else  {
                try {
                    if(estudiantesHashMap.get(Integer.parseInt(entrada)) != null){
                        verAprobadoPorLegajo(estudiantesHashMap, entrada);
                    }
                }catch (NumberFormatException e) {
                    System.out.printf("Entrada [ %s ] no válida. Debes ingresar un número de legajo válido o 'todos' para ver todos los aprobados. %n", entrada.toUpperCase());
                }
            }
        } while (true);
        scanner.close();

    }


    public static void verTodosLosAprobados(Map<Integer, Estudiante> estudiantesHashMap) {
        System.out.printf("%n::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
        for (Map.Entry<Integer, Estudiante> entry : estudiantesHashMap.entrySet()) {
            //Integer legajo = entry.getKey();
            Estudiante estudiante = entry.getValue();
            String materiasAprobadasTemp = "";
            for (String x : estudiante.getMateriasAprobadas()) {
                materiasAprobadasTemp += x + ", ";
            }
            System.out.printf("%n LEGAJO: " + estudiante.getLegajo() + ", ALUMNO:" + estudiante.getNombreApellido() + ", APROBADAS: " + Utilidades.quitarUltimoCaracter(materiasAprobadasTemp) );
        }
        System.out.printf("%n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::%n%n");
    }

    public static void verAprobadoPorLegajo(Map<Integer, Estudiante> estudiantesHashMap, String legajo){
        Estudiante estudiante = estudiantesHashMap.get(Integer.parseInt(legajo));
        String materiasAprobadasTemp = "";
        for (String x : estudiante.getMateriasAprobadas()) {
            materiasAprobadasTemp += x + ", ";
        }
        System.out.printf("%n::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
        System.out.printf("%n LEGAJO: " + estudiante.getLegajo() + ", ALUMNO:" + estudiante.getNombreApellido() + ", APROBADAS: " + Utilidades.quitarUltimoCaracter(materiasAprobadasTemp) + "%n");
        System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: %n%n%n");
    }

}

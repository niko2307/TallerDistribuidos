package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Obtiene el registro RMI
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

            // Obtiene la referencia al objeto remoto del servidor
            estudiante stub = (estudiante) registry.lookup("EstudianteServer");

            // Crear un Scanner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            // Ciclo del menú
            boolean salir = false;
            while (!salir) {
                // Mostrar opciones del menú
                System.out.println("\n-- Menú --");
                System.out.println("1. Obtener nombre de estudiante por ID");
                System.out.println("2. Obtener grupo por nombre de estudiante");
                System.out.println("3. Obtener promedio de notas de todos los estudiantes");
                System.out.println("4. Salir");

                // Leer opción del usuario
                System.out.print("Ingrese el número de la opción deseada: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                // Procesar opción
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el ID del estudiante: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea
                        String nombreEstudiante = stub.getNombreestu(id);
                        if (nombreEstudiante != null) {
                            System.out.println("El nombre del estudiante con ID " + id + " es: " + nombreEstudiante);
                        } else {
                            System.out.println("No se encontró ningún estudiante con el ID proporcionado.");
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese el ID del estudiante: ");
                        int ide = scanner.nextInt();
                        String grupoEstudiante = stub.getgrupo(ide);
                        if (grupoEstudiante != null) {
                            System.out.println("El grupo del estudiante con ID " + ide + " es: " + grupoEstudiante);
                        } else {
                            System.out.println("No se encontró ningún estudiante con el ID proporcionado.");
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese el ID o nombre del estudiante: ");
                        String nombreOId = scanner.nextLine();
                        double promedio = stub.getpromedio(nombreOId);
                        if (promedio != 0) {
                            System.out.println("El promedio de notas del estudiante " + nombreOId + " es: " + promedio);
                        } else {
                            System.out.println("No se encontró ningún estudiante con el ID o nombre proporcionado.");
                        }
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número válido.");
                }
            }

            // Cierre del Scanner
            scanner.close();

            System.out.println("¡Gracias por usar el cliente!");

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}

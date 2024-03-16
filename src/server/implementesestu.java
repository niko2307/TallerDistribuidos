package server;

import java.rmi.RemoteException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class implementesestu implements estudiante{
   public String nombre;
   public int id;
    public double notaTaller1;
    public double notaTaller2;
    public String grupo;
   public Map<Integer, implementesestu> mapaEstudiantes;

    public implementesestu(String nombre, int id, double notaTaller1, double notaTaller2, String grupo) {
        this.nombre = nombre;
        this.id = id;
        this.notaTaller1 = notaTaller1;
        this.notaTaller2 = notaTaller2;
        this.grupo = grupo;
    }
    public implementesestu() throws IOException {
        mapaEstudiantes = leerArchivo("archivo.txt");
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNotaTaller1() {
        return notaTaller1;
    }

    public void setNotaTaller1(double notaTaller1) {
        this.notaTaller1 = notaTaller1;
    }

    public double getNotaTaller2() {
        return notaTaller2;
    }

    public void setNotaTaller2(double notaTaller2) {
        this.notaTaller2 = notaTaller2;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }


    @Override
    public String getNombreestu(int id) throws RemoteException {
        System.out.println("Buscando estudiante con ID: " + id);

        // Verificar el contenido del mapa
        System.out.println("Contenido del mapa de estudiantes: " + mapaEstudiantes);

        // Verificar los IDs almacenados en el mapa
        System.out.println("IDs almacenados en el mapa: " + mapaEstudiantes.keySet());

        implementesestu estudiante = mapaEstudiantes.get(id);
        if (estudiante != null) {
            String info = "Nombre: " + estudiante.getNombre() + ", Grupo: " + estudiante.getGrupo() +
                    ", Nota Taller 1: " + estudiante.getNotaTaller1() + ", Nota Taller 2: " + estudiante.getNotaTaller2();
            System.out.println("Información del estudiante encontrado: " + info);
            return info;
        }
        System.out.println("Estudiante no encontrado con ID: " + id);
        return null;
    }


    @Override
    public String getgrupo(int id) throws RemoteException {
        for (implementesestu estudiante : mapaEstudiantes.values()) {
            if (estudiante.getId() == id) {
                return estudiante.getGrupo();
            }
        }
        return null;

    }
        @Override
        public double getpromedio(String nombreOId) throws RemoteException {
            double sumaNotas = 0;
            int contadorEstudiantes = 0;

            for (implementesestu estudiante : mapaEstudiantes.values()) {
                if (estudiante.getNombre().equals(nombreOId) || String.valueOf(estudiante.getId()).equals(nombreOId)) {
                    sumaNotas += (estudiante.getNotaTaller1() + estudiante.getNotaTaller2()) / 2.0;
                    contadorEstudiantes++;
                }
            }

            if (contadorEstudiantes > 0) {
                return sumaNotas / contadorEstudiantes;
            } else {
                return 0; // Retornar 0 si no se encontró ningún estudiante con el nombre o ID proporcionado
            }
        }



    // Método para leer el archivo y almacenar los datos en un mapa
        public Map<Integer, implementesestu> leerArchivo(String archivo) throws IOException {
            Map<Integer, implementesestu> mapaEstudiantes = new HashMap<>();


            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;

                // Leer la primera línea para ignorarla (encabezados)
                br.readLine();

                while ((linea = br.readLine()) != null) {


                    String[] datos = linea.split("\\|");
                    if (datos.length == 5) {
                        String[] nombres = datos[2].split(",");
                        if (nombres.length == 2) {
                            int id = Integer.parseInt(datos[1].trim());
                            String nombre = nombres[1].trim() + " " + nombres[0].trim(); // Concatenar nombre y apellido
                            double notaTaller1 = Double.parseDouble(datos[3].trim());
                            double notaTaller2 = Double.parseDouble(datos[4].trim());
                            String grupo = datos[0].trim();

                            implementesestu estudiante = new implementesestu(nombre, id, notaTaller1, notaTaller2, grupo);
                            mapaEstudiantes.put(id, estudiante);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw e; // Relanzar la excepción para que el llamador la maneje
            }

            return mapaEstudiantes;
        }



}

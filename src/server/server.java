package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class server {
    public static void main(String [] args){
        try{
            implementesestu implementacion = new implementesestu();

            // Exportamos el objeto remoto
            estudiante stub = (estudiante) UnicastRemoteObject.exportObject(implementacion, 0);

            // Creamos o obtenemos el registro RMI en la dirección IP 127.0.0.1 y el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099, null, null);

            // Enlazamos el stub del objeto remoto en el registro
            registry.rebind("EstudianteServer", stub);

            System.out.println("Servidor listo...");


        }catch(Exception e){
            System.out.println("error en el servidor" + e);
        }
    }

}

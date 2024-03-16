package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface estudiante extends Remote {
    //metodos para obtener lo necesario del estudiante
    public String getNombreestu(int id) throws RemoteException;
    public String getgrupo(int id) throws RemoteException;

    public double getpromedio(String nombreOId) throws RemoteException;


}

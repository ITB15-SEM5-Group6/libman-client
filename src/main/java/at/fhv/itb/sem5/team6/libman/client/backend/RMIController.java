package at.fhv.itb.sem5.team6.libman.client.backend;

import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibrary;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibraryFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIController {

    static String host = "localhost";
    static int port = 1099;
    private static RMIController instance = null;


    protected RMIController() {
    }

    public static RMIController getInstance() {
        if (instance == null) {
            return new RMIController();
        } else {
            return instance;
        }
    }

    public void doConnection(String username, String password) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        ILibraryFactory stubFactory = (ILibraryFactory) registry.lookup("LibraryFactory");
        ILibrary stub = stubFactory.create(username, password);
        ClientController.setLibrary(stub);
    }


}

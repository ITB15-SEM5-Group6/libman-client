package at.fhv.itb.sem5.team6.libman.client;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.ClientGUI;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibrary;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibraryFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;

public class Client extends Application {

    static String host = "localhost";
    static int port = 1099;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.UK);

        // connect rmi
        Registry registry = LocateRegistry.getRegistry(host, port);
        ILibraryFactory stubFactory = (ILibraryFactory) registry.lookup("LibraryFactory");
        ILibrary stub = stubFactory.create();
        ClientController.setLibrary(stub);

        // start gui
        ClientGUI gui = new ClientGUI();
        gui.loadGUI(primaryStage);
    }

    public static void main(String[] args) {
          launch(args);
    }
}
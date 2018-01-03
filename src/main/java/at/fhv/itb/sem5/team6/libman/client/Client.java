package at.fhv.itb.sem5.team6.libman.client;

import at.fhv.itb.sem5.team6.libman.client.backend.RMIController;
import at.fhv.itb.sem5.team6.libman.client.presentation.ClientGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main(String[] args) {
        if (args.length > 0) {
            RMIController.setHost(args[0]);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // start gui
        ClientGUI gui = new ClientGUI(primaryStage);
        gui.showLogin(new Stage());
    }
}
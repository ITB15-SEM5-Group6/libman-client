package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Christina on 31.10.2017.
 */
public class ClientGUI {
    public static Scene scene;
    public static Stage primaryStage;

    public void showLogin(Stage primaryStage) throws Exception {
        primaryStage.setTitle("LIBMAN Login");

        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Login.fxml"));
        Parent parent = loader.load();
        scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        primaryStage.show();
        this.primaryStage = primaryStage;
    }

    public void loadMainFrame(Stage primaryStage) throws IOException{
        primaryStage.setTitle("LIBMAN");
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(t -> {
            //closeLIBMAN();
        });

        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/MainFrame.fxml"));
        Parent parent = loader.load();
        scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        primaryStage.show();
        this.primaryStage = primaryStage;

    }

    public void loadSearchForGuest(Stage primaryStage) throws IOException{
        primaryStage.setTitle("LIBMAN");
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(t -> {
            //closeLIBMAN();
        });

        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Search.fxml"));
        Parent parent = loader.load();
        scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        primaryStage.setFullScreen(true);
        primaryStage.show();
        this.primaryStage = primaryStage;

    }
}

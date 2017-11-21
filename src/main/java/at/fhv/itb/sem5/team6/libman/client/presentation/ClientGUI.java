package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.application.Platform;
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
    public static Scene loginScene;
    public static Stage primaryStage;
    public static Stage loginStage;

    public ClientGUI(Stage stage) {
        this.primaryStage = stage;
    }

    public ClientGUI() {
    }

    public static void closeLIBMAN() {
        //close Application correctly
        Platform.exit();
        System.exit(0);
    }


    public void loadGUI(Stage primaryStage) throws IOException{
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
    }

    public static void changeUser(Stage stage) throws IOException {
        primaryStage.close();
        stage.setTitle("LIBMAN Login");

        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Login.fxml"));
        Parent parent = loader.load();
        loginScene = new Scene(parent);
        stage.setScene(loginScene);
        stage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        stage.show();
        loginStage = stage;
    }

    public void showLogin(Stage loginStage) throws Exception {
        loginStage.setTitle("LIBMAN Login");

        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Login.fxml"));
        Parent parent = loader.load();
        loginScene = new Scene(parent);
        loginStage.setScene(loginScene);
        loginStage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        loginStage.show();
        this.loginStage = loginStage;
    }

    public void loadSearchForGuest() throws IOException {
        loginStage.close();
        primaryStage.setTitle("LIBMAN");
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(t -> {
            closeLIBMAN();
        });

        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Search.fxml"));
        Parent parent = loader.load();
        scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        primaryStage.show();
    }
}
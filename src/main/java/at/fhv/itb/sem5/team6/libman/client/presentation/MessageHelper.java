package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.scene.control.Alert;

/**
 * Created by Christina on 13.11.2017.
 */
public class MessageHelper {

    public static void showConfirmationMessage(String confirmation){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(confirmation);
            alert.showAndWait();

    }

    public static void showErrorAlertMessage(String errormessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(errormessage);
        alert.showAndWait();
    }

    public static void showInformationMessage(String infomessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(infomessage);
        alert.showAndWait();
    }

}

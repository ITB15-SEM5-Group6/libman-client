package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.rmi.RemoteException;


public class MessageController {


    @FXML
    private TextArea messageField;

    private String previousMessage;

    @FXML
    void initialize() {
        previousMessage = "";
        messageField.setText("");
    }

    @FXML
    void getNextMessage(ActionEvent event) {
        previousMessage = messageField.getText();
        messageField.clear();
        try {
            messageField.setText(ClientController.getInstance().getNextMessage());
        } catch (RemoteException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }

    @FXML
    void getPreviousMessage(ActionEvent event) {
        if (!previousMessage.isEmpty()) {
            messageField.setText(previousMessage);
        }
    }

}

package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class MessageController {


    @FXML
    private TextArea messageField;

    private String previousMessage;

    @FXML
    void initialize() {
        previousMessage = "";
        messageField.setText("JavaFX ist ein Framework zur Erstellung plattformübergreifender Java-Applikationen. \n Es ist eine Java-Spezifikation von Oracle und setzt sich zum Ziel, das professionelle Erstellen und Verteilen von interaktiven, \n multimedialen Inhalten und grafischen Benutzeroberflächen (GUIs) über sämtliche Java-Plattformen \n hinweg zu erleichtern.");
    }

    @FXML
    void getNextMessage(ActionEvent event) {
        previousMessage = messageField.getText();
        messageField.clear();
    }

    @FXML
    void getPreviousMessage(ActionEvent event) {
        if (!previousMessage.isEmpty()) {
            messageField.setText(previousMessage);
        }
    }

}

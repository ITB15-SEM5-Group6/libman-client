package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.RMIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.rmi.NotBoundException;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnGuest;

    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            if(password.getText() == null || password.getText().isEmpty()){
                btnLogin.setDisable(true);
            } else {
                btnLogin.setDisable(false);
            }
        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if(username.getText() == null || username.getText().isEmpty()){
                btnLogin.setDisable(true);
            } else {
                btnLogin.setDisable(false);
            }
        });
    }

    @FXML
    void continueAsGuest(ActionEvent event) {
        ClientGUI gui = new ClientGUI();
        try {
            RMIController.getInstance().doConnection(null, null);
            gui.loadSearchForGuest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void login() throws IOException, NotBoundException {
        RMIController.getInstance().doConnection(username.getText(), password.getText());
        ClientGUI gui = new ClientGUI();
        gui.loadGUI(ClientGUI.primaryStage);
    }

    //handles the Enter-Key-Button for faster login.
    @FXML
    public void handleEnterPressed(KeyEvent event) throws IOException, NotBoundException {
        if(event.getCode() == KeyCode.ENTER){
            login();
        }
    }
}

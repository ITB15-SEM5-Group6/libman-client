package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Christina on 02.11.2017.
 */
public class MainFrameController {

    @FXML
    private AnchorPane splitPaneLeft;
    @FXML
    private Button searchButton;
    @FXML
    private Button customerButton;
    @FXML
    private Button lendingButton;
    @FXML
    private AnchorPane splitPaneRight;
    @FXML
    private MenuItem menuLogOut;
    @FXML
    private MenuItem menuChangeUser;
    @FXML
    private VBox vBoxMain;

    @FXML
    void openSearch(ActionEvent event) {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Search.fxml"));
        try {
            Parent parent = loader.load();
            splitPaneRight.getChildren().add(parent);
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }

    @FXML
    void changeUser(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void openAddCustomer(ActionEvent event) {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Customer.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
            splitPaneRight.getChildren().add(parent);
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }
    @FXML
    void openCustomerSearch(ActionEvent event) throws IOException {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/CustomerSearch.fxml"));
        Parent parent = loader.load();
        splitPaneRight.getChildren().add(parent);
    }
}


package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane splitPaneRight;


    @FXML
    void openSearch(ActionEvent event) throws IOException {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Search.fxml"));
        Parent parent = loader.load();
        splitPaneRight.getChildren().add(parent);

    }


    @FXML
    void openAddCustomer(ActionEvent event) throws IOException {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Customer.fxml"));
        Parent parent = loader.load();
        splitPaneRight.getChildren().add(parent);

    }

}

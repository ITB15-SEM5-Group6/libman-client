package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

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

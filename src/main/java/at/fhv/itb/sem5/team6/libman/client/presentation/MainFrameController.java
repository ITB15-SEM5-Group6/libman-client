package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.enums.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by Christina on 02.11.2017.
 */
public class MainFrameController {

    @FXML
    public AnchorPane splitPaneLeft;
    @FXML
    public  AnchorPane  splitPaneRight;

    public  static AnchorPane  splitPaneRightStatic;
    @FXML
    private Button searchButton;
    @FXML
    private Button customerButton;
    @FXML
    private Button lendingButton;
    @FXML
    private MenuItem menuLogOut;
    @FXML
    private MenuItem menuChangeUser;
    @FXML
    private VBox vBoxMain;
    @FXML
    private MenuBar menuBar;

    @FXML
    public void initialize() {
        splitPaneRightStatic = splitPaneRight;
        try {
            if (ClientController.getInstance().getUserRole().equals(UserRole.GUEST)) {
                splitPaneLeft.setVisible(false);
                splitPaneLeft.setMaxWidth(0.0);
                menuBar.setVisible(false);
            }
        } catch (RemoteException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
        openSearch();
    }


    @FXML
    void openSearch() {
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
    void changeUser(ActionEvent event) throws IOException {
        ClientGUI.changeUser(new Stage());
    }

    @FXML
    void logout(ActionEvent event) {
        ClientGUI.closeLIBMAN();
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
    void openCustomerSearch(ActionEvent event) {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/CustomerSearch.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
            splitPaneRight.getChildren().add(parent);
        } catch (IOException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }

    public static void setFXMLtoSplitPaneRight(Parent parent) {
        splitPaneRightStatic.getChildren().clear();
        splitPaneRightStatic.getChildren().add(parent);
    }

    @FXML
    void openMessageService(ActionEvent event) {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/Message.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
            splitPaneRight.getChildren().add(parent);
        } catch (IOException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }
}


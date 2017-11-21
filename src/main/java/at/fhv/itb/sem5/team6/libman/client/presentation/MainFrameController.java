package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Christina on 02.11.2017.
 */
public class MainFrameController {

    @FXML
    public static AnchorPane splitPaneLeft;
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
    public void initialize() {
        splitPaneRightStatic = splitPaneRight;
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
    void openCustomerSearch(ActionEvent event) throws IOException {
        splitPaneRight.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(ClientGUI.class.getResource("/views/CustomerSearch.fxml"));
        Parent parent = loader.load();
        splitPaneRight.getChildren().add(parent);
    }

    public static void setFXMLtoSplitPaneRight(Parent parent) {
        splitPaneRightStatic.getChildren().clear();
        splitPaneRightStatic.getChildren().add(parent);
    }
}


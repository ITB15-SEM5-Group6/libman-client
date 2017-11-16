package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class CustomerSearchController {

    private static CustomerDTO selectedCustomer;
    @FXML
    private GridPane gridPane;

    @FXML
    private Label headerLabelLending;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<CustomerSearchEntry> tableView;

    @FXML
    private TableColumn<CustomerSearchEntry, String> columnName;

    @FXML
    private TableColumn<CustomerSearchEntry, String> columnSurname;

    @FXML
    private TableColumn<CustomerSearchEntry, String> columnAddress;

    @FXML
    private TableColumn<CustomerSearchEntry, String> columnPhone;

    @FXML
    private TableColumn<CustomerSearchEntry, String> columnEmail;



    @FXML
    public void initialize() {
        columnName.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2
        columnSurname.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2
        columnAddress.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2
        columnPhone.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2
        columnEmail.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2

        columnName.setCellValueFactory(new PropertyValueFactory<CustomerSearchEntry, String>("customerName"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<CustomerSearchEntry, String>("customerSurname"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<CustomerSearchEntry, String>("customerAddress"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<CustomerSearchEntry, String>("customerPhone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<CustomerSearchEntry, String>("customerEmail"));
    }

    @FXML
    void search(ActionEvent event) {
        tableView.getItems().clear();
        String searchText = searchField.getText();
        ObservableList<CustomerSearchEntry> customerSearchEntries = FXCollections.observableArrayList();
        List<CustomerDTO> allCustomers = new LinkedList<>();

        try {
            allCustomers = ClientController.getInstance().getCustomers(searchText);
        } catch (RemoteException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }

        for (CustomerDTO customer : allCustomers) {
            customerSearchEntries.add(new CustomerSearchEntry(customer));
        }
        tableView.setItems(customerSearchEntries);
    }

    public static CustomerDTO getSelectedCustomer() {
        return selectedCustomer;
    }

    @FXML
    void clickItem(MouseEvent event) {
        if (tableView.getItems().size() > 0) {
            selectedCustomer = tableView.getSelectionModel().getSelectedItem().getCustomerDTO();
            if (event.getClickCount() == 2) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/DetailCustomerView.fxml"));
                    MainFrameController.setFXMLtoSplitPaneRight(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.rmi.RemoteException;

public class NewLendingController {

    @FXML
    private TextField textFieldSearchCustomer;

    @FXML
    private Button buttonSearchCustomer;

    @FXML
    private TableView<CustomerEntry> tableView;

    @FXML
    private Button buttonSaveLending;

    @FXML
    private Button buttonCancelLending;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    public void initialize() throws RemoteException {
        initColumns();

    }

    private void initColumns() {
        TableColumn<CustomerEntry, String> firstnameCol = new TableColumn("Firstname");
        firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        firstnameCol.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2

        TableColumn<CustomerEntry, String> lastnameCol = new TableColumn("Lastname");
        lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        lastnameCol.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2

        TableColumn<CustomerEntry, String> emailCol = new TableColumn("E-Mail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2

        TableColumn<CustomerEntry, String> phoneCol = new TableColumn("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneCol.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2

        TableColumn<CustomerEntry, String> addressCol = new TableColumn("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.prefWidthProperty().bind(tableView.widthProperty().divide(5)); // w * 1/2

        tableView.getColumns().addAll(firstnameCol, lastnameCol, emailCol, phoneCol, addressCol);
    }

    @FXML
    void clickItem(MouseEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void searchCustomer(ActionEvent event) {

    }
}

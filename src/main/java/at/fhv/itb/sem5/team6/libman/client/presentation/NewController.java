package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.CustomerEntry;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Christina on 14.11.2017.
 */
public abstract class NewController {

    public void searchCustomer(String searchText, TableView tableView) throws RemoteException {

        ObservableList<CustomerEntry> customerEntries = FXCollections.observableArrayList();
        List<CustomerDTO> allCustomer = new LinkedList<>();
        if (searchText.length() > 0) {
            allCustomer = ClientController.getInstance().getCustomers(searchText);
        } else {
            allCustomer = ClientController.getInstance().getAllCustomers();
        }

        for (CustomerDTO customerDTO : allCustomer) {
            customerEntries.addAll(new CustomerEntry(customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(), customerDTO.getPhoneNumber(), customerDTO.getAddress(), customerDTO));
        }
        tableView.setItems(customerEntries);
    }

    public void initColumns(TableView tableView) {
        TableColumn<CustomerEntry, String> firstnameCol = new TableColumn("Firstname");
        firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstnameCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        TableColumn<CustomerEntry, String> lastnameCol = new TableColumn("Lastname");
        lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastnameCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        TableColumn<CustomerEntry, String> emailCol = new TableColumn("E-Mail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        TableColumn<CustomerEntry, String> phoneCol = new TableColumn("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        TableColumn<CustomerEntry, String> addressCol = new TableColumn("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        tableView.getColumns().addAll(firstnameCol, lastnameCol, emailCol, phoneCol, addressCol);
    }
}

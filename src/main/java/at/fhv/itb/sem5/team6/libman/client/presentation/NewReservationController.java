package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.CustomerEntry;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.rmi.RemoteException;

/**
 * Created by Christina on 13.11.2017.
 */
public class NewReservationController {
    @FXML
    private TextField textFieldSearchMedia;

    @FXML
    private Button buttonSearchCustomer;

    @FXML
    private TableView<CustomerEntry> tableView;

    @FXML
    private Button buttonSaveReservation;

    @FXML
    private Button buttonCancelReservation;

    @FXML
    public void initialize() throws RemoteException {
        //initColumns(tableView);
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void clickItem(MouseEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
        try {
            String searchText = textFieldSearchMedia.getText();
            CustomerDTO customerDTO = tableView.getSelectionModel().getSelectedItem().getCustomerDTO();
            if (customerDTO != null) {
                ClientController.getInstance().reserve(DetailMediaViewController.mediaDTO.getId(), customerDTO.getId());
                DetailMediaViewController.detailStage.close();
                MessageHelper.showInformationMessage("New Reservation saved!");
            }
        }catch(Exception e) {
            MessageHelper.showErrorAlertMessage("Reservation could not be saved!");
        }
    }

    @FXML
    void searchCustomer(ActionEvent event) {
        try {
            String searchText = textFieldSearchMedia.getText();
            //searchCustomer(searchText, tableView);
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }
}

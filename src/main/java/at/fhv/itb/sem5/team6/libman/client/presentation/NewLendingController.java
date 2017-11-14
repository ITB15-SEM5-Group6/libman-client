package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.rmi.RemoteException;


public class NewLendingController extends NewController {

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

    private MediaDTO mediaDTO;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    public void initialize() throws RemoteException {
        initColumns(tableView);
    }



    @FXML
    void clickItem(MouseEvent event) {
    }

    @FXML
    void save(ActionEvent event) throws RemoteException {
        CustomerDTO customerDTO = tableView.getSelectionModel().getSelectedItem().getCustomerDTO();

        if(customerDTO != null) {
            if(ClientController.getInstance().lendPhysicalMedia(DetailMediaViewController.getCurrentSelectedPhysicalMedia(), customerDTO) != null ) {
                DetailMediaViewController.detailStage.close();
                MessageHelper.showConfirmationMessage("New Lending saved!");
            } else {
                MessageHelper.showErrorAlertMessage("Lending could not be saved!");
            }

        }
    }

    @FXML
    void searchCustomer(ActionEvent event) throws RemoteException {
        String searchText = textFieldSearchCustomer.getText();
        searchCustomer(searchText, tableView);

    }

}

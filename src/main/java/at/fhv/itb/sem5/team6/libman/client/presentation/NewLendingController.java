package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.CustomerEntry;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


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
    public void initialize() {
        initColumns(tableView);
    }



    @FXML
    void clickItem(MouseEvent event) {
    }

    @FXML
    void save(ActionEvent event) {
        /*
        CustomerDTO customerDTO = tableView.getSelectionModel().getSelectedItem().getCustomerDTO();

        if(customerDTO != null) {
            try {
                LendingDTO lending = ClientController.getInstance().lendPhysicalMedia(DetailMediaViewController.getCurrentSelectedPhysicalMedia().getId(), customerDTO.getId());
                DetailMediaViewController.detailStage.close();
                MessageHelper.showInformationMessage("New Lending saved!");
                //DetailMediaViewController.loadTableViewWithPhysicalMediaDTOs();
            } catch (Exception e) {
                MessageHelper.showErrorAlertMessage(e.getMessage());
            }
        }
        */
    }

    @FXML
    void searchCustomer(ActionEvent event){
        try {
            String searchText = textFieldSearchCustomer.getText();
            searchCustomer(searchText, tableView);
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }
}
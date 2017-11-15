package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetailCustomerViewController {

    private static CustomerDTO customerDTO;
    private static MediaDTO mediaDTO;
    private static PhysicalMediaDTO physicalMediaDTO;

    @FXML
    private Label customerLabel;

    @FXML
    private Label labelSurname;

    @FXML
    private Label labelName;

    @FXML
    private Label labelEMail;

    @FXML
    private Label labelAdress;

    @FXML
    private Label labelPhone;

    @FXML
    private Label lableIban;

    @FXML
    private Label lableBIC;

    @FXML
    private Button buttonReserve;

    @FXML
    private Button buttonLend;

    @FXML
    private TableView<LendingEntry> tableViewLendings;

    @FXML
    private TableColumn<LendingEntry,String> lendingTitleColumn;

    @FXML
    private TableColumn<LendingEntry,String> LendingMediaTypeColumn;

    @FXML
    private TableColumn<LendingEntry,String> LendingLendDateColumn;

    @FXML
    private TableColumn<LendingEntry,String> LendingIndexColumn;

    @FXML
    private TableView<ReservationEntry> tableViewReservation;

    @FXML
    private TableColumn<ReservationEntry, String> ReservationTitleColumn;

    @FXML
    private TableColumn<ReservationEntry, String> ReservationDateColumn;

    public void initialize() {
        initColumns();
        CustomerDTO customer = CustomerSearchController.getSelectedCustomer();
        customerLabel.setText(customer.getLastName());

        labelSurname.setText(customer.getLastName());
        labelName.setText(customer.getFirstName());
        labelAdress.setText(customer.getAddress());
        labelEMail.setText(customer.getEmail());
        labelPhone.setText(customer.getPhoneNumber());
        lableIban.setText(customer.getIban());
        lableBIC.setText(customer.getBic());

    }

    private void initColumns(){

        lendingTitleColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(4));
        LendingMediaTypeColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(4));
        LendingIndexColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(4));
        LendingLendDateColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(4));

        lendingTitleColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("title"));
        LendingLendDateColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("lendingDate"));
        LendingIndexColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("index"));
        LendingMediaTypeColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("mediaType"));


        ReservationDateColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));
        ReservationTitleColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));

        ReservationTitleColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("lendingDate"));
        ReservationDateColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("lendingDate"));





    }


}

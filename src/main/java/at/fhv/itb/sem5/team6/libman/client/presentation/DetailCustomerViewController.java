package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class DetailCustomerViewController {

    private static CustomerDTO customerDTO;
    private static MediaDTO mediaDTO;
    private static PhysicalMediaDTO physicalMediaDTO;

    @FXML
    private Label customerLabel;

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

    @FXML
    TableColumn<ReservationEntry, Boolean> RemoveLendingColumn = new TableColumn<>();

    public void initialize() throws RemoteException {
        initColumns();
        customerDTO = CustomerSearchController.getSelectedCustomer();
        customerLabel.setText(customerDTO.getFirstName() + " " + customerDTO.getLastName());
        labelAdress.setText(customerDTO.getAddress());
        labelEMail.setText(customerDTO.getEmail());
        labelPhone.setText(customerDTO.getPhoneNumber());
        lableIban.setText(customerDTO.getIban());
        lableBIC.setText(customerDTO.getBic());

        RemoveLendingColumn.setSortable(false);

        initTableValues();
    }

    private void initColumns(){

        lendingTitleColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(5));
        LendingMediaTypeColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(5));
        LendingIndexColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(5));
        LendingLendDateColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(5));
        RemoveLendingColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(5));

        lendingTitleColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("title"));
        LendingLendDateColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("lendingDate"));
        LendingIndexColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("index"));
        LendingMediaTypeColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("mediaType"));

        ReservationDateColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));
        ReservationTitleColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));
        ReservationTitleColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("media"));
        ReservationDateColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("date"));
    }

    private void initTableValues(){
        initLending();
        initReservations();
    }

    private void initReservations() {
        tableViewReservation.getItems().clear();
        ObservableList<ReservationEntry> reservationEntries = FXCollections.observableArrayList();
        List<ReservationDTO> allReservations = new LinkedList<>();

        try {
            allReservations = ClientController.getInstance().getAllReservations(customerDTO.getId());
        } catch (RemoteException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }

        for (ReservationDTO reservation : allReservations) {
            reservationEntries.add(new ReservationEntry(reservation.getMedia().getTitle(),reservation.getMedia().getType().toString(), customerDTO, reservation.getMedia(), reservation));
        }

        tableViewReservation.setItems(reservationEntries);
    }

    private void initLending() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        tableViewLendings.getItems().clear();
        ObservableList<LendingEntry> lendingEntries = FXCollections.observableArrayList();
        List<LendingDTO> allLendings = new LinkedList<>();

        try {
            allLendings = ClientController.getInstance().getAllLendings(customerDTO.getId());
        } catch (RemoteException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }

        for (LendingDTO lending : allLendings) {
            lendingEntries.add(new LendingEntry(lending.getPhysicalMedia().getMedia().getTitle(), lending.getPhysicalMedia().getMedia().getType().toString(), lending.getPhysicalMedia().getIndex(), sdf.format(lending.getLendDate()), customerDTO, lending.getPhysicalMedia(), lending));
        }
        tableViewLendings.setItems(lendingEntries);
    }

    @FXML
    void extend(ActionEvent event) {
        LendingDTO lendingDTO = tableViewLendings.getSelectionModel().getSelectedItem().getLendingDTO();
        if(lendingDTO != null) {
            try {
                if(ClientController.getInstance().extendLending(lendingDTO.getId()) != null) {
                    MessageHelper.showConfirmationMessage("Extention successful!");
                }
            } catch (RemoteException e) {
               MessageHelper.showConfirmationMessage(e.getMessage());
            }
        } else {
            MessageHelper.showErrorAlertMessage("No lending object selected!");
        }
    }

    @FXML
    void returnLending(ActionEvent event) {
        LendingDTO lendingDTO = tableViewLendings.getSelectionModel().getSelectedItem().getLendingDTO();
        if (lendingDTO != null && lendingDTO.getState().equals(LendingState.LENT)) {
            try {
                ClientController.getInstance().returnLending(lendingDTO.getId());
            } catch (RemoteException e) {
                MessageHelper.showConfirmationMessage(e.getMessage());
            }
        } else {
            MessageHelper.showErrorAlertMessage("No lending object selected or Lending is already returned!");
        }
        initLending();
    }

}

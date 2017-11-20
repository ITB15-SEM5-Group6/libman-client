package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButtons.RemoveLendingCell;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.rmi.RemoteException;
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
    private TableColumn<LendingEntry, String> LendingStateColumn;

    @FXML
    TableColumn<LendingEntry, Boolean> RemoveLendingColumn = new TableColumn<LendingEntry, Boolean>();

    public void initialize() throws RemoteException {
        initColumns();

        customerDTO = CustomerSearchController.getSelectedCustomer();

        customerLabel.setText(customerDTO.getFirstName() + " " + customerDTO.getLastName());
        labelAdress.setText(customerDTO.getAddress());
        labelEMail.setText(customerDTO.getEmail());
        labelPhone.setText(customerDTO.getPhoneNumber());
        lableIban.setText(customerDTO.getIban());
        lableBIC.setText(customerDTO.getBic());

        initTableValues();
    }

    private void initColumns(){

        lendingTitleColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(6));
        LendingMediaTypeColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(6));
        LendingIndexColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(6));
        LendingLendDateColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(6));
        LendingStateColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(6));
        RemoveLendingColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(6));

        lendingTitleColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("title"));
        LendingMediaTypeColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("mediaType"));
        LendingIndexColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("index"));
        LendingLendDateColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("lendingDate"));
        LendingStateColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("lendingState"));

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        RemoveLendingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LendingEntry, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<LendingEntry, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        RemoveLendingColumn.setSortable(false);

        ReservationDateColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));
        ReservationDateColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("date"));
        ReservationTitleColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));
        ReservationTitleColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("media"));
    }

    private void initTableValues(){
        initLendings();
        initReservations();
    }

    private void initLendings() {
        tableViewLendings.getItems().clear();

        ObservableList<LendingEntry> lendingEntries = FXCollections.observableArrayList();
        try {
            List<LendingDTO> allLendings = ClientController.getInstance().getAllLendings(customerDTO.getId());
            for (LendingDTO lending : allLendings) {
                lendingEntries.add(new LendingEntry(
                        lending.getPhysicalMedia().getMedia().getTitle(),
                        lending.getPhysicalMedia().getMedia().getType().toString(),
                        lending.getPhysicalMedia().getIndex(),
                        lending.getLendDate(),
                        lending.getState(),
                        customerDTO,
                        lending.getPhysicalMedia(),
                        lending));
            }
            tableViewLendings.setItems(lendingEntries);
        } catch (RemoteException e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }

        // create a cell value factory with an add button for each row in the table.
        RemoveLendingColumn.setCellFactory(new Callback<TableColumn<LendingEntry, Boolean>, TableCell<LendingEntry, Boolean>>() {
            @Override
            public TableCell<LendingEntry, Boolean> call(TableColumn<LendingEntry, Boolean> removeLendingBooleanTableColumn) {
                RemoveLendingCell cell = new RemoveLendingCell();

                cell.getRemoveLendingButton().setOnAction(actionEvent -> {
                    //is necessary because if you directly click the button without selecting the row first no row is selected
                    tableViewLendings.getSelectionModel().select(cell.getTableRow().getIndex());

                    LendingDTO lendingDTO = tableViewLendings.getSelectionModel().getSelectedItem().getLendingDTO();
                    if (lendingDTO != null && LendingState.LENT == lendingDTO.getState()) {
                        try {
                            ClientController.getInstance().returnLending(lendingDTO.getId());
                            initLendings();
                        } catch (RemoteException e) {
                            MessageHelper.showConfirmationMessage(e.getMessage());
                        }
                    } else {
                        MessageHelper.showErrorAlertMessage("No lending object selected or Lending is already returned!");
                    }
                });
                return cell;
            }
        });
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
        initLendings();
    }
}


package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButtons.ExtendLendingCell;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButtons.RemoveLendingCell;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    @FXML
    TableColumn<LendingEntry, Boolean> ExtendLendingColumn = new TableColumn<LendingEntry, Boolean>();

    public void initialize() throws RemoteException {
        initColumns();

        customerDTO = CustomerSearchController.getSelectedCustomer();

        customerLabel.setText(customerDTO.getFirstName() + " " + customerDTO.getLastName());
        labelAdress.setText(customerDTO.getAddress());
        labelEMail.setText(customerDTO.getEmail());
        labelPhone.setText(customerDTO.getPhoneNumber());
        lableIban.setText(customerDTO.getIban());
        lableBIC.setText(customerDTO.getBic());

        loadLendings();
        loadReservations();
    }

    private void initColumns(){

        //width:
        lendingTitleColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(7));
        LendingMediaTypeColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(7));
        LendingIndexColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(7));
        LendingLendDateColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(7));
        LendingStateColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(7));
        RemoveLendingColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(7));
        ExtendLendingColumn.prefWidthProperty().bind(tableViewLendings.widthProperty().divide(7));

        lendingTitleColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("title"));
        LendingMediaTypeColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("mediaType"));
        LendingIndexColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("index"));
        LendingLendDateColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("lendingDate"));
        LendingStateColumn.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("lendingState"));

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        RemoveLendingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LendingEntry, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<LendingEntry, Boolean> features) {
                boolean initialValue = features.getValue() != null && LendingState.LENT.toString().equals(features.getValue().getLendingState());
                return new SimpleBooleanProperty(initialValue);
            }
        });

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        ExtendLendingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LendingEntry, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<LendingEntry, Boolean> features) {
                boolean initialValue = features.getValue() != null && LendingState.LENT.toString().equals(features.getValue().getLendingState());
                return new SimpleBooleanProperty(initialValue);
            }
        });

        ReservationDateColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));
        ReservationDateColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("date"));
        ReservationTitleColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(2));
        ReservationTitleColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("media"));
    }

    private void loadLendings() {
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
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
        createDynamicRemoveLendingButton();
        createDynamicExtendLendingButton();
    }

    private void loadReservations() {
        tableViewReservation.getItems().clear();

        ObservableList<ReservationEntry> reservationEntries = FXCollections.observableArrayList();

        try {
            List<ReservationDTO> allReservations = ClientController.getInstance().getAllReservations(customerDTO.getId());
            for (ReservationDTO reservation : allReservations) {
                reservationEntries.add(new ReservationEntry(reservation.getMedia().getTitle(),reservation.getMedia().getType().toString(), customerDTO, reservation.getMedia(), reservation));
            }
            tableViewReservation.setItems(reservationEntries);
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }

    private void createDynamicRemoveLendingButton() {
        // create a cell value factory with an add button for each row in the table.
        RemoveLendingColumn.setCellFactory(new Callback<TableColumn<LendingEntry, Boolean>, TableCell<LendingEntry, Boolean>>() {
            @Override
            public TableCell<LendingEntry, Boolean> call(TableColumn<LendingEntry, Boolean> removeLendingBooleanTableColumn) {
                RemoveLendingCell cell = new RemoveLendingCell();

                cell.getRemoveLendingButton().setOnAction(actionEvent -> {
                    //is necessary because if you directly click the button without selecting the row first no row is selected
                    tableViewLendings.getSelectionModel().select(cell.getTableRow().getIndex());
                    try {
                        LendingDTO lendingDTO = tableViewLendings.getSelectionModel().getSelectedItem().getLendingDTO();
                        if (lendingDTO != null && LendingState.LENT == lendingDTO.getState()) {
                            ClientController.getInstance().returnLending(lendingDTO.getId());
                            loadLendings();
                        } else {
                            MessageHelper.showErrorAlertMessage("Lending is already returned!");
                        }
                    } catch (Exception e) {
                        MessageHelper.showErrorAlertMessage(e.getMessage());
                    }
                });
                return cell;
            }
        });
    }

    private void createDynamicExtendLendingButton() {
        // create a cell value factory with an add button for each row in the table.
        ExtendLendingColumn.setCellFactory(new Callback<TableColumn<LendingEntry, Boolean>, TableCell<LendingEntry, Boolean>>() {
            @Override
            public TableCell<LendingEntry, Boolean> call(TableColumn<LendingEntry, Boolean> extendLendingBooleanTableColumn) {
                ExtendLendingCell cell = new ExtendLendingCell();

                cell.getExtendLendingButton().setOnAction(actionEvent -> {
                    //is necessary because if you directly click the button without selecting the row first no row is selected
                    tableViewLendings.getSelectionModel().select(cell.getTableRow().getIndex());

                    try {
                        LendingDTO lendingDTO = tableViewLendings.getSelectionModel().getSelectedItem().getLendingDTO();
                        if (lendingDTO != null && LendingState.LENT == lendingDTO.getState()) {
                            ClientController.getInstance().extendLending(lendingDTO.getId());
                            MessageHelper.showConfirmationMessage("Extention successful!");
                            loadLendings();
                        } else {
                            MessageHelper.showErrorAlertMessage("Lending is already returned!");
                        }
                    } catch (Exception e) {
                        MessageHelper.showErrorAlertMessage(e.getMessage());
                    }
                });
                return cell;
            }
        });
    }
}


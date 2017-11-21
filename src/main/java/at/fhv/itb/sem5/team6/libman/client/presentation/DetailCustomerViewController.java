package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButton.ExtendLendingCell;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButton.LendReservationCell;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButton.RemoveLendingCell;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.LendingEntry;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.ReservationEntry;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

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
    private TableColumn<ReservationEntry, String> ReservationMediaTypeColumn;

    @FXML
    private TableColumn<ReservationEntry, Boolean> LendReservationColumn;

    @FXML
    private TableColumn<LendingEntry, String> LendingStateColumn;

    @FXML
    TableColumn<LendingEntry, Boolean> RemoveLendingColumn = new TableColumn<LendingEntry, Boolean>();

    @FXML
    TableColumn<LendingEntry, Boolean> ExtendLendingColumn = new TableColumn<LendingEntry, Boolean>();

    @FXML
    private Button NewLendingButton;

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

        ReservationTitleColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(4));
        ReservationDateColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(4));
        ReservationMediaTypeColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(4));
        LendReservationColumn.prefWidthProperty().bind(tableViewReservation.widthProperty().divide(4));

        ReservationTitleColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("title"));
        ReservationDateColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("date"));
        ReservationMediaTypeColumn.setCellValueFactory(new PropertyValueFactory<ReservationEntry, String>("mediaType"));

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        LendReservationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ReservationEntry, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ReservationEntry, Boolean> features) {
                boolean initialValue = features.getValue() != null;
                return new SimpleBooleanProperty(initialValue);
            }
        });
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
                reservationEntries.add(new ReservationEntry(
                                        reservation.getMedia().getTitle(),
                                        reservation.getMedia().getType().toString(),
                                        reservation.getDate(),
                                        customerDTO,
                                        reservation.getMedia(),
                                        reservation));
            }
            tableViewReservation.setItems(reservationEntries);
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
        createDynamicLendReservationButton();
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
                            MessageHelper.showInformationMessage("Extention successful!");
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

    private void createDynamicLendReservationButton() {
        // create a cell value factory with an add button for each row in the table.
        LendReservationColumn.setCellFactory(new Callback<TableColumn<ReservationEntry, Boolean>, TableCell<ReservationEntry, Boolean>>() {
            @Override
            public TableCell<ReservationEntry, Boolean> call(TableColumn<ReservationEntry, Boolean> extendLendingBooleanTableColumn) {
                LendReservationCell cell = new LendReservationCell();

                cell.getLendReservationButton().setOnAction(actionEvent -> {
                    //is necessary because if you directly click the button without selecting the row first no row is selected
                    tableViewReservation.getSelectionModel().select(cell.getTableRow().getIndex());

                    try {
                        ReservationEntry ee = tableViewReservation.getSelectionModel().getSelectedItem();
                        ReservationDTO reservationDTO = ee.getReservationDTO();

                        List<PhysicalMediaDTO> physicalAvailableMedias = ClientController.getInstance().findPhysicalMediasByMedia(reservationDTO.getMedia().getId()).stream().filter(x -> Availability.AVAILABLE.equals(x.getAvailability())).collect(Collectors.toList());
                        if(physicalAvailableMedias.isEmpty()) {
                            MessageHelper.showErrorAlertMessage("Dieses Medium ist dzt. leider nicht vorhanden.");
                        } else {
                            LendingDTO lending = ClientController.getInstance().lendPhysicalMedia(physicalAvailableMedias.get(0).getId(), reservationDTO.getCustomer().getId());
                            MessageHelper.showInformationMessage("Es wurde für " + lending.getPhysicalMedia().getMedia().getTitle() + " das Exemplar mit dem Index " + lending.getPhysicalMedia().getIndex() + " ausgehliehen.");
                        }
                        loadReservations();
                        loadLendings();
                    } catch (Exception e) {
                        MessageHelper.showErrorAlertMessage(e.getMessage());
                    }
                });
                return cell;
            }
        });
    }



    @FXML
    void openNewLendingDlg(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/NewLendingView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("New Lending");
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        //NewLendingController.detailStage = stage;
        stage.show();
    }

    @FXML
    void openNewReservationDlg(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/NewReservationView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("New Reservation");
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
        //NewReservationController.detailStage = stage;
        stage.show();
    }
}


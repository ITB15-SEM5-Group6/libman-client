package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButton.LendCell;
import at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButton.ReservationCell;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.SelectMediaEntry;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.rmi.RemoteException;
import java.util.List;


public class NewLendingController {

    static Stage detailStage;

    private CustomerDTO customerDTO;

    @FXML
    private TextField textFieldSearchPhysicalMedia;

    @FXML
    private Label titleLabel;

    @FXML
    private Button buttonSearchPhysicalMedia;

    @FXML
    private TableView<SelectMediaEntry> tableView;

    private TableColumn<SelectMediaEntry, String> titleCol;
    private TableColumn<SelectMediaEntry, String> mediaTypeCol;
    private TableColumn<SelectMediaEntry, String> indexCol;
    private TableColumn<SelectMediaEntry, String> availabilityCol;
    private TableColumn<SelectMediaEntry, Boolean> lendCol;
    private TableColumn<SelectMediaEntry, Boolean> reservateCol;

    @FXML
    public void initialize() throws RemoteException {
        initColumns(tableView);
    }

    public void initColumns(TableView tableView) {
        customerDTO = DetailCustomerViewController.getSelectedCustomer();
        titleLabel.setWrapText(true);
        titleLabel.setText("New Lending or Reservation for " + customerDTO.getFirstName() + " " + customerDTO.getLastName());

        titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.prefWidthProperty().bind(tableView.widthProperty().divide(6));

        mediaTypeCol = new TableColumn("Media Type");
        mediaTypeCol.setCellValueFactory(new PropertyValueFactory<>("mediaType"));
        mediaTypeCol.prefWidthProperty().bind(tableView.widthProperty().divide(6));

        indexCol = new TableColumn("Index");
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        indexCol.prefWidthProperty().bind(tableView.widthProperty().divide(6));

        availabilityCol = new TableColumn("Available");
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
        availabilityCol.prefWidthProperty().bind(tableView.widthProperty().divide(6));

        lendCol = new TableColumn();
        lendCol.prefWidthProperty().bind(tableView.widthProperty().divide(6));

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        lendCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SelectMediaEntry, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SelectMediaEntry, Boolean> features) {
                boolean initialValue = features.getValue() != null && Availability.AVAILABLE.toString().equals(features.getValue().getAvailability());
                return new SimpleBooleanProperty(initialValue);
            }
        });

        reservateCol = new TableColumn();
        reservateCol.prefWidthProperty().bind(tableView.widthProperty().divide(6));

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        reservateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SelectMediaEntry, Boolean>, ObservableValue<Boolean>>() {
            boolean mediaIsAvailable = false;

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SelectMediaEntry, Boolean> features) {
                try {
                    List<ReservationDTO> reservations = ClientController.getInstance().findReservationsByMedia(features.getValue().getMediaDTO().getId());
                    List<PhysicalMediaDTO> physicalMediaList = ClientController.getInstance().findPhysicalMediasByMedia(features.getValue().getMediaDTO().getId());
                    mediaIsAvailable = (physicalMediaList.stream().filter(x -> x.getAvailability().equals(Availability.AVAILABLE)).count() > reservations.size()) ? true : false;
                } catch (RemoteException e) {
                    MessageHelper.showErrorAlertMessage(e.getMessage());
                }
                return new SimpleBooleanProperty(mediaIsAvailable);
            }
        });

        tableView.getColumns().addAll(titleCol, mediaTypeCol, indexCol, availabilityCol, lendCol);
        createDynamicLendButton();
    }

    @FXML
    void handleEnterPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            searchPhysicalMedia();
        }
    }

    private void createDynamicLendButton() {
        // create a cell value factory with an add button for each row in the table.
        lendCol.setCellFactory(new Callback<TableColumn<SelectMediaEntry, Boolean>, TableCell<SelectMediaEntry, Boolean>>() {
            @Override
            public TableCell<SelectMediaEntry, Boolean> call(TableColumn<SelectMediaEntry, Boolean> LendBooleanTableColumn) {
                LendCell cell = new LendCell();

                cell.getLendButton().setOnAction(actionEvent -> {
                    //is necessary because if you directly click the button without selecting the row first no row is selected
                    tableView.getSelectionModel().select(cell.getTableRow().getIndex());
                    try {
                        PhysicalMediaDTO physicalMediaDTO = tableView.getSelectionModel().getSelectedItem().getPhysicalMediaDTO();
                        CustomerDTO customerDTO = tableView.getSelectionModel().getSelectedItem().getCustomerDTO();
                        ClientController.getInstance().lendPhysicalMedia(physicalMediaDTO.getId(), customerDTO.getId());
                        detailStage.close();
                    } catch (Exception e) {
                        MessageHelper.showErrorAlertMessage(e.getMessage());
                    }
                });
                return cell;
            }
        });
    }

    private void createDynamicReservateButton() {
        // create a cell value factory with an add button for each row in the table.
        reservateCol.setCellFactory(new Callback<TableColumn<SelectMediaEntry, Boolean>, TableCell<SelectMediaEntry, Boolean>>() {
            @Override
            public TableCell<SelectMediaEntry, Boolean> call(TableColumn<SelectMediaEntry, Boolean> ReservateBooleanTableColumn) {
                ReservationCell cell = new ReservationCell();

                cell.getReservateButton().setOnAction(actionEvent -> {
                    //is necessary because if you directly click the button without selecting the row first no row is selected
                    tableView.getSelectionModel().select(cell.getTableRow().getIndex());
                    try {
                        CustomerDTO customerDTO = tableView.getSelectionModel().getSelectedItem().getCustomerDTO();
                        ClientController.getInstance().reserve(tableView.getSelectionModel().getSelectedItem().getMediaDTO().getId(), customerDTO.getId());
                        detailStage.close();
                    } catch (Exception e) {
                        MessageHelper.showErrorAlertMessage(e.getMessage());
                    }
                });
                return cell;
            }
        });
    }

    @FXML
    void searchPhysicalMedia() {
        try {
            ObservableList<SelectMediaEntry> lendingEntries = FXCollections.observableArrayList();

            String searchText = textFieldSearchPhysicalMedia.getText();
            if (searchText.isEmpty()) {
                MessageHelper.showErrorAlertMessage("Please enter a search text!");
            } else {
                List<MediaDTO> mediaList = ClientController.getInstance().findAllMedia(searchText, Genre.ALL, MediaType.ALL, Availability.AVAILABLE);

                for (MediaDTO media : mediaList) {
                    List<PhysicalMediaDTO> physicalMediaList = ClientController.getInstance().findPhysicalMediasByMedia(media.getId());

                    for (PhysicalMediaDTO physicalMedia : physicalMediaList) {
                        lendingEntries.add(new SelectMediaEntry(media.getTitle(), media.getType(), physicalMedia.getIndex(), physicalMedia.getAvailability(), customerDTO, physicalMedia, media));
                    }
                }
                tableView.setItems(lendingEntries);
            }

        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }
}
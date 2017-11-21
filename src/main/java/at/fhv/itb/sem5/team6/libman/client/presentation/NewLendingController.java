package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.*;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.rmi.RemoteException;
import java.util.List;


public class NewLendingController {

    private CustomerDTO customerDTO;

    @FXML
    private TextField textFieldSearchPhysicalMedia;

    @FXML
    private Button buttonSearchPhysicalMedia;

    @FXML
    private TableView<SelectMediaEntry> tableView;

    @FXML
    public void initialize() throws RemoteException {
        initColumns(tableView);
    }

    public void initColumns(TableView tableView) {
        customerDTO = DetailCustomerViewController.getSelectedCustomer();

        TableColumn<SelectMediaEntry, String> titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        TableColumn<SelectMediaEntry, String> mediaTypeCol = new TableColumn("Media Type");
        mediaTypeCol.setCellValueFactory(new PropertyValueFactory<>("mediaType"));
        mediaTypeCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        TableColumn<SelectMediaEntry, String> index = new TableColumn("Index");
        index.setCellValueFactory(new PropertyValueFactory<>("index"));
        index.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        TableColumn<SelectMediaEntry, String> availabilityCol = new TableColumn("Available");
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
        availabilityCol.prefWidthProperty().bind(tableView.widthProperty().divide(5));

        tableView.getColumns().addAll(titleCol, mediaTypeCol, index, availabilityCol);
    }

    @FXML
    void searchPhysicalMedia() {
        try {
            ObservableList<SelectMediaEntry> lendingEntries = FXCollections.observableArrayList();

            String searchText = textFieldSearchPhysicalMedia.getText();

            if(searchText.isEmpty()) {
                MessageHelper.showErrorAlertMessage("Please enter a search text!");
            } else {
                List<MediaDTO> mediaList = ClientController.getInstance().findAllMedia(searchText, Genre.ALL, MediaType.ALL, Availability.AVAILABLE);

                for(MediaDTO media : mediaList) {
                    List<PhysicalMediaDTO> physicalMediaList = ClientController.getInstance().findPhysicalMediasByMedia(media.getId());

                    for(PhysicalMediaDTO physicalMedia : physicalMediaList) {
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
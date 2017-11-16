package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Christina on 07.11.2017.
 */
public class DetailMediaViewController {

    public static MediaDTO mediaDTO;
    static Stage detailStage;
    private static PhysicalMediaDTO selectedPhysicalMedia;
    @FXML
    private Label titleLabel;
    @FXML
    private TableView<PhysicalMediaEntry> tableView;
    @FXML
    private Label labelMediaType;
    @FXML
    private Label labelISBN;
    @FXML
    private Label labelAuthor;
    @FXML
    private Label labelPublisher;
    @FXML
    private Label lableReleaseDate;
    @FXML
    private Label lableTags;
    @FXML
    private Label lableGenre;
    @FXML
    private Button buttonReserve;
    @FXML
    private Button buttonLend;

    public static PhysicalMediaDTO getCurrentSelectedPhysicalMedia() {
        return selectedPhysicalMedia;
    }

    @FXML
    public void initialize() {
        initColumns();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        mediaDTO = SearchController.getCurrentSelectedMedia();
        titleLabel.setText(mediaDTO.getTitle());
        labelMediaType.setText(mediaDTO.getType().toString());
        labelISBN.setText(mediaDTO.getIsbn());
        labelAuthor.setText(mediaDTO.getAuthor());
        labelPublisher.setText(mediaDTO.getPublisher());
        lableTags.setText(mediaDTO.getTags());
        lableGenre.setText(mediaDTO.getGenre() != null ? mediaDTO.getGenre().toString() : " ");

        lableReleaseDate.setText(mediaDTO.getReleaseDate() != null ? sdf.format(mediaDTO.getReleaseDate()).toString() : " ");

        loadTableViewWithPhysicalMediaDTOs();
    }

    public void loadTableViewWithPhysicalMediaDTOs() {
        try {
            List<PhysicalMediaDTO> physicalMedia = ClientController.getInstance().findPhysicalMediasByMedia(mediaDTO.getId());
            if(physicalMedia != null) {
                ObservableList<PhysicalMediaEntry> mediaEntries = FXCollections.observableArrayList();
                for (PhysicalMediaDTO physicalMedia1 : physicalMedia) {
                    mediaEntries.add(new PhysicalMediaEntry(physicalMedia1.getIndex(), physicalMedia1.getAvailability().toString(), physicalMedia1));
                }
                tableView.setItems(mediaEntries);
            }

        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }

    @FXML
    void clickItem(MouseEvent event) {
        if (tableView.getItems().size() > 0) {
            selectedPhysicalMedia = tableView.getSelectionModel().getSelectedItem().getPhysicalMediaDTO();
        }
    }

    private void initColumns() {
        TableColumn<PhysicalMediaEntry, String> indexCol = new TableColumn("Index");
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        indexCol.prefWidthProperty().bind(tableView.widthProperty().divide(2)); // w * 1/2

        TableColumn<PhysicalMediaEntry, String> availCol = new TableColumn("Availability");
        availCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        availCol.prefWidthProperty().bind(tableView.widthProperty().divide(2)); // w * 1/2

        tableView.getColumns().addAll(indexCol, availCol);
    }

    @FXML
    void lend(ActionEvent event) {
        if(Availability.AVAILABLE.equals(selectedPhysicalMedia.getAvailability())) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/NewLendingView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (Exception e) {
                MessageHelper.showErrorAlertMessage(e.getMessage());
            }
            detailStage = new Stage();
            detailStage.setTitle("New Lending");
            detailStage.setScene(scene);
            detailStage.show();
        }
    }

    @FXML
    void reserve(ActionEvent event) {

    }
}
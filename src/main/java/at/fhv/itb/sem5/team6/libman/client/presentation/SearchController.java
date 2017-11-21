package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.MediaEntry;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SearchController {

    private static MediaDTO selectedMedia;
    @FXML
    private TableView<MediaEntry> tableView;
    @FXML
    private TableColumn<MediaEntry, String> columnTitle;
    @FXML
    private TableColumn<MediaEntry, String> columnMediatype;
    @FXML
    private TableColumn<MediaEntry, String> columnAvailable;
    @FXML
    private ComboBox<MediaType> comboMediatype;
    @FXML
    private ComboBox<Genre> comboGenre;
    @FXML
    private ComboBox<Availability> comboAvailabilty;
    @FXML
    private TextField searchTextField;

    public static MediaDTO getCurrentSelectedMedia() {
        return selectedMedia;
    }

    @FXML
    public void initialize() {
        initTable();
        initFilterOptions();
        showFirstEntry();
    }

    @FXML
    void handleEnterPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            search();
        }
    }

    private void initFilterOptions() {
        Collection<MediaType> mediaTyps = Arrays.asList(MediaType.values());
        Collection<Availability> availabilities = Arrays.asList(Availability.values());
        Collection<Genre> genres = Arrays.asList(Genre.values());
        comboMediatype.getItems().addAll(mediaTyps);
        comboAvailabilty.getItems().addAll(availabilities);
        comboGenre.getItems().addAll(genres);
    }

    private void showFirstEntry() {
        comboMediatype.getSelectionModel().selectFirst();
        comboAvailabilty.getSelectionModel().selectFirst();
        comboGenre.getSelectionModel().selectFirst();
    }

    private void initTable() {
        columnTitle.prefWidthProperty().bind(tableView.widthProperty().divide(2)); // w * 1/4
        columnMediatype.prefWidthProperty().bind(tableView.widthProperty().divide(4)); // w * 1/2
        columnAvailable.prefWidthProperty().bind(tableView.widthProperty().divide(4)); // w * 1/4

        columnTitle.setCellValueFactory(new PropertyValueFactory<MediaEntry, String>("title"));
        columnMediatype.setCellValueFactory(new PropertyValueFactory<MediaEntry, String>("mediaType"));
        columnAvailable.setCellValueFactory(new PropertyValueFactory<MediaEntry, String>("available"));
    }

    @FXML
    void search() {
        try {
            tableView.getItems().clear();
            String searchText = searchTextField.getText();
            ObservableList<MediaEntry> mediaEntries = FXCollections.observableArrayList();

            List<MediaDTO> allMedia = ClientController.getInstance().findAllMedia(searchText, comboGenre.getSelectionModel().getSelectedItem(), comboMediatype.getSelectionModel().getSelectedItem(), comboAvailabilty.getSelectionModel().getSelectedItem());
            for (MediaDTO media : allMedia) {
                List<PhysicalMediaDTO> physicalMedia = ClientController.getInstance().findPhysicalMediasByMedia(media.getId());
                List<ReservationDTO> reservations = ClientController.getInstance().findReservationsByMedia(media.getId());

                String available = (physicalMedia.stream().filter(x -> x.getAvailability().equals(Availability.AVAILABLE)).count() > reservations.size()) ? Availability.AVAILABLE.toString(): Availability.NOT_AVAILABLE.toString();
                mediaEntries.add(new MediaEntry(media.getTitle(), media.getType().toString(), available, media));
            }
            tableView.setItems(mediaEntries);
        } catch (Exception e) {
            MessageHelper.showErrorAlertMessage(e.getMessage());
        }
    }

    @FXML
    void clickItem(MouseEvent event) {
        if (tableView.getItems().size() > 0) {
            selectedMedia = tableView.getSelectionModel().getSelectedItem().getMediaDTO();
            if (event.getClickCount() == 2) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/DetailMediaView.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Detail View");
                stage.setScene(scene);
                stage.getIcons().add(new Image("file:src/main/resources/images/logo_libman.png"));
                DetailMediaViewController.detailStage = stage;
                stage.show();
            }
        }
    }
}

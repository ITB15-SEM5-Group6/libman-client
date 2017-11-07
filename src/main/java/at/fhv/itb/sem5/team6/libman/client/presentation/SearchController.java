package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class SearchController {

    private static MediaDTO selectedMedia;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label headerLabel;
    @FXML
    private TableView<MediaEntry> tableView;
    @FXML
    private TableColumn<MediaEntry, String> columnTitle;
    @FXML
    private TableColumn<MediaEntry, String> columnMediatype;
    @FXML
    private TableColumn<MediaEntry, String> columnAvailable;
    @FXML
    private ComboBox<?> comboMediatype;
    @FXML
    private ComboBox<?> comboGenre;
    @FXML
    private ComboBox<?> comboAvailabilty;
    @FXML
    private TextField searchTextField;

    public static MediaDTO getCurrentSelectedMedia() {
        return selectedMedia;
    }

    @FXML
    public void initialize() {
        initTable();


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
    void search(ActionEvent event) throws RemoteException {
        tableView.getItems().clear();
        String searchText = searchTextField.getText();
        searchText = searchText.toUpperCase();
        ObservableList<MediaEntry> mediaEntries = FXCollections.observableArrayList();
        List<MediaDTO> allMedia = new LinkedList<>();
        if (searchText.length() > 0) {
            allMedia = ClientController.getInstance().findAllMedia(searchText);
        } else {
            allMedia = ClientController.getInstance().findAllMedia();
        }

        for (MediaDTO media : allMedia) {
            mediaEntries.add(new MediaEntry(media.getTitle(), media.getType().toString(), " ", media));
        }
        tableView.setItems(mediaEntries);
    }

    @FXML
    void clickItem(MouseEvent event) {
        selectedMedia = tableView.getSelectionModel().getSelectedItem().getMediaDTO();
        if (event.getClickCount() == 2) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/DetailMediaView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Detail View");
            stage.setScene(scene);
            stage.show();
        }
    }




}

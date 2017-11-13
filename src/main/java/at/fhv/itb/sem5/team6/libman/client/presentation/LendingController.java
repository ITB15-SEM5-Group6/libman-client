package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;


public class LendingController {

        @FXML
        private GridPane gridPane;
        @FXML
        private TableView<LendingEntry> tableView;
        @FXML
        private Label headerLabelLending;
        @FXML
        private Button searchButton;
        @FXML
        private TextField searchField;
        @FXML
        private TableColumn<LendingEntry, String> columnTitel;
        @FXML
        private TableColumn<LendingEntry, String> columnName;
        @FXML
        private TableColumn<LendingEntry, String> columnSurname;

        @FXML
        public void initialize() {
                columnTitel.prefWidthProperty().bind(tableView.widthProperty().divide(2)); // w * 1/4
                columnName.prefWidthProperty().bind(tableView.widthProperty().divide(4)); // w * 1/2
                columnSurname.prefWidthProperty().bind(tableView.widthProperty().divide(4)); // w * 1/4

                columnTitel.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("title"));
                columnName.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("customerName"));
                columnSurname.setCellValueFactory(new PropertyValueFactory<LendingEntry, String>("customerSurname"));
        }

        @FXML
        void search(ActionEvent event) throws RemoteException {
                tableView.getItems().clear();
                String searchText = searchField.getText();
                ObservableList<LendingEntry> lendingEntries = FXCollections.observableArrayList();
                List<LendingDTO> allLendigns = new LinkedList<>();

                //TODO: findAllLEndings();
               // allLendigns = ClientController.getInstance().findAllLendings();

                for (LendingDTO lending : allLendigns) {
                        lendingEntries.add(new LendingEntry( lending.getPhysicalMedia().getMedia().getTitle(), lending.getCustomer().getFirstName(), lending.getCustomer().getLastName(), lending));
                }
                tableView.setItems(lendingEntries);
        }
}

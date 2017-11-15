package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerSearchController {

    private static CustomerDTO selectedCustomer;


    public static

    @FXML
    void clickItem(MouseEvent event) {
        if (tableView.getItems().size() > 0) {
            selectedCustomer = tableView.getSelectionModel().getSelectedItem().getMediaDTO();
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
}

}

package at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButtons;

import at.fhv.itb.sem5.team6.libman.client.backend.ClientController;
import at.fhv.itb.sem5.team6.libman.client.presentation.LendingEntry;
import at.fhv.itb.sem5.team6.libman.client.presentation.MessageHelper;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.rmi.RemoteException;

public class RemoveLendingCell extends TableCell<LendingEntry, Boolean> {

    private Button removeLendingButton = new Button("Remove Lending");
    private StackPane paddedButton = new StackPane(); // pads and centers the removeLendingButton in the cell

    public RemoveLendingCell() {
        paddedButton.setPadding(new Insets(3));
        paddedButton.getChildren().add(removeLendingButton);
    }

    public Button getRemoveLendingButton() {
        return removeLendingButton;
    }

    public void setRemoveLendingButton(Button removeLendingButton) {
        this.removeLendingButton = removeLendingButton;
    }

    /** places an button in the row only if the row is not empty. */
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        //only if item is true the button is displayed
        if(item != null && !item) {
            empty = true;
        }
        super.updateItem(item, empty);

        if (!empty) {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(paddedButton);
        } else {
            setGraphic(null);
        }
    }
}

package at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButton;

import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.SelectMediaEntry;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.layout.StackPane;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LendCell extends TableCell<SelectMediaEntry, Boolean> {

    private Button lendButton = new Button("Lend");
    private StackPane paddedButton = new StackPane();

    public LendCell() {
        paddedButton.setPadding(new Insets(3));
        paddedButton.getChildren().add(lendButton);
    }

    public Button getLendButton() {
        return lendButton;
    }

    public void setLendButton(Button button) {
        throw new NotImplementedException();
    }

    /**
     * places an button in the row only if the row is not empty.
     */
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        //only if item is true the button is displayed
        if (item != null && !item) {
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
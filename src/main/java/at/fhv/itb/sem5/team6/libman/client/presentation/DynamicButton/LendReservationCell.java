package at.fhv.itb.sem5.team6.libman.client.presentation.DynamicButton;

import at.fhv.itb.sem5.team6.libman.client.presentation.Entry.ReservationEntry;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.layout.StackPane;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LendReservationCell extends TableCell<ReservationEntry, Boolean> {

    private Button lendReservationButton = new Button("Lend Reservation");
    private StackPane paddedButton = new StackPane(); // pads and centers the removeLendingButton in the cell

    public LendReservationCell() {
        paddedButton.setPadding(new Insets(3));
        paddedButton.getChildren().add(lendReservationButton);
    }

    public Button getLendReservationButton() {
        return lendReservationButton;
    }

    public void setLendReservationButton(Button button) {
        throw new NotImplementedException();
    }


    /**
     * places an button in the row only if the row is not empty.
     */
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        //only if item is true the button is displayed
        super.updateItem(item, empty);

        if (!empty) {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(paddedButton);
        } else {
            setGraphic(null);
        }
    }
}

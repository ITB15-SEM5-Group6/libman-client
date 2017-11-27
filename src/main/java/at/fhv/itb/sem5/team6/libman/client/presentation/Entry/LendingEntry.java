package at.fhv.itb.sem5.team6.libman.client.presentation.Entry;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LendingEntry {

    private SimpleStringProperty title;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty index;
    private SimpleStringProperty lendingDate;
    private SimpleStringProperty lendingState;
    private SimpleIntegerProperty extensions;

    private CustomerDTO customerDTO;
    private PhysicalMediaDTO physicalMediaDTO;
    private LendingDTO lendingDTO;

    public LendingEntry(String title, String mediaType, String index, Date lendingDate, LendingState state, Integer extensions, CustomerDTO customerDTO, PhysicalMediaDTO physicalMediaDTO, LendingDTO lendingDTO) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.title = new SimpleStringProperty(title);
        this.mediaType = new SimpleStringProperty(mediaType);
        this.index = new SimpleStringProperty(index);
        this.lendingDate = new SimpleStringProperty(sdf.format(lendingDate));
        this.lendingState = new SimpleStringProperty(state.toString());
        this.extensions = new SimpleIntegerProperty(extensions);

        this.customerDTO = customerDTO;
        this.physicalMediaDTO = physicalMediaDTO;
        ;
        this.lendingDTO = lendingDTO;
    }


    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getMediaType() {
        return mediaType.get();
    }

    public SimpleStringProperty mediaTypeProperty() {
        return mediaType;
    }

    public String getIndex() {
        return index.get();
    }

    public SimpleStringProperty indexProperty() {
        return index;
    }

    public String getLendingDate() {
        return lendingDate.get();
    }

    public SimpleStringProperty lendingDateProperty() {
        return lendingDate;
    }

    public String getLendingState() {
        return lendingState.get();
    }

    public SimpleStringProperty lendingStateProperty() {
        return lendingState;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public PhysicalMediaDTO getPhysicalMediaDTO() {
        return physicalMediaDTO;
    }

    public LendingDTO getLendingDTO() {
        return lendingDTO;
    }
}

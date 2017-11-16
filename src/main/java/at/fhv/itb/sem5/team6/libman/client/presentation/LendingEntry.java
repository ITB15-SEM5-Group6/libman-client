package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import javafx.beans.property.SimpleStringProperty;

public class LendingEntry {

    private SimpleStringProperty title;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty index;
    private SimpleStringProperty lendingDate;

    private CustomerDTO customerDTO;
    private PhysicalMediaDTO physicalMediaDTO;
    private LendingDTO lendingDTO;

    public LendingEntry(String title, String mediaType, String index, String lendingDate, CustomerDTO customerDTO, PhysicalMediaDTO physicalMediaDTO, LendingDTO lendingDTO) {
        this.title = new SimpleStringProperty(title);
        this.mediaType = new SimpleStringProperty(mediaType);
        this.index = new SimpleStringProperty(index);

        this.lendingDate = new SimpleStringProperty(lendingDate);
        this.customerDTO = customerDTO;
        this.physicalMediaDTO = physicalMediaDTO;
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

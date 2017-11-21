package at.fhv.itb.sem5.team6.libman.client.presentation.Entry;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import javafx.beans.property.SimpleStringProperty;

public class SelectMediaEntry {

    private SimpleStringProperty title;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty index;
    private SimpleStringProperty availability;

    private CustomerDTO customerDTO;
    private PhysicalMediaDTO physicalMediaDTO;
    private MediaDTO mediaDTO;

    public SelectMediaEntry(String title, MediaType mediaType, String index, Availability availability, CustomerDTO customerDTO, PhysicalMediaDTO physicalMediaDTO, MediaDTO mediaDTO) {
        this.title = new SimpleStringProperty(title);
        this.mediaType = new SimpleStringProperty(mediaType.toString());
        this.index = new SimpleStringProperty(index);
        this.availability = new SimpleStringProperty(availability.toString());

        this.customerDTO = customerDTO;
        this.physicalMediaDTO = physicalMediaDTO;;
        this.mediaDTO = mediaDTO;
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

    public String getAvailability() {
        return availability.get();
    }

    public SimpleStringProperty AvailabilityProperty() {
        return availability;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public PhysicalMediaDTO getPhysicalMediaDTO() {
        return physicalMediaDTO;
    }

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }
}

package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import javafx.beans.property.SimpleStringProperty;

public class ReservationEntry {

    private SimpleStringProperty media;

    private CustomerDTO customerDTO;
    private MediaDTO mediaDTO;

    public ReservationEntry(SimpleStringProperty media, CustomerDTO customerDTO, MediaDTO mediaDTO) {
        this.media = media;
        this.customerDTO = customerDTO;
        this.mediaDTO = mediaDTO;
    }

    public String getMedia() {
        return media.get();
    }

    public SimpleStringProperty mediaProperty() {
        return media;
    }

    public void setMedia(String media) {
        this.media.set(media);
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }

    public void setMediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
    }
}

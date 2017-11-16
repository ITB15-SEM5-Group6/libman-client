package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import javafx.beans.property.SimpleStringProperty;

public class ReservationEntry {

    private SimpleStringProperty media;
    private SimpleStringProperty date;

    private CustomerDTO customerDTO;
    private MediaDTO mediaDTO;
    private ReservationDTO reservationDTO;


    public ReservationEntry(String media, String date, CustomerDTO customerDTO, MediaDTO mediaDTO, ReservationDTO reservationDTO) {
        this.media = new SimpleStringProperty(media);
        this.date = new SimpleStringProperty(date);
        this.customerDTO = customerDTO;
        this.mediaDTO = mediaDTO;
        this.reservationDTO = reservationDTO;
    }

    public String getMedia() {
        return media.get();
    }

    public SimpleStringProperty mediaProperty() {
        return media;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }

    public ReservationDTO getReservationDTO() {
        return reservationDTO;
    }
}

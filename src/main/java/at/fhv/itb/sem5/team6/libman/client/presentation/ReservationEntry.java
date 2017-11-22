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


    public ReservationEntry(ReservationDTO reservationDTO) {
        this.media = new SimpleStringProperty(reservationDTO.getMedia().getTitle());
        this.date = new SimpleStringProperty(reservationDTO.getMedia().getReleaseDate().toString());
        this.customerDTO = reservationDTO.getCustomer();
        this.mediaDTO = reservationDTO.getMedia();
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

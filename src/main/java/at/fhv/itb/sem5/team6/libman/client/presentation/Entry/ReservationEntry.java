package at.fhv.itb.sem5.team6.libman.client.presentation.Entry;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import javafx.beans.property.SimpleStringProperty;

public class ReservationEntry {

    private SimpleStringProperty title;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty date;

    private CustomerDTO customerDTO;
    private MediaDTO mediaDTO;
    private ReservationDTO reservationDTO;


    public ReservationEntry(ReservationDTO reservationDTO) {
        this.title = new SimpleStringProperty(reservationDTO.getMedia().getTitle());
        this.date = new SimpleStringProperty(reservationDTO.getMedia().getReleaseDate().toString());
        this.mediaType = new SimpleStringProperty(reservationDTO.getMedia().getType().toString());
        this.customerDTO = reservationDTO.getCustomer();
        this.mediaDTO = reservationDTO.getMedia();
        this.reservationDTO = reservationDTO;
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

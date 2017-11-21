package at.fhv.itb.sem5.team6.libman.client.presentation.Entry;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationEntry {

    private SimpleStringProperty title;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty date;

    private CustomerDTO customerDTO;
    private MediaDTO mediaDTO;
    private ReservationDTO reservationDTO;


    public ReservationEntry(String title, String mediaType, Date date, CustomerDTO customerDTO, MediaDTO mediaDTO, ReservationDTO reservationDTO) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        this.title = new SimpleStringProperty(title);
        this.mediaType = new SimpleStringProperty(mediaType);
        this.date = new SimpleStringProperty(sdf.format(date));

        this.customerDTO = customerDTO;
        this.mediaDTO = mediaDTO;
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

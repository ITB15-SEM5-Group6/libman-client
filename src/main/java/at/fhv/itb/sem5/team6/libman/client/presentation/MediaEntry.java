package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Christina on 06.11.2017.
 */
public class MediaEntry {
    private SimpleStringProperty title;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty available;
    private MediaDTO mediaDTO;

    public MediaEntry(String title, String mediaType, String available, MediaDTO mediaDTO) {
        this.title = new SimpleStringProperty(title);
        this.mediaType = new SimpleStringProperty(mediaType);
        this.available = new SimpleStringProperty(available);
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

    public String getAvailable() {
        return available.get();
    }

    public SimpleStringProperty availableProperty() {
        return available;
    }

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }
}

package at.fhv.itb.sem5.team6.libman.client.presentation;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Christina on 07.11.2017.
 */
public class PhysicalMediaEntry {
    private SimpleStringProperty index;
    private SimpleStringProperty available;

    public PhysicalMediaEntry(String index, String available) {
        this.index = new SimpleStringProperty(index);
        this.available = new SimpleStringProperty(available);
    }

    public String getIndex() {
        return index.get();
    }

    public SimpleStringProperty indexProperty() {
        return index;
    }

    public String getAvailable() {
        return available.get();
    }

    public SimpleStringProperty availableProperty() {
        return available;
    }
}

package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Christina on 07.11.2017.
 */
public class PhysicalMediaEntry {
    private SimpleStringProperty index;
    private SimpleStringProperty available;
    private PhysicalMediaDTO physicalMediaDTO;

    public PhysicalMediaEntry(String index, String available, PhysicalMediaDTO physicalMediaDTO) {
        this.index = new SimpleStringProperty(index);
        this.available = new SimpleStringProperty(available);
        this. physicalMediaDTO = physicalMediaDTO;
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

    public PhysicalMediaDTO getPhysicalMediaDTO() {
        return physicalMediaDTO;
    }
}

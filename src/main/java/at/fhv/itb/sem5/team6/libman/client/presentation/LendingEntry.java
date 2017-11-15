package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import javafx.beans.property.SimpleStringProperty;

public class LendingEntry {


    private SimpleStringProperty surname;
    private SimpleStringProperty name;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty index;
    private SimpleStringProperty lendingDate;

    private CustomerDTO customerDTO;
    private PhysicalMediaDTO physicalMediaDTO;
    private LendingDTO lendingDTO;

    public LendingEntry(String surname, String name, String mediaType, String index, String lendingDate, CustomerDTO customerDTO, PhysicalMediaDTO physicalMediaDTO, LendingDTO lendingDTO) {
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.mediaType = new SimpleStringProperty(mediaType);
        this.index = new SimpleStringProperty(index);
        this.lendingDate = new SimpleStringProperty(lendingDate);
        this.customerDTO = customerDTO;
        this.physicalMediaDTO = physicalMediaDTO;
        this.lendingDTO = lendingDTO;
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMediaType() {
        return mediaType.get();
    }

    public SimpleStringProperty mediaTypeProperty() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType.set(mediaType);
    }

    public String getIndex() {
        return index.get();
    }

    public SimpleStringProperty indexProperty() {
        return index;
    }

    public void setIndex(String index) {
        this.index.set(index);
    }

    public String getLendingDate() {
        return lendingDate.get();
    }

    public SimpleStringProperty lendingDateProperty() {
        return lendingDate;
    }

    public void setLendingDate(String lendingDate) {
        this.lendingDate.set(lendingDate);
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public PhysicalMediaDTO getPhysicalMediaDTO() {
        return physicalMediaDTO;
    }

    public void setPhysicalMediaDTO(PhysicalMediaDTO physicalMediaDTO) {
        this.physicalMediaDTO = physicalMediaDTO;
    }

    public LendingDTO getLendingDTO() {
        return lendingDTO;
    }

    public void setLendingDTO(LendingDTO lendingDTO) {
        this.lendingDTO = lendingDTO;
    }
}

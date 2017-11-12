package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import javafx.beans.property.SimpleStringProperty;

public class CustomerEntry {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;
    private CustomerDTO customerDTO;

    public CustomerEntry(String firstName, String lastName, String email, String phoneNumber, String address, CustomerDTO customerDTO) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.customerDTO = customerDTO;
    }

    public SimpleStringProperty getFirstName() {
        return firstName;
    }

    public SimpleStringProperty getLastName() {
        return lastName;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public SimpleStringProperty getPhoneNumber() {
        return phoneNumber;
    }

    public SimpleStringProperty getAddress() {
        return address;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }
}

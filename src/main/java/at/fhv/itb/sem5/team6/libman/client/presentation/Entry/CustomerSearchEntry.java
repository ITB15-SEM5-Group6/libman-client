package at.fhv.itb.sem5.team6.libman.client.presentation.Entry;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import javafx.beans.property.SimpleStringProperty;

public class CustomerSearchEntry {

    private SimpleStringProperty customerName;
    private SimpleStringProperty customerSurname;
    private SimpleStringProperty customerAddress;
    private SimpleStringProperty customerPhone;
    private SimpleStringProperty customerEmail;

    private CustomerDTO customerDTO;

    public CustomerSearchEntry(CustomerDTO customerDTO) {
        this.customerName = new SimpleStringProperty(customerDTO.getLastName());
        this.customerSurname = new SimpleStringProperty(customerDTO.getFirstName());
        this.customerAddress = new SimpleStringProperty(customerDTO.getAddress());
        this.customerPhone = new SimpleStringProperty(customerDTO.getPhoneNumber());
        this.customerEmail = new SimpleStringProperty(customerDTO.getEmail());

        this.customerDTO = customerDTO;
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return customerName;
    }

    public String getCustomerSurname() {
        return customerSurname.get();
    }

    public SimpleStringProperty CustomerSurnameProperty() {
        return customerSurname;
    }

    public CustomerDTO getCustomerDTO(){return customerDTO;}

    public SimpleStringProperty customerSurnameProperty() {
        return customerSurname;
    }

    public String getCustomerAddress() {
        return customerAddress.get();
    }

    public SimpleStringProperty customerAddressProperty() {
        return customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone.get();
    }

    public SimpleStringProperty customerPhoneProperty() {
        return customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail.get();
    }

    public SimpleStringProperty customerEmailProperty() {
        return customerEmail;
    }
}

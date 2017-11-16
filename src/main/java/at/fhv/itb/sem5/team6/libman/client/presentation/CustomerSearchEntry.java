package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import javafx.beans.property.SimpleStringProperty;

public class CustomerSearchEntry {

    private SimpleStringProperty customerName;
    private SimpleStringProperty customerSurname;
    private CustomerDTO customerDTO;

    public CustomerSearchEntry(String customerName, String customerSurname, CustomerDTO customerDTO) {
        this.customerName = new SimpleStringProperty(customerName);
        this.customerSurname = new SimpleStringProperty(customerSurname);
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

}

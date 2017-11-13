package at.fhv.itb.sem5.team6.libman.client.presentation;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import javafx.beans.property.SimpleStringProperty;

public class LendingEntry {
    private SimpleStringProperty title;
    private SimpleStringProperty customerName;
    private SimpleStringProperty customerSurname;
    private LendingDTO lendingDTO;
    private CustomerDTO customerDTO;

    public LendingEntry(String title, String customerName, String customerSurname, LendingDTO lendingDTO) {
        this.title = new SimpleStringProperty(title);
        this.customerName = new SimpleStringProperty(customerName);
        this.customerSurname = new SimpleStringProperty(customerSurname);
        this.lendingDTO = lendingDTO;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
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

    public LendingDTO getLendingDTO() {
        return lendingDTO;
    }

    public CustomerDTO getCustomerDTO(){return customerDTO;}

}

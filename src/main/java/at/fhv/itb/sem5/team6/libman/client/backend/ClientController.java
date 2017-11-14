package at.fhv.itb.sem5.team6.libman.client.backend;


import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibrary;

import java.rmi.RemoteException;
import java.util.List;

public class ClientController {
    private static ClientController instance = null;
    private static ILibrary library;

    protected ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            return new ClientController();
        } else {
            return instance;
        }
    }

    public static void setLibrary(ILibrary library) {
        ClientController.library = library;
    }

    public List<PhysicalMediaDTO> getPhysicalMedia(MediaDTO media) throws RemoteException {
        return library.findPhysicalMedias(media);
    }

    public List<PhysicalMediaDTO> findAllPhysicalMedia() throws RemoteException {
        return library.findPhysicalMedias();
    }

    public List<MediaDTO> findAllMedia(String text, Genre genre, MediaType type, Availability availability) throws RemoteException {
        return library.findMedias(text, genre, type, availability);
    }

    public List<CustomerDTO> getCustomers(String text) throws RemoteException {
        return library.findCustomers(text);
    }

    public List<CustomerDTO> getAllCustomers() throws RemoteException {
        return library.findCustomers();
    }

    public LendingDTO lendPhysicalMedia(PhysicalMediaDTO physicalMediaDTO, CustomerDTO customerDTO) throws RemoteException {
        return library.lend(physicalMediaDTO, customerDTO);
    }

    public ReservationDTO reserve(MediaDTO mediaDTO, CustomerDTO customerDTO) throws RemoteException {
        return library.reserve(mediaDTO, customerDTO);
    }

}
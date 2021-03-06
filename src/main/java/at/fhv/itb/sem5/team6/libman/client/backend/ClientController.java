package at.fhv.itb.sem5.team6.libman.client.backend;


import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import at.fhv.itb.sem5.team6.libman.shared.enums.UserRole;
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

    public UserRole getUserRole() throws RemoteException {
        return library.getUserRole();
    }

    public static void setLibrary(ILibrary library) {
        ClientController.library = library;
    }

    public List<PhysicalMediaDTO> findPhysicalMediasByMedia(String id) throws RemoteException {
        return library.findPhysicalMediasByMedia(id);
    }

    public List<PhysicalMediaDTO> findAllPhysicalMedia() throws RemoteException {
        return library.findPhysicalMedias();
    }

    public List<MediaDTO> findAllMedia(String text, Genre genre, MediaType type, Availability availability) throws RemoteException {
        return library.findMedias(text, genre, type, availability);
    }

    public List<ReservationDTO> findReservationsByMedia(String mediaId) throws RemoteException {
        return library.findReservationsByMedia(mediaId);
    }

    public List<CustomerDTO> getCustomers(String text) throws RemoteException {
        return library.findCustomers(text);
    }

    public List<CustomerDTO> getAllCustomers() throws RemoteException {
        return library.findCustomers();
    }

    public LendingDTO lendPhysicalMedia(String physicalMediaId, String customerId) throws RemoteException {
        return library.lend(physicalMediaId, customerId);
    }

    public ReservationDTO reserve(String mediaId, String customerId) throws RemoteException {
        return library.reserve(mediaId, customerId);
    }

    public List<LendingDTO> getAllLendings(String customerId) throws RemoteException {
        return library.findLendingsByCustomer(customerId);
    }

    public List<ReservationDTO> getAllReservations(String customerId) throws RemoteException{
        return  library.findReservationsByCustomer(customerId);
    }

    public LendingDTO extendLending(String lendingId) throws RemoteException {
        return library.extendLending(lendingId);
    }

    public void returnLending(String lendingId) throws RemoteException {
        library.returnLending(lendingId);
    }

    public int getMaxExtensions() throws RemoteException {
        return library.getMaxExtensions();
    }

    public boolean isLendPossible(String reservationId) throws RemoteException {
        return library.isLendPossible(reservationId);
    }

    public int getNumberOfAvailableMedias(String mediaId) throws  RemoteException {
        return library.getNumberOfAvailableMedias(mediaId);
    }

    public String getNextMessage() throws RemoteException {
        return library.getNextMessage();
    }

}
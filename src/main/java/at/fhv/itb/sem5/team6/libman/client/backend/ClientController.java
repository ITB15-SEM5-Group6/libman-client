package at.fhv.itb.sem5.team6.libman.client.backend;


import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
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

    public List<MediaDTO> findAllMedia(String text) throws RemoteException {
        return library.findAllMedia();
    }

    public List<MediaDTO> findAllMedia() throws RemoteException {
        return library.findAllMedia();
    }

    public List<PhysicalMediaDTO> getPhysicalMedia(MediaDTO media) throws RemoteException {
        return library.getPhysicalMedia(media);
    }

    public List<PhysicalMediaDTO> findAllPhysicalMedia() throws RemoteException {
        return library.findAllPhysicalMedia();
    }

    public List<MediaDTO> findAllMedia(String text, MediaType type, Availability availability) throws RemoteException {
        return library.findAllMedia(text, type, availability);
    }
}
package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource = null;

    private AdminResource(){}

    public static AdminResource getInstance(){
        if(adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public static CustomerService customerService = CustomerService.getInstance();
    public static ReservationService reservationService = ReservationService.getInstance();

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public static Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public static void addRoom(List<IRoom> rooms){
        for (IRoom room: rooms){
            reservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms(){
        return reservationService.allRooms();
    }

    public static void displayAllReservations(){
        reservationService.printAllReservation();
    }
}

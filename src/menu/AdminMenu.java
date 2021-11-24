package menu;

import api.AdminResource;
import model.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void AdminMenu(){
        boolean exit = false;

        while (!exit){
            printAdminMenu();

            String option = scanner.nextLine();
            switch (option){
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addARoom();
                    break;
                case "5":
                    exit = true;
                    System.out.println("You will go back to main menu.");
                    System.out.println("============================================");
                    break;
                default:
                    System.out.println("\nError: Invalid Input.");
                    System.out.println("Please enter 1, 2, 3, 4, or 5 for your choices.\n");
            }
        }
    }

    private void printAdminMenu(){
        System.out.println("This is the administrator menu.");
        System.out.println("Please enter a number for the following choices:");
        System.out.println("1. See all Customers.");
        System.out.println("2. See all Rooms.");
        System.out.println("3. See all reservations.");
        System.out.println("4. Add a room.");
        System.out.println("5. Go back to main menu.");
        System.out.println("============================================");
    }

    private void seeAllCustomers(){
        Collection<Customer> allCustomers = AdminResource.getAllCustomers();

        if (allCustomers.isEmpty()){
            System.out.println("Sorry, there are no available customers yet.");
        }else{
            for (Customer customer: allCustomers) {
                System.out.println(customer);
            }
        }
        System.out.println("============================================");
    }

    private void seeAllRooms(){
        Collection<IRoom> allRooms = AdminResource.getAllRooms();

        if (allRooms.isEmpty()){
            System.out.println("Sorry, there are no available rooms yet.");
        }else{
            for (IRoom room: allRooms) {
                System.out.println(room);
            }
        }
        System.out.println("============================================");

    }

    private static void seeAllReservations(){
        AdminResource.displayAllReservations();
        System.out.println("============================================");
    }

    private void addARoom(){
        System.out.println("Enter the room's number: ");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter the room's price: ");
        double price = scanner.nextDouble();

        RoomType roomType = null;
        boolean exit = false;
        do {
            System.out.println("Which type of room do you want to create?");
            System.out.println("1. Single\n2. Double");

            String option = scanner.nextLine();
            switch (option){
                case "1":
                    roomType = RoomType.SINGLE;
                    exit = true;
                    break;
                case "2":
                    roomType = RoomType.DOUBLE;
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }while(!exit);

        IRoom room;
        if (price == 0){
            room = new FreeRoom(roomNumber, price, roomType);
        }else {
            room = new Room(roomNumber, price, roomType);
        }

        List<IRoom> rooms = new LinkedList<>();
        rooms.add(room);
        AdminResource.addRoom(rooms);
        System.out.println("room number: " + roomNumber + ", room type: " + roomType + ", price: $" + price + " has been added.");
        System.out.println("============================================");
    }
}

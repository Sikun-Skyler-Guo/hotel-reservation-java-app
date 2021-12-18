package menu;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void MainMenu(){
        boolean exit = false;
        while (!exit){
            printMainMenu();

            String option = scanner.nextLine();
            switch (option){
                case "1":
                    findAndReserveARoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAnAccount();
                    break;
                case "4":
                    openAdministratorMenu();
                    break;
                case "5":
                    exit = true;
                    System.out.println("Thank you for using the hotel reservation service.");
                    System.out.println("Have a nice day! See you next time.");
                    break;
                default:
                    System.out.println("\nError: Invalid Input.");
                    System.out.println("Please enter 1, 2, 3, 4, or 5 for your choices.\n");
            }
        }
    }

    private static void printMainMenu(){
        System.out.println("Welcome to the hotel reservation main menu!");
        System.out.println("Please enter a number for the following choices:");
        System.out.println("1. Find and reserve a room.");
        System.out.println("2. See my reservations.");
        System.out.println("3. Create an account.");
        System.out.println("4. Open administrator menu.");
        System.out.println("5. Exit.");
        System.out.println("============================================");
    }

    private void findAndReserveARoom(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date checkInDate;
        Date checkOutDate;

        for(;;){
            try{
                System.out.println("Enter your check in date (MM/dd/yyyy): ");
                String date = scanner.nextLine();
                checkInDate = dateFormat.parse(date);
                break;
            }catch (Exception e){
                System.out.println("Your date format is wrong! Enter in MM/dd/yyyy format.");
            }
        }

        for(;;){
            try{
                System.out.println("Enter your check out date (MM/dd/yyyy): ");
                String date = scanner.nextLine();
                checkOutDate = dateFormat.parse(date);
                break;
            }catch (Exception e){
                System.out.println("Your date format is wrong! Enter in MM/dd/yyyy format.");
            }
        }

        Collection<IRoom> rooms = HotelResource.findARoom(checkInDate, checkOutDate);

        if (rooms.isEmpty()){
            System.out.println("No room available for your provided date.");
            System.out.println("============================================");
            return;
        }

        System.out.println("Here are the available rooms on that date range.");
        for(IRoom room: rooms){
            System.out.println(room);
        }

        System.out.println("Enter your customer email: ");
        String email = scanner.nextLine();

        System.out.println("You've seen what we have available. Enter a room number to book: ");
        String roomNumber = scanner.nextLine();
        IRoom room = HotelResource.getRoom(roomNumber);

        Reservation reservation = HotelResource.bookARoom(email, room, checkInDate, checkOutDate);
        System.out.println(reservation);

        System.out.println("Thank you for booking with us.");

    }

    private void seeMyReservations(){
        System.out.println("Enter customer email: ");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = HotelResource.getCustomerReservations(email);

        if (reservations.isEmpty()){
            System.out.println("Sorry, you don't have available reservations.");
            System.out.println("============================================");
        }

        for (Reservation reservation: reservations){
            System.out.println(reservation);
            System.out.println("============================================");
        }
    }

    private void createAnAccount(){
        final String emailRegEx = "^(.+)@(.+).com$";
        final Pattern pattern = Pattern.compile(emailRegEx);
        boolean correct_email_format = false;
        while(!correct_email_format) {
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();
            try {
                if (!pattern.matcher(email).matches()) {
                    throw new IllegalArgumentException("Error, Invalid email.");
                }

                System.out.println("Enter your first name: ");
                String firstName = scanner.nextLine();

                System.out.println("Enter your last name: ");
                String lastName = scanner.nextLine();

                HotelResource.createACustomer(email, firstName, lastName);
                System.out.println("Welcome " + firstName + " " + lastName + ", your account has been created.");
                System.out.println("============================================");
                correct_email_format = true;
            } catch (Exception exception) {
                System.out.println("Illegal argument > " + exception.getMessage() + "Please enter a correct email form.");
            }




        }
    }

    private static void openAdministratorMenu(){
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.AdminMenu();
    }
}

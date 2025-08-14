import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();
    Map<Integer, String> bookings = new HashMap<>(); // roomNumber -> guestName

    Hotel() {
        // Sample rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
    }

    void viewAvailableRooms() {
        System.out.println("\n Available Rooms:");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room " + r.roomNumber + " (" + r.category + ")");
            }
        }
    }

    void bookRoom(int roomNumber, String guestName) {
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                if (!r.isBooked) {
                    r.isBooked = true;
                    bookings.put(roomNumber, guestName);
                    System.out.println(" Room " + roomNumber + " booked successfully for " + guestName);
                    simulatePayment();
                } else {
                    System.out.println(" Room is already booked!");
                }
                return;
            }
        }
        System.out.println(" Room not found!");
    }

    void cancelBooking(int roomNumber) {
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                if (r.isBooked) {
                    r.isBooked = false;
                    bookings.remove(roomNumber);
                    System.out.println(" Booking for Room " + roomNumber + " has been canceled.");
                } else {
                    System.out.println("Room is not booked.");
                }
                return;
            }
        }
        System.out.println(" Room not found!");
    }

    void viewBookings() {
        System.out.println("\n Current Bookings:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Map.Entry<Integer, String> entry : bookings.entrySet()) {
                System.out.println("Room " + entry.getKey() + " - Guest: " + entry.getValue());
            }
        }
    }

    void simulatePayment() {
        System.out.println(" Processing payment...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Payment interrupted!");
        }
        System.out.println(" Payment Successful!");
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n=== HOTEL RESERVATION SYSTEM ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    hotel.viewAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter Room Number: ");
                    int roomNum = sc.nextInt();
                    System.out.print("Enter Guest Name: ");
                    sc.nextLine(); // clear buffer
                    String name = sc.nextLine();
                    hotel.bookRoom(roomNum, name);
                    break;
                case 3:
                    System.out.print("Enter Room Number to Cancel: ");
                    int cancelRoom = sc.nextInt();
                    hotel.cancelBooking(cancelRoom);
                    break;
                case 4:
                    hotel.viewBookings();
                    break;
                case 5:
                    System.out.println(" Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println(" Invalid choice!");
            }
        }
    }
}

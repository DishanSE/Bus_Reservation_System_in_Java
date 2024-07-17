import java.util.*;

class Customer {
    String id;
    String name;
    String mobileNumber;
    String email;
    String city;
    int age;
    List<String> reservations;

    public Customer(String id, String name, String mobileNumber, String email, String city, int age) {
        if (id == null || name == null || mobileNumber == null || email == null || city == null || age <= 0) {
            throw new IllegalArgumentException("Invalid customer details provided");
        }
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.city = city;
        this.age = age;
        this.reservations = new ArrayList<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public List<String> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", reservations=" + reservations +
                '}';
    }
}

class Bus {
    String busNumber;
    int totalSeats;
    String startingPoint;
    String endingPoint;
    String startingTime;
    double fare;
    HashMap<Integer, String> seatReservations;

    public Bus(String busNumber, int totalSeats, String startingPoint, String endingPoint, String startingTime,
               double fare) {
        if (busNumber == null || totalSeats <= 0 || startingPoint == null || endingPoint == null || startingTime == null || fare <= 0) {
            throw new IllegalArgumentException("Invalid bus details provided");
        }
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingTime = startingTime;
        this.fare = fare;
        this.seatReservations = new HashMap<>();
    }

    // Getters
    public String getBusNumber() {
        return busNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public double getFare() {
        return fare;
    }

    public HashMap<Integer, String> getSeatReservations() {
        return seatReservations;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busNumber='" + busNumber + '\'' +
                ", totalSeats=" + totalSeats +
                ", startingPoint='" + startingPoint + '\'' +
                ", endingPoint='" + endingPoint + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", fare=" + fare +
                ", seatReservations=" + seatReservations +
                '}';
    }
}

class Reservation {
    String reservationId;
    String customerId;
    String busNumber;
    int seatNumber;
    Date timestamp;

    public Reservation(String reservationId, String customerId, String busNumber, int seatNumber) {
        if (reservationId == null || customerId == null || busNumber == null || seatNumber <= 0) {
            throw new IllegalArgumentException("Invalid reservation details provided");
        }
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.busNumber = busNumber;
        this.seatNumber = seatNumber;
        this.timestamp = new Date();
    }

    // Getters
    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", seatNumber=" + seatNumber +
                ", timestamp=" + timestamp +
                '}';
    }
}

class Notification {
    String notificationId;
    String customerId;
    String message;

    public Notification(String notificationId, String customerId, String message) {
        if (notificationId == null || customerId == null || message == null) {
            throw new IllegalArgumentException("Invalid notification details provided");
        }
        this.notificationId = notificationId;
        this.customerId = customerId;
        this.message = message;
    }

    // Getters
    public String getNotificationId() {
        return notificationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

public class BusReservationSystem {
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, Bus> buses = new HashMap<>();
    private HashMap<String, Reservation> reservations = new HashMap<>();
    private PriorityQueue<Reservation> waitingQueue = new PriorityQueue<>(Comparator.comparing(Reservation::getTimestamp));
    private Queue<Notification> notificationQueue = new LinkedList<>();

    // Customer Registration
    public void registerCustomer(String id, String name, String mobileNumber, String email, String city, int age) {
        if (customers.containsKey(id)) {
            System.out.println("Customer already registered.");
            return;
        }
        try {
            Customer customer = new Customer(id, name, mobileNumber, email, city, age);
            customers.put(id, customer);
            System.out.println("Customer registered successfully.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Error registering customer: " + e.getMessage());
        }
    }

    // Bus Registration
    public void registerBus(String busNumber, int totalSeats, String startingPoint, String endingPoint,
                            String startingTime, double fare) {
        if (buses.containsKey(busNumber)) {
            System.out.println("Bus already registered.");
            return;
        }
        try {
            Bus bus = new Bus(busNumber, totalSeats, startingPoint, endingPoint, startingTime, fare);
            buses.put(busNumber, bus);
            System.out.println("Bus registered successfully.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Error registering bus: " + e.getMessage());
        }
    }

    // Search for Buses
    public List<Bus> searchBuses(String startingPoint, String endingPoint) {
        List<Bus> foundBuses = new ArrayList<>();
        for (Bus bus : buses.values()) {
            if (bus.getStartingPoint().equalsIgnoreCase(startingPoint) && bus.getEndingPoint().equalsIgnoreCase(endingPoint)) {
                foundBuses.add(bus);
            }
        }
        return foundBuses;
    }

    // Reserve Seat
    public void reserveSeat(String customerId, String busNumber, int seatNumber) {
        Customer customer = customers.get(customerId);
        Bus bus = buses.get(busNumber);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }
        if (bus.getSeatReservations().containsKey(seatNumber)) {
            System.out.println("Seat already reserved.");
            return;
        }

        try {
            String reservationId = UUID.randomUUID().toString();
            Reservation reservation = new Reservation(reservationId, customerId, busNumber, seatNumber);
            bus.getSeatReservations().put(seatNumber, reservationId);
            customer.getReservations().add(reservationId);
            reservations.put(reservationId, reservation);

            sendNotification(customerId,
                    "Seat reserved successfully on bus " + busNumber + " for seat number " + seatNumber);
            System.out.println("Seat reserved successfully.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Error reserving seat: " + e.getMessage());
        }
    }

    // Cancel Reservation
    public void cancelReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        Customer customer = customers.get(reservation.getCustomerId());
        Bus bus = buses.get(reservation.getBusNumber());

        bus.getSeatReservations().remove(reservation.getSeatNumber());
        customer.getReservations().remove(reservationId);
        reservations.remove(reservationId);

        sendNotification(customer.getId(), "Reservation cancelled for bus " + bus.getBusNumber() + " and seat number " + reservation.getSeatNumber());

        if (!waitingQueue.isEmpty()) {
            Reservation nextReservation = waitingQueue.poll();
            reserveSeat(nextReservation.getCustomerId(), nextReservation.getBusNumber(), reservation.getSeatNumber());
            sendNotification(nextReservation.getCustomerId(), "Your waiting request has been confirmed for bus " + nextReservation.getBusNumber() + " and seat number " + reservation.getSeatNumber());
        }

        System.out.println("Reservation cancelled successfully.");
    }

    // Request New Seat
    public void requestNewSeat(String customerId, String busNumber) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        try {
            String reservationId = UUID.randomUUID().toString();
            Reservation reservation = new Reservation(reservationId, customerId, busNumber, -1);
            waitingQueue.add(reservation);
            sendNotification(customerId, "Your request for a new seat on bus " + busNumber + " is in the waiting queue.");
            System.out.println("New seat request added to the waiting queue.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Error requesting new seat: " + e.getMessage());
        }
    }

    // Display All Reservations
    public void displayReservations() {
        for (Reservation reservation : reservations.values()) {
            System.out.println(reservation);
        }
    }

    // Send Notification
    private void sendNotification(String customerId, String message) {
        String notificationId = UUID.randomUUID().toString();
        Notification notification = new Notification(notificationId, customerId, message);
        notificationQueue.add(notification);
        processNotifications();
    }

    // Process Notifications
    private void processNotifications() {
        while (!notificationQueue.isEmpty()) {
            Notification notification = notificationQueue.poll();
            System.out.println("Notification sent to " + notification.getCustomerId() + ": " + notification.getMessage());
        }
    }
    public static void main(String[] args) {
        BusReservationSystem system = new BusReservationSystem();

        // Test Case 1: Customer Registration
        system.registerCustomer("C001", "Alice", "1234567890", "alice@example.com", "CityA", 25);
        system.registerCustomer("C002", "Bob", "0987654321", "bob@example.com", "CityB", 30);
        system.registerCustomer("C001", "Alice", "1234567890", "alice@example.com", "CityA", 25);
        System.out.println();

        // Test Case 2: Bus Registration
        system.registerBus("B001", 40, "CityA", "CityB", "10:00 AM", 15.50);
        system.registerBus("B002", 30, "CityA", "CityC", "02:00 PM", 20.00);
        system.registerBus("B001", 40, "CityA", "CityB", "10:00 AM", 15.50);
        System.out.println();

        // Test Case 3: Search for Buses
        List<Bus> foundBuses = system.searchBuses("CityA", "CityB");
        for (Bus bus : foundBuses) {
            System.out.println(bus);
        }
        System.out.println();

        // Test Case 4: Reserve Seat
        system.reserveSeat("C001", "B001", 1);
        system.reserveSeat("C002", "B001", 2);
        system.reserveSeat("C001", "B001", 1);
        System.out.println();

        // Test Case 5: Cancel Reservation
        String reservationId = system.reservations.values().iterator().next().getReservationId();
        system.cancelReservation(reservationId);
        System.out.println();

        // Test Case 6: Request New Seat
        system.requestNewSeat("C001", "B001");
        System.out.println();

        // Test Case 7: Display Reservations
        system.displayReservations();
    }
}

import java.util.*;

class BookTicket {
    String name;
    int age;
    String boardingPoint;
    String destinationPoint;
    int trainNo;
    int pnr;

    public BookTicket(String name, int age, String boardingPoint, String destinationPoint, int trainNo, int pnr) {
        this.name = name;
        this.age = age;
        this.boardingPoint = boardingPoint;
        this.destinationPoint = destinationPoint;
        this.trainNo = trainNo;
        this.pnr = pnr;
    }
}

class RailwayService {
    private final Map<Integer, Map<Integer, BookTicket>> bookedTickets = new HashMap<>();
    private final Map<Integer, BookTicket> waitingList = new LinkedHashMap<>();
    private int pnrCounter = 4578;

    public RailwayService() {
        bookedTickets.put(1, new LinkedHashMap<>());
        bookedTickets.put(2, new LinkedHashMap<>());
        bookedTickets.put(3, new LinkedHashMap<>());
    }

    public void bookTicket(String name, int age, String boardingPoint, String destinationPoint, int trainNo) {
        BookTicket ticket = new BookTicket(name, age, boardingPoint, destinationPoint, trainNo, pnrCounter);

        int capacity = getTrainCapacity(trainNo);
        if (bookedTickets.containsKey(trainNo) && bookedTickets.get(trainNo).size() < capacity) {
            bookedTickets.get(trainNo).put(pnrCounter, ticket);
            System.out.println("Ticket Booked Successfully. Your PNR is: " + pnrCounter);
        } else {
            waitingList.put(pnrCounter, ticket);
            System.out.println("Train is full. Added to waiting list. Your PNR is: " + pnrCounter);
        }

        pnrCounter++;
    }

    public void cancelTicket(int trainNo, int pnr) {
        if (bookedTickets.containsKey(trainNo) && bookedTickets.get(trainNo).remove(pnr) != null) {
            handleWaitingList(trainNo);
            System.out.println("Ticket Cancelled Successfully.");
        } else if (waitingList.remove(pnr) != null) {
            System.out.println("Ticket Cancelled Successfully from Waiting List.");
        } else {
            System.out.println("Invalid Train or PNR Number.");
        }
    }

    public void viewStatus(int trainNo, int pnr) {
        if (bookedTickets.containsKey(trainNo) && bookedTickets.get(trainNo).containsKey(pnr)) {
            System.out.println("Passenger is booked in Train.");
        } else if (waitingList.containsKey(pnr)) {
            System.out.println("Passenger is in Waiting List.");
        } else {
            System.out.println("No record found for the provided Train and PNR Number.");
        }
    }

    private int getTrainCapacity(int trainNo) {
        switch (trainNo) {
            case 1: return 5;
            case 2: return 7;
            case 3: return 3;
            default: return 0;
        }
    }

    private void handleWaitingList(int trainNo) {
        if (!waitingList.isEmpty()) {
            Iterator<Integer> iterator = waitingList.keySet().iterator();
            if (iterator.hasNext()) {
                int nextPnr = iterator.next();
                BookTicket nextTicket = waitingList.remove(nextPnr);
                bookedTickets.get(trainNo).put(nextPnr, nextTicket);
            }
        }
    }
}

public class Railway {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RailwayService railwayService = new RailwayService();

        while (true) {
            displayMainMenu();
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    bookTicketMenu(sc, railwayService);
                    break;
                case 2:
                    cancelTicketMenu(sc, railwayService);
                    break;
                case 3:
                    viewStatusMenu(sc, railwayService);
                    break;
                case 4:
                    System.out.println("Exiting System. Thank you!");
                    return;
                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== Railway Reservation System =====");
        System.out.println("1. Book Ticket");
        System.out.println("2. Cancel Ticket");
        System.out.println("3. View Status");
        System.out.println("4. Exit");
    }

    private static void bookTicketMenu(Scanner sc, RailwayService railwayService) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine();

        displayTrainDetails();
        System.out.print("Enter your boarding point: ");
        String boardingPoint = sc.nextLine();
        System.out.print("Enter your destination point: ");
        String destinationPoint = sc.nextLine();
        System.out.print("Enter your train number: ");
        int trainNo = sc.nextInt();

        railwayService.bookTicket(name, age, boardingPoint, destinationPoint, trainNo);
    }

    private static void cancelTicketMenu(Scanner sc, RailwayService railwayService) {
        System.out.print("Enter Train Number: ");
        int trainNo = sc.nextInt();
        System.out.print("Enter PNR Number: ");
        int pnr = sc.nextInt();

        railwayService.cancelTicket(trainNo, pnr);
    }

    private static void viewStatusMenu(Scanner sc, RailwayService railwayService) {
        System.out.print("Enter Train Number: ");
        int trainNo = sc.nextInt();
        System.out.print("Enter PNR Number: ");
        int pnr = sc.nextInt();

        railwayService.viewStatus(trainNo, pnr);
    }

    private static void displayTrainDetails() {
        System.out.println("\nTrain Details:");
        System.out.println("1. Kovai ---> Chennai");
        System.out.println("2. Ernakulam ---> Chennai");
        System.out.println("3. Coimbatore ---> Chennai");
    }
}

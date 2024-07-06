import java.util.*;

class BookTicket {
    String name;
    int age;
    String boardingPoint;
    String destinationPoint;
    int train_no;
    int Pnr;

    public BookTicket(String name, int age, String boardingPoint, String destinationPoint, int trainNo, int pnr) {
        this.name = name;
        this.age = age;
        this.boardingPoint = boardingPoint;
        this.destinationPoint = destinationPoint;
        this.train_no = trainNo;
        this.Pnr = pnr;
    }
} 

public class Railway {
    public static void mainMenu() {
        System.out.println("1. Book Ticket");
        System.out.println("2. Cancel Ticket");
        System.out.println("3. View Status");
        System.out.println("4. Exit");
    }

    public static void trainDetails() {
        System.out.println("\nTrains Details");
        System.out.println("1. Kovai ---> Chennai");
        System.out.println("2. Ernakulam ---> Chennai");
        System.out.println("3. Coimbatore ---> Chennai");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\t\t\t\tWelcome to Railway System\n");
        boolean check = true;
        Map<Integer, Map<Integer, BookTicket>> bookDetails = new HashMap<>();
        Map<Integer, BookTicket> waitingList = new LinkedHashMap<>();
        int Pnr = 4578;

        do {
            mainMenu();
            System.out.println("\nEnter your choice :");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter your name : ");
                    String name = sc.nextLine();
                    System.out.println("Enter your age : ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    trainDetails();
                    System.out.println("\nEnter your boarding : ");
                    String boarding_point = sc.nextLine();
                    System.out.println("Enter your destination : ");
                    String destination_point = sc.nextLine();

                    System.out.println("Enter your train_no : ");
                    int train_no = sc.nextInt();
                    BookTicket book = new BookTicket(name, age, boarding_point, destination_point, train_no, Pnr);
                    System.out.println();

                    if (!bookDetails.containsKey(train_no)) {
                        bookDetails.put(train_no, new LinkedHashMap<>());
                    }

                    boolean isBooked = false;
                    if (train_no == 1 && bookDetails.get(train_no).size() < 5) {
                        bookDetails.get(train_no).put(Pnr, book);
                        isBooked = true;
                    } else if (train_no == 2 && bookDetails.get(train_no).size() < 7) {
                        bookDetails.get(train_no).put(Pnr, book);
                        isBooked = true;
                    } else if (train_no == 3 && bookDetails.get(train_no).size() < 3) {
                        bookDetails.get(train_no).put(Pnr, book);
                        isBooked = true;
                    }

                    if (!isBooked) {
                        waitingList.put(Pnr, book);
                        System.out.println("You're in the waiting List\n");
                    }

                    System.out.println("Your Unique PNR number : " + Pnr);
                    System.out.println();
                    Pnr++;
                    break;

                case 2:
                    System.out.println("Enter the train_no: ");
                    int train_num = sc.nextInt();
                    System.out.println("Enter the PNR number: ");
                    int pnr = sc.nextInt();
                    if (bookDetails.containsKey(train_num) && bookDetails.get(train_num).containsKey(pnr)) {
                        bookDetails.get(train_num).remove(pnr);
                        if (!waitingList.isEmpty()) {
                            Integer first = waitingList.keySet().iterator().next();
                            BookTicket train = waitingList.remove(first);
                            bookDetails.get(train.train_no).put(train.Pnr, train);
                        }
                        System.out.println("Ticket Cancelled");
                    } else if (waitingList.containsKey(pnr)) {
                        waitingList.remove(pnr);
                        System.out.println("Ticket Cancelled");
                    } else {
                        System.out.println("Invalid train number or PNR number");
                    }
                    break;

                case 3:
                    System.out.println("Enter the train_no: ");
                    int trainNo = sc.nextInt();
                    System.out.println("Enter the PNR number: ");
                    int getPnr = sc.nextInt();
                    if (bookDetails.containsKey(trainNo) && bookDetails.get(trainNo).containsKey(getPnr)) {
                        System.out.println("Passenger is in train");
                    } else if (waitingList.containsKey(getPnr)) {
                        System.out.println("Passenger is in the waiting list");
                    } else {
                        System.out.println("No passenger with this PNR number");
                    }
                    break;

                default:
                    check = false;
                    break;
            }
        } while (check);
    }
}

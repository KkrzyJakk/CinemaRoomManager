import java.util.Scanner;

public class Cinema {
    public static void printSeats(String cinema[][], int rows) {
        System.out.print("Cinema: \n" + "  ");

        for(int k = 0; k <= rows; k++) {
            System.out.print((k + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j <= cinema[i].length; j++) {
                if (j==0) {
                    System.out.print(i+1 + " ");
                } else {
                    System.out.print(cinema[i][j-1]);
                }
            }
            System.out.println();
        }
    }
    public static String[][] seatReserve(String cinema[][]) {
        Scanner s = new Scanner(System.in);
        int done = 0;
        do {
            System.out.println("\nEnter a row number:");
            int rows = s.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seats = s.nextInt();
            int price = 0;

            if (cinema.length * cinema[1].length <= 60) {
                price = 10;
            } else {
                if (cinema.length/2 < rows) {
                    price = 8;
                } else {
                    price = 10;
                }
            }
            if (rows > cinema.length || seats > cinema[1].length) {
                System.out.println("Wrong input!");
            } else if(cinema[rows-1][seats-1]== "S ") {
                System.out.println("Ticket price: $" + price);
                cinema[rows-1][seats-1] = "B ";
                done = 1;
            } else if (cinema[rows-1][seats-1]== "B ") {
                System.out.println("That ticket has already been purchased!");
            }
        } while (done != 1);
        return cinema;

    }

    public static void printMenu() {
        System.out.println("1. Show the seats\n" + "2. Buy a ticket\n" + "3. Show statistics\n" + "0. Exit\n");
    }
    public static void printStatistics(String cinema[][]) {
        int occupiedSeats = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        int price = 0;
        for (int i = 0; i < cinema.length; i++ ) {
            for (int j = 0; j < cinema[i].length; j++) {

                if (cinema.length*cinema[1].length <= 60) {
                    totalIncome += 10;
                } else {
                    if (cinema.length/2 <= i) {
                        totalIncome += 8;
                    } else {
                        totalIncome += 10;
                    }
                }

                if (cinema[i][j] == "B ") {
                    occupiedSeats++;
                    if (cinema.length * cinema[1].length <= 60) {
                        price = 10;
                        currentIncome += price;
                    } else {
                        if (cinema.length/2 <= i) {
                            price = 8;
                            currentIncome += price;
                        } else {
                            price = 10;
                            currentIncome += price;
                        }
                    }
                }
            }
        }
        float multiplication = cinema.length*cinema[1].length;
        float percentage = 100*occupiedSeats/multiplication;

        System.out.println("Number of purchased tickets: " + occupiedSeats);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome + "\n");

    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = s.nextInt();
        String cinema[][] = new String[rows][seats];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S ";
            }
        }

        int exit = 1;

        do {
            printMenu();
            exit = s.nextInt();
            switch(exit) {
                case 1:
                    printSeats(cinema, rows);
                    break;
                case 2:
                    seatReserve(cinema);
                    break;
                case 3:
                    printStatistics(cinema);
                    break;
                case 0:
                    exit = 0;
                    System.out.println("Bye!");
            }
        } while (exit !=0);

    }
}
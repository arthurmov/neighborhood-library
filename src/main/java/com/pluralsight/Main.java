package com.pluralsight;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static Book[] library = getPopulatedBooks();
    private static Console console = new Console();

    public static void main(String[] args) {

        ShowScreenHome();

    }

    private static void ShowScreenHome() {

        String homeScreenPrompt = "╔═══════════════════════════════════════╗\n" +
                                  "║ Welcome to the Neighborhood Library!  ║\n" +
                                  "╠═══════════════════════════════════════╣\n" +
                                  "║ 1. Show Available Books               ║\n" +
                                  "║ 2. Show Checked Out Books             ║\n" +
                                  "║ 0. Exit                               ║\n" +
                                  "╚═══════════════════════════════════════╝\n" +
                                  "Please select an option: ";
        int option;

            do{
                option = console.promptForInt(homeScreenPrompt);

                switch(option) {
                    case 1:
                        System.out.println("\n[Showing available books...]");
                        ShowScreenAvailableBooks();
                        break;
                    case 2:
                        System.out.println("\n[Showing checked out books...]\n");
                        ShowScreenCheckedOutBooks();
                        break;
                    case 0:
                        System.out.println("\nThank you for visiting the Neighborhood Library. Goodbye!");
                        break;
                    default:
                        System.out.println("\nInvalid option. Try again!");
                }
            } while(option != 0);
        }

    private static void ShowScreenAvailableBooks() {

        int option;
        String name;
        int bookId;

        for(int i = 0; i < library.length; i++) {
            if(!library[i].isCheckedOut()) {
                System.out.println("\nID: " + library[i].getId());
                System.out.println("ISBN: " + library[i].getIsbn());
                System.out.println("Title: " + library[i].getTitle());
            }
        }

        do {
            System.out.println("\n╔═══════════════════════════════════════╗\n" +
                                 "║ 1. Check Out a Book                   ║\n" +
                                 "║ 0. Exit Back to Home Screen           ║\n" +
                                 "╚═══════════════════════════════════════╝\n" +
                                 "Please select an option: \n");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("\nPlease Enter Your Full Name: ");
                    name = scanner.nextLine();

                    System.out.println("\nEnter the ID of the Book: ");
                    bookId = scanner.nextInt();

                    library[bookId - 1].setCheckedOut(true);
                    System.out.println("\nYou Have Successfully Checked Out " + library[bookId -1].getTitle());
                    break;
                case 0:
                    System.out.println("\n[Exiting back to home screen...]");
                    break;
                default:
                    System.out.println("\nInvalid option. Try again!");
            }
        } while (option != 0);
    }

    private static void ShowScreenCheckedOutBooks() {

        int option;
        int bookId;

        for(int i = 0; i < library.length; i++) {
            if (library[i].isCheckedOut()) {
                System.out.println("\nID: " + library[i].getId());
                System.out.println("ISBN: " + library[i].getIsbn());
                System.out.println("Title: " + library[i].getTitle());
            }
        }

        do {
            System.out.println("\n╔═══════════════════════════════════════╗\n" +
                                 "║ 1. Check In a Book                    ║\n" +
                                 "║ 0. Exit Back to Home Screen           ║\n" +
                                 "╚═══════════════════════════════════════╝\n" +
                                 "Please select an option: \n");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Please enter the ID of the book: ");
                    bookId = scanner.nextInt();

                    if(library[bookId - 1].isCheckedOut()) {
                        library[bookId - 1].setCheckedOut(false);
                        System.out.println("\nYou Have Successfully Checked In " + library[bookId - 1].getTitle());
                    } else {
                        System.out.println("\nThat Book was not Checked Out. Try Again!");
                    }
                    break;
                case 2:
                    System.out.println("\n[Exiting back to home screen...]");
                    break;
            }
        } while (option != 0);
    }


    private static Book[] getPopulatedBooks() {

        Book[] library = new Book[20];

        library[0] = new Book(1, "9780061120084", "To Kill a Mockingbird");
        library[1] = new Book(2, "9780451524935", "1984");
        library[2] = new Book(3, "9780743273565", "The Great Gatsby");
        library[3] = new Book(4, "9780140283334", "Of Mice and Men");
        library[4] = new Book(5, "9780307277671", "The Road");
        library[5] = new Book(6, "9780060850524", "Brave New World");
        library[6] = new Book(7, "9780307387899", "The Kite Runner");
        library[7] = new Book(8, "9780439064873", "Harry Potter and the Chamber of Secrets");
        library[8] = new Book(9, "9780545582889", "Harry Potter and the Deathly Hallows");
        library[9] = new Book(10, "9780618640157", "The Hobbit");
        library[10] = new Book(11, "9780140449136", "The Odyssey");
        library[11] = new Book(12, "9780142437230", "Moby-Dick");
        library[12] = new Book(13, "9780140177398", "Of Human Bondage");
        library[13] = new Book(14, "9780062024039", "The Alchemist");
        library[14] = new Book(15, "9780316769488", "The Catcher in the Rye");
        library[15] = new Book(16, "9780385472579", "Zen and the Art of Motorcycle Maintenance");
        library[16] = new Book(17, "9780374533557", "The Stranger");
        library[17] = new Book(18, "9780143105985", "Crime and Punishment");
        library[18] = new Book(19, "9780679734529", "Beloved");
        library[19] = new Book(20, "9780061122415", "Fahrenheit 451");

        return library;
    }
}
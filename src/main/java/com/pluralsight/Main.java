package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
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

        try {
            FileReader fr = new FileReader("books.txt");
            BufferedReader reader = new BufferedReader(fr);

            Book[] booksTemp = new Book[1000];
            int size = 0;
            String dataString;

            while ((dataString = reader.readLine()) != null) {

                booksTemp[size] = getBookFromEncodedString(dataString);

                size++;
            }

            Book[] booksFinal = Arrays.copyOf(booksTemp, size);
            return booksFinal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Book getBookFromEncodedString(String encodedBook) {

        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[1];

        Book result = new Book(id, isbn, title);
        return result;
    }
}
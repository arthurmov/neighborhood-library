package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    private static Console console = new Console();
    private static Book[] library = getPopulatedBooks();

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
                        System.out.println("\n[Showing available books...]\n");
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

        // show all the books that are currently available
        displayAvailableBooks();

        do {
            String userPrompt = ("\n╔═══════════════════════════════════════╗\n" +
                                   "║ 1. Check Out a Book                   ║\n" +
                                   "║ 0. Exit Back to Home Screen           ║\n" +
                                   "╚═══════════════════════════════════════╝\n" +
                                   "Please select an option: \n");

            option = console.promptForInt(userPrompt);

            switch (option) {
                case 1:
                    showScreenCheckOutBookYes();
                    break;
                case 0:
                    System.out.println("\n[Exiting back to home screen...]");
                    break;
                default:
                    System.out.println("\nInvalid option. Try again!");
            }
        } while (option != 0);
    }

    private static void showScreenCheckOutBookYes() {

        int bookId = console.promptForInt("Please enter the book Id: ");
        String name = console.promptforString("Please enter your name: ");

        Book theSelectedBook = getBookById(library, bookId);

        assert theSelectedBook != null;

        theSelectedBook.checkOut(name);

        System.out.printf("%s you have checkout out successfully.\n", name);
    }

    private static void displayAvailableBooks() {
        System.out.println(Book.getFormattedBookTextHeader());
        for (Book book : library) {
            if (!book.isCheckedOut()) {
                System.out.println(book.getFormattedBookText());
            }
        }
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
            String userPrompt = ("\n╔═══════════════════════════════════════╗\n" +
                                   "║ 1. Check In a Book                    ║\n" +
                                   "║ 0. Exit Back to Home Screen           ║\n" +
                                   "╚═══════════════════════════════════════╝\n" +
                                   "Please select an option: \n");

            option = console.promptForInt(userPrompt);

            switch (option) {
                case 1:
                    bookId = console.promptForInt("Please enter the ID of the book: ");

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

    private static Book getBookById(Book[] books, int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Book getBookFromEncodedString(String encodedBook) {

        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[2];

        Book result = new Book(id, isbn, title);
        return result;
    }
}
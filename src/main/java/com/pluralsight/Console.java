package com.pluralsight;

import java.util.Scanner;

public class Console {
    Scanner scanner = new Scanner(System.in);

    public int promptForInt(String prompt) {
        System.out.println(prompt);
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public String promptforString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}

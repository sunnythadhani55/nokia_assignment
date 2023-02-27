package com.nokia.menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected final Scanner scanner = new Scanner(System.in);

    //Displays Menu
    public abstract void display();

    //Takes User Input
    public abstract void handleUserChoice(int choice);

    //Returns List of submenus associated with the current menu
    public abstract List<Menu> getSubMenus();

    //Executes Menu Items for relevant menus
    public abstract void handleMenuItem();

    //Takes User Input/Choice
    public int getUserChoice() {
        int choice;
        do {
            choice = 0;
            System.out.print("\nEnter choice: ");
            try {
                choice = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        } while (choice == 0);

        return choice;
    }

    //Returns Menu Title
    public abstract String getTitle();
}
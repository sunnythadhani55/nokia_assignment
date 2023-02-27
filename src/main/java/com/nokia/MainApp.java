package com.nokia;

import com.nokia.menu.MainMenu;
import com.nokia.menu.Menu;

import java.util.Scanner;

public class MainApp {
    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Starting point of the Program
        startProgram();
    }

    private static void startProgram() {
        //Displays Main Menu, takes the user choice/input and navigate according to users selection
        Menu mainMenu = new MainMenu();
        int choice = 0;
        do {
            mainMenu.display();
            choice = mainMenu.getUserChoice();
            mainMenu.handleUserChoice(choice);
        } while (choice != mainMenu.getSubMenus().size());
    }
}
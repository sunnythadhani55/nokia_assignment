package com.nokia;

import com.nokia.menu.MainMenu;
import com.nokia.menu.Menu;
import java.util.Scanner;

public class MainApp
{
    private final static Scanner sc=new Scanner(System.in);

    public static void main( String[] args ) {startProgram();}

    private static void startProgram() {
        Menu mainMenu=new MainMenu();
        int choice=0;
        do{
            mainMenu.display();
            choice=mainMenu.getUserChoice();
            mainMenu.handleUserChoice(choice);
        }while(choice!=mainMenu.getSubMenus().size());
    }
}
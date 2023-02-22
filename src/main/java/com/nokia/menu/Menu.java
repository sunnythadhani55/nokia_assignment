package com.nokia.menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {
  protected final Scanner scanner = new Scanner(System.in);

  public abstract void display();

  public abstract void handleUserChoice(int choice);

  public abstract List<Menu> getSubMenus();
  public abstract void handleMenuItem();

  public int getUserChoice() {
    int choice;
    do{
      choice=0;
      System.out.print("\nEnter choice: ");
      try {
        choice = Integer.parseInt(String.valueOf(scanner.nextInt()));
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
        scanner.next();
      }
    }while(choice==0);

    return choice;
  }
  public abstract String getTitle();
}
package com.nokia.menu;

import com.nokia.menu.submenus.CompanyMenu;
import com.nokia.menu.submenus.DataMenu;
import com.nokia.menu.submenus.ExitMenu;
import com.nokia.menu.submenus.ManufacturersMenu;
import com.nokia.utils.Formatter;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {
  private final List<Menu> subMenus;

  public MainMenu() {
    this.subMenus = Arrays.asList(new DataMenu(),new ManufacturersMenu(),new CompanyMenu(),new ExitMenu());
  }

  @Override
  public void display() {
    System.out.println("Main Menu\n");
    for (int i = 0; i < subMenus.size(); i++) {
      System.out.println((i + 1) + ": " + subMenus.get(i).getTitle());
    }
  }

  @Override
  public void handleUserChoice(int choice) {

    if(choice==subMenus.size()){
      Formatter.printException("Exiting the Program...");
      return;
    }

    if (choice < 1 || choice > subMenus.size()) {
      String message=String.format("Invalid input. Please enter a number between 1 and %1$d.",subMenus.size());
      Formatter.printException(message);
      return;
    }
    Menu subMenu = subMenus.get(choice - 1);
    int subChoice=0;
    do{
       subMenu.display();
       subChoice= subMenu.getUserChoice();
       subMenu.handleUserChoice(subChoice);
    }while(subChoice!=subMenu.getSubMenus().size());
  }

  @Override
  public List<Menu> getSubMenus() {
    return subMenus;
  }

  @Override
  public void handleMenuItem() {}

  @Override
  public String getTitle() {
    return "Main";
  }
}
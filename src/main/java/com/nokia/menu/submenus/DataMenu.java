package com.nokia.menu.submenus;

import com.nokia.menu.Menu;
import com.nokia.menu.options.AddManufacturerMenu;
import com.nokia.menu.options.AddPartMenu;
import com.nokia.menu.options.BackMenu;
import com.nokia.menu.options.RemoveManufacturerMenu;
import com.nokia.utils.Formatter;
import java.util.Arrays;
import java.util.List;

public class DataMenu extends Menu {
  private final List<Menu> subMenus;

  public DataMenu() {
    this.subMenus = Arrays.asList(new AddPartMenu(),new AddManufacturerMenu(),new RemoveManufacturerMenu(),new BackMenu());
  }
  @Override
  public void display() {
    System.out.println("\nData Menu\n");
    for (int i = 0; i < subMenus.size(); i++) {
      System.out.println((i + 1) + ". " + subMenus.get(i).getTitle());
    }
  }
  @Override
  public void handleUserChoice(int choice) {
    if (choice < 1 || choice > subMenus.size()) {
      String message=String.format("Invalid input. Please enter a number between 1 and %1$d.",subMenus.size());
      Formatter.printException(message);
      return;
    }
    Menu subMenu = subMenus.get(choice - 1);
    subMenu.handleMenuItem();
  }

  @Override
  public List<Menu> getSubMenus() {
    return subMenus;
  }

  @Override
  public void handleMenuItem() {

  }

  @Override
  public String getTitle() {
    return "Data";
  }
}

package com.nokia.menu.submenus;

import com.nokia.menu.Menu;
import java.util.List;

public class ExitMenu extends Menu {

  @Override
  public void display() {}

  @Override
  public void handleUserChoice(int choice) {}

  @Override
  public List<Menu> getSubMenus() {
    return null;
  }

  @Override
  public void handleMenuItem() {}

  @Override
  public String getTitle() {
    return "Exit";
  }
}

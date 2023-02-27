package com.nokia.menu.submenus;

import com.nokia.menu.Menu;
import com.nokia.menu.options.AddQuantityMenu;
import com.nokia.menu.options.BackMenu;
import com.nokia.menu.options.ListQuantityMenu;
import com.nokia.utils.Formatter;

import java.util.Arrays;
import java.util.List;

public class ManufacturersMenu extends Menu {

    private final List<Menu> subMenus;

    public ManufacturersMenu() {
        this.subMenus = Arrays.asList(new AddQuantityMenu(), new ListQuantityMenu(), new BackMenu());
    }

    @Override
    public void display() {
        System.out.println("\nManufacturers Menu\n");
        for (int i = 0; i < subMenus.size(); i++) {
            System.out.println((i + 1) + ". " + subMenus.get(i).getTitle());
        }
    }

    @Override
    public void handleUserChoice(int choice) {
        if (choice < 1 || choice > subMenus.size()) {
            String message = String.format("Invalid input. Please enter a number between 1 and %1$d.", subMenus.size());
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
        return "Manufacturers";
    }
}

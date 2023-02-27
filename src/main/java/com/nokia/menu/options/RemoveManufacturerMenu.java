package com.nokia.menu.options;

import com.nokia.menu.Menu;
import com.nokia.service.ManufacturerService;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;

import java.util.List;

public class RemoveManufacturerMenu extends Menu {

    private final IOUtil ioUtil;
    private final ManufacturerService manufacturerService;

    public RemoveManufacturerMenu() {
        this.ioUtil = new IOUtil();
        this.manufacturerService = ServiceFactory.getManufacturerService();
    }

    @Override
    public void display() {
    }

    @Override
    public void handleUserChoice(int choice) {
    }

    @Override
    public List<Menu> getSubMenus() {
        return null;
    }

    @Override
    public void handleMenuItem() {
        String inputMessage = "Please Enter Manufacturer Name : ";
        String manufacturerName = ioUtil.getStringInput(inputMessage);

        manufacturerService.removeManufacturer(manufacturerName);
    }

    @Override
    public String getTitle() {
        return "Remove Manufacturer";
    }
}

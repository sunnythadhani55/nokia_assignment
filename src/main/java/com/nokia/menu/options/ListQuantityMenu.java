package com.nokia.menu.options;

import com.nokia.entity.PartManufacturer;
import com.nokia.menu.Menu;
import com.nokia.service.PartManufacturerService;
import com.nokia.utils.Formatter;
import com.nokia.utils.INPUT_MESSAGES_CONSTANTS;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;

import java.util.List;

public class ListQuantityMenu extends Menu {

    private final IOUtil ioUtil;

    private final PartManufacturerService partManufacturerService;

    public ListQuantityMenu() {
        this.ioUtil = new IOUtil();
        this.partManufacturerService = ServiceFactory.getPartManufacturerService();
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
        String partName = ioUtil.getStringInput(INPUT_MESSAGES_CONSTANTS.PART_NAME);
        String manufacturerName = ioUtil.getStringInput(INPUT_MESSAGES_CONSTANTS.MANUFACTURER_NAME);

        List<PartManufacturer> partManufacturerList = partManufacturerService.listQuantity(partName, manufacturerName);

        if (partManufacturerList.size() > 0) {
            displayPartManufacturers(partManufacturerList);
        }
    }

    private void displayPartManufacturers(List<PartManufacturer> partManufacturerList) {
        for (PartManufacturer partManufacturer : partManufacturerList) {
            Formatter.printSuccessfullExecution("\nPart name: " + partManufacturer.getPart().getName());
            Formatter.printSuccessfullExecution("Manufacturer name: " + partManufacturer.getManufacturer().getName());
            Formatter.printSuccessfullExecution(String.format("Price: %1$f, Quantity: %2$d", partManufacturer.getPrice(), partManufacturer.getQuantity()));
        }
        System.out.println();
    }

    @Override
    public String getTitle() {
        return "List Quantity";
    }
}

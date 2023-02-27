package com.nokia.menu.options;

import com.nokia.menu.Menu;
import com.nokia.service.PartManufacturerService;
import com.nokia.utils.INPUT_MESSAGES_CONSTANTS;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;

import java.util.List;

public class AddQuantityMenu extends Menu {

    private final IOUtil ioUtil;

    private final PartManufacturerService partManufacturerService;

    public AddQuantityMenu() {
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
        int quantity = ioUtil.getIntegerInput(INPUT_MESSAGES_CONSTANTS.QUANTITY);
        Float price = ioUtil.getFloatInput(INPUT_MESSAGES_CONSTANTS.PRICE);

        partManufacturerService.addQuantity(partName, manufacturerName, quantity, price);
    }

    @Override
    public String getTitle() {
        return "Add Quantity";
    }
}

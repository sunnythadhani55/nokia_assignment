package com.nokia.menu.options;

import com.nokia.menu.Menu;
import com.nokia.service.CompanyStockService;
import com.nokia.utils.INPUT_MESSAGES_CONSTANTS;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;

import java.util.List;

public class BuyPartsMenu extends Menu {
    private final IOUtil ioUtil;
    private final CompanyStockService companyStockService;

    public BuyPartsMenu() {
        this.ioUtil = new IOUtil();
        this.companyStockService = ServiceFactory.getCompanyStockService();
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
        int quantity = ioUtil.getIntegerInput(INPUT_MESSAGES_CONSTANTS.QUANTITY);

        companyStockService.buyParts(partName, quantity);
    }

    @Override
    public String getTitle() {
        return "Buy Parts";
    }
}

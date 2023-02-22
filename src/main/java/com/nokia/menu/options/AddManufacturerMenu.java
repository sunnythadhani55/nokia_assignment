package com.nokia.menu.options;

import com.nokia.menu.Menu;
import com.nokia.service.ManufacturerService;
import com.nokia.utils.INPUT_MESSAGES_CONSTANTS;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;
import java.util.List;

public class AddManufacturerMenu extends Menu {

  private IOUtil ioUtil;
  private ManufacturerService manufacturerService;

  public AddManufacturerMenu() {
    this.ioUtil = new IOUtil();;
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
    String manufacturerName=ioUtil.getStringInput(INPUT_MESSAGES_CONSTANTS.MANUFACTURER_NAME);
    manufacturerService.addManufacturer(manufacturerName);
  }

  @Override
  public String getTitle() {
    return "Add Manufacturer";
  }
}

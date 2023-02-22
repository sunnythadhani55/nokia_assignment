package com.nokia.menu.options;

import com.nokia.menu.Menu;
import com.nokia.service.PartService;
import com.nokia.utils.INPUT_MESSAGES_CONSTANTS;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;
import java.util.List;
import java.util.Scanner;

public class AddPartMenu extends Menu {

  protected final Scanner scanner = new Scanner(System.in);

  private IOUtil ioUtil;
  private PartService partService;

  public AddPartMenu(){
    this.ioUtil=new IOUtil();
    this.partService= ServiceFactory.getPartService();
  }

  @Override
  public void display() {}

  @Override
  public void handleUserChoice(int choice) {}

  @Override
  public List<Menu> getSubMenus() {
    return null;
  }

  @Override
  public void handleMenuItem() {
    String partName=ioUtil.getStringInput(INPUT_MESSAGES_CONSTANTS.PART_NAME);
    partService.addPart(partName);
  }

  @Override
  public String getTitle() {
    return "Add Part";
  }
}

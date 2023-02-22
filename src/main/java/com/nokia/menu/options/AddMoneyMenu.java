package com.nokia.menu.options;

import com.nokia.menu.Menu;
import com.nokia.service.CompanyService;
import com.nokia.utils.INPUT_MESSAGES_CONSTANTS;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;
import java.util.List;

public class AddMoneyMenu extends Menu {
  private IOUtil ioUtil;
  private CompanyService companyService;

  public AddMoneyMenu() {
    this.ioUtil = new IOUtil();
    this.companyService= ServiceFactory.getCompanyService();
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
    Double amount=ioUtil.getDoubleInput(INPUT_MESSAGES_CONSTANTS.AMOUNT);
    companyService.addMoney(amount);
  }

  @Override
  public String getTitle() {
    return "Add Money";
  }
}

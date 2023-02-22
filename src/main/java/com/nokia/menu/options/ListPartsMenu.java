package com.nokia.menu.options;

import com.nokia.entity.CompanyStock;
import com.nokia.menu.Menu;
import com.nokia.service.CompanyStockService;
import com.nokia.utils.Formatter;
import com.nokia.utils.IOUtil;
import com.nokia.utils.ServiceFactory;
import java.util.List;

public class ListPartsMenu extends Menu {

  private IOUtil ioUtil;
  private CompanyStockService companyStockService;

  public ListPartsMenu(){
    this.ioUtil=new IOUtil();
    this.companyStockService= ServiceFactory.getCompanyStockService();
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
    List<CompanyStock> companyStockList=companyStockService.listParts();

    if(companyStockList.size()>0){
      displayCompanyStocks(companyStockList);
    }
  }

  private void displayCompanyStocks(List<CompanyStock> companyStockList) {
    System.out.println();
    for (CompanyStock companyStock : companyStockList){
      Formatter.printSuccessfullExecution(String.format("Part: %1$s, manufacturer: %2$s, quantity: %3$d",
          companyStock.getPart().getName(),companyStock.getManufacturer().getName(),companyStock.getQuantity()));
    }
  }

  @Override
  public String getTitle() {
    return "List Parts";
  }
}

package com.nokia.business;

import com.nokia.dao.CompanyStockDAO;
import com.nokia.dao.CompanyStockDAOImpl;
import com.nokia.entity.Company;
import com.nokia.entity.CompanyStock;
import com.nokia.entity.PartManufacturer;
import com.nokia.utils.Formatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyStockBusinessImpl implements CompanyStockBusiness {
  private CompanyStockDAO companyStockDAO;

  public CompanyStockBusinessImpl(CompanyStockDAOImpl companyStockDAO) {
    this.companyStockDAO = companyStockDAO;
  }
  @Override
  public List<PartManufacturer> getBoughtPartManufacturers(List<PartManufacturer> partManufacturerList, Company company, int required_quantity) {
    List<PartManufacturer> boughtPartManufacturerList=new ArrayList<>();
    Double totalCost=0.0;

    partManufacturerList.sort((a, b) ->
        Float.compare(a.getPrice(), b.getPrice())
    );

    for(PartManufacturer partManufacturer : partManufacturerList){
      int partsToBuy = Math.min(partManufacturer.getQuantity(), required_quantity);

      boughtPartManufacturerList.add(new PartManufacturer(partManufacturer.getId(),partManufacturer.getPart(),
          partManufacturer.getManufacturer(),partsToBuy,partManufacturer.getPrice()));
      required_quantity-=partsToBuy;
      totalCost+=partsToBuy*partManufacturer.getPrice();

      if(required_quantity==0)
        break;
      }

      if(required_quantity>0){
        Formatter.printException("Enough parts not available");
        return new ArrayList<>();
      }
      else if(totalCost>company.getBalance()){
        Formatter.printException("Not enough money to buy all the parts");
        return new ArrayList<>();
      }

      return boughtPartManufacturerList;
  }

  @Override
  public CompanyStock getCompanyStock(CompanyStock inputCompanyStock) {
    Optional<CompanyStock> fetchedCompanyStock=companyStockDAO.getByPartNameAndManufacturerName
        (inputCompanyStock.getPart().getName(),inputCompanyStock.getManufacturer().getName());
    if(fetchedCompanyStock.isPresent()){
      fetchedCompanyStock.get().setQuantity(fetchedCompanyStock.get().getQuantity()+inputCompanyStock.getQuantity());
      return fetchedCompanyStock.get();
    }
    return inputCompanyStock;
  }

}

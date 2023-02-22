package com.nokia.service;

import com.nokia.business.CompanyStockBusiness;
import com.nokia.business.CompanyStockBusinessImpl;
import com.nokia.business.PartManufacturerBusiness;
import com.nokia.business.PartManufacturerBusinessImpl;
import com.nokia.dao.CompanyDAO;
import com.nokia.dao.CompanyDAOImpl;
import com.nokia.dao.CompanyStockDAO;
import com.nokia.dao.CompanyStockDAOImpl;
import com.nokia.dao.PartManufacturerDAO;
import com.nokia.dao.PartManufacturerDAOImpl;
import com.nokia.entity.Company;
import com.nokia.entity.CompanyStock;
import com.nokia.entity.PartManufacturer;
import com.nokia.utils.Formatter;
import java.util.List;
import javax.swing.FocusManager;

public class CompanyStockServiceImpl implements CompanyStockService {
  private final PartManufacturerDAO partManufacturerDAO;
  private final CompanyStockDAO companyStockDAO;
  private final CompanyStockBusiness companyStockBusiness;
  private final PartManufacturerBusiness partManufacturerBusiness;
  private final CompanyDAO companyDAO;

  public CompanyStockServiceImpl(PartManufacturerDAOImpl partManufacturerDAO, CompanyDAOImpl companyDAO,
      CompanyStockDAOImpl companyStockDAO, CompanyStockBusinessImpl companyStockBusiness,
      PartManufacturerBusinessImpl partManufacturerBusiness) {
    this.partManufacturerDAO = partManufacturerDAO;
    this.companyDAO=companyDAO;
    this.partManufacturerBusiness=partManufacturerBusiness;
    this.companyStockDAO = companyStockDAO;
    this.companyStockBusiness = companyStockBusiness;
  }

  @Override
  public void buyParts(String partName, int required_quantity) {
    Company company=companyDAO.getCompany();
    List<PartManufacturer> partManufacturerList=partManufacturerDAO.listQuantity(partName,"");
    List<PartManufacturer> boughtPartManufacturersList=companyStockBusiness.getBoughtPartManufacturers(partManufacturerList,company,required_quantity);

    if(boughtPartManufacturersList.size()!=0){
      Double totalMoneySpent=0.0;

      System.out.println();

      for(PartManufacturer boughtPartManufacturer : boughtPartManufacturersList){
        totalMoneySpent+=boughtPartManufacturer.getQuantity()*boughtPartManufacturer.getPrice();
        PartManufacturer updatedPartManufacturer =partManufacturerBusiness.getPartManufacturerWithUpdatedQuantiity(partManufacturerList,boughtPartManufacturer);

        CompanyStock inputCompanyStock=new CompanyStock(null,boughtPartManufacturer.getPart(),boughtPartManufacturer.getManufacturer()
                  ,company,boughtPartManufacturer.getQuantity());

        CompanyStock companyStock =companyStockBusiness.getCompanyStock(inputCompanyStock);

        partManufacturerDAO.savePartManufacturer(updatedPartManufacturer);
        companyStockDAO.saveCompanyStock(companyStock);

        String message=String.format("%1$d quantity is taken from %2$s and added to the company",
            boughtPartManufacturer.getQuantity(),boughtPartManufacturer.getManufacturer().getName());
        Formatter.printSuccessfullExecution(message);
      }
      company.setBalance(company.getBalance()-totalMoneySpent);
      companyDAO.updateBalance(company);
    }
  }

  @Override
  public List<CompanyStock> listParts() {
    return companyStockDAO.listPart(companyDAO.getCompany());
  }
}

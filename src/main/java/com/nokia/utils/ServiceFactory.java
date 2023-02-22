package com.nokia.utils;

import com.nokia.business.CompanyStockBusinessImpl;
import com.nokia.business.ManufacturerBusinessImpl;
import com.nokia.business.PartBusinessImpl;
import com.nokia.business.PartManufacturerBusinessImpl;
import com.nokia.dao.CompanyDAOImpl;
import com.nokia.dao.CompanyStockDAOImpl;
import com.nokia.dao.ManufacturerDAOImpl;
import com.nokia.dao.PartDAOImpl;
import com.nokia.dao.PartManufacturerDAOImpl;
import com.nokia.service.CompanyService;
import com.nokia.service.CompanyServiceImpl;
import com.nokia.service.CompanyStockService;
import com.nokia.service.CompanyStockServiceImpl;
import com.nokia.service.ManufacturerService;
import com.nokia.service.ManufacturerServiceImpl;
import com.nokia.service.PartManufacturerService;
import com.nokia.service.PartManufacturerServiceImpl;
import com.nokia.service.PartService;
import com.nokia.service.PartServiceImpl;

public class ServiceFactory {

  private static CompanyService companyService=null;

  private static CompanyStockService companyStockService=null;

  private static ManufacturerService manufacturerService=null;

  private static PartService partService=null;

  private static PartManufacturerService partManufacturerService=null;

  public static CompanyService getCompanyService(){
    if(companyService==null){
      companyService=new CompanyServiceImpl((CompanyDAOImpl) DAOFactory.getCompanyDAO());
    }
    return companyService;
  }

  public static CompanyStockService getCompanyStockService(){
    if(companyStockService==null){
      companyStockService=new CompanyStockServiceImpl(
          (PartManufacturerDAOImpl) DAOFactory.getPartManufacturerDAO(),
          (CompanyDAOImpl) DAOFactory.getCompanyDAO(),
          (CompanyStockDAOImpl) DAOFactory.getCompanyStockDAO(),
          (CompanyStockBusinessImpl) BusinessFactory.getCompanyStockBusiness(),
          (PartManufacturerBusinessImpl) BusinessFactory.getPartManufacturerBusiness());
    }
    return companyStockService;
  }

  public static ManufacturerService getManufacturerService(){
    if(manufacturerService==null){
      manufacturerService=new ManufacturerServiceImpl(
          (ManufacturerDAOImpl) DAOFactory.getManufacturerDAO(),
          (ManufacturerBusinessImpl) BusinessFactory.getManufacturerBusiness(),
          (PartDAOImpl) DAOFactory.getPartDAO());
    }
    return manufacturerService;
  }

  public static PartService getPartService(){
    if(partService==null){
      partService=new PartServiceImpl((PartDAOImpl) DAOFactory.getPartDAO(),
          (PartBusinessImpl) BusinessFactory.getPartBusiness());
    }
    return partService;
  }

  public static PartManufacturerService getPartManufacturerService(){
    if(partManufacturerService==null){
      partManufacturerService=new PartManufacturerServiceImpl(
          (PartManufacturerDAOImpl) DAOFactory.getPartManufacturerDAO(),
          (PartManufacturerBusinessImpl) BusinessFactory.getPartManufacturerBusiness(),
          (ManufacturerDAOImpl) DAOFactory.getManufacturerDAO(),
          (PartDAOImpl) DAOFactory.getPartDAO());
    }
    return partManufacturerService;
  }
}

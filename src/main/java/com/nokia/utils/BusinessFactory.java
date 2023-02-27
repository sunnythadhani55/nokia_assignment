package com.nokia.utils;

import com.nokia.business.*;
import com.nokia.dao.CompanyStockDAOImpl;
import com.nokia.dao.ManufacturerDAOImpl;
import com.nokia.dao.PartDAOImpl;
import com.nokia.dao.PartManufacturerDAOImpl;

public class BusinessFactory {

    private static CompanyStockBusiness companyStockBusiness = null;

    private static ManufacturerBusiness manufacturerBusiness = null;

    private static PartBusiness partBusiness = null;

    private static PartManufacturerBusiness partManufacturerBusiness = null;

    public static CompanyStockBusiness getCompanyStockBusiness() {
        if (companyStockBusiness == null) {
            companyStockBusiness = new CompanyStockBusinessImpl(
                    (CompanyStockDAOImpl) DAOFactory.getCompanyStockDAO());
        }
        return companyStockBusiness;
    }

    public static ManufacturerBusiness getManufacturerBusiness() {
        if (manufacturerBusiness == null) {
            manufacturerBusiness = new ManufacturerBusinessImpl(
                    (ManufacturerDAOImpl) DAOFactory.getManufacturerDAO());
        }
        return manufacturerBusiness;
    }

    public static PartBusiness getPartBusiness() {
        if (partBusiness == null) {
            partBusiness = new PartBusinessImpl((PartDAOImpl) DAOFactory.getPartDAO());
        }
        return partBusiness;
    }

    public static PartManufacturerBusiness getPartManufacturerBusiness() {
        if (partManufacturerBusiness == null) {
            partManufacturerBusiness = new PartManufacturerBusinessImpl(
                    (PartDAOImpl) DAOFactory.getPartDAO(),
                    (ManufacturerDAOImpl) DAOFactory.getManufacturerDAO(),
                    (PartManufacturerDAOImpl) DAOFactory.getPartManufacturerDAO());
        }
        return partManufacturerBusiness;
    }
}

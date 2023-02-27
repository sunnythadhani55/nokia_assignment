package com.nokia.utils;

import com.nokia.dao.*;

public class DAOFactory {

    private static CompanyDAO companyDAO = null;

    private static CompanyStockDAO companyStockDAO = null;

    private static ManufacturerDAO manufacturerDAO = null;

    private static PartDAO partDAO = null;

    private static PartManufacturerDAO partManufacturerDAO = null;

    public static CompanyDAO getCompanyDAO() {
        if (companyDAO == null) {
            companyDAO = new CompanyDAOImpl();
        }
        return companyDAO;
    }

    public static CompanyStockDAO getCompanyStockDAO() {
        if (companyStockDAO == null) {
            companyStockDAO = new CompanyStockDAOImpl();
        }
        return companyStockDAO;
    }

    public static ManufacturerDAO getManufacturerDAO() {
        if (manufacturerDAO == null) {
            manufacturerDAO = new ManufacturerDAOImpl();
        }
        return manufacturerDAO;
    }

    public static PartDAO getPartDAO() {
        if (partDAO == null) {
            partDAO = new PartDAOImpl();
        }
        return partDAO;
    }

    public static PartManufacturerDAO getPartManufacturerDAO() {
        if (partManufacturerDAO == null) {
            partManufacturerDAO = new PartManufacturerDAOImpl();
        }
        return partManufacturerDAO;
    }

}

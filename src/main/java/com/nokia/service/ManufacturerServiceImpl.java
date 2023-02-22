package com.nokia.service;

import com.nokia.business.ManufacturerBusiness;
import com.nokia.business.ManufacturerBusinessImpl;
import com.nokia.dao.ManufacturerDAO;
import com.nokia.dao.ManufacturerDAOImpl;
import com.nokia.dao.PartDAO;
import com.nokia.dao.PartDAOImpl;
import com.nokia.entity.Manufacturer;
import com.nokia.utils.Formatter;

public class ManufacturerServiceImpl implements ManufacturerService{
    private final ManufacturerDAO manufacturerDAO;
    private final ManufacturerBusiness manufacturerBusiness;
    private final PartDAO partDAO;

    public ManufacturerServiceImpl(ManufacturerDAOImpl manufacturerDAO, ManufacturerBusinessImpl manufacturerBusiness,
        PartDAOImpl partDAO) {
        this.manufacturerDAO = manufacturerDAO;
        this.manufacturerBusiness=manufacturerBusiness;
        this.partDAO = partDAO;
    }

    @Override
    public void addManufacturer(String manufacturerName) {
        Manufacturer manufacturer=manufacturerBusiness.getManufacturer(manufacturerName);

        if(manufacturer.getId()==null){
            manufacturerDAO.saveManufacturer(manufacturer);
            Formatter.printSuccessfullExecution("\nManufacturer added successfully");
        }else{
            String message=String.format("Duplicate Manufacturer '%s' can't be added",manufacturerName);
            Formatter.printException(message);
        }
    }

    @Override
    public void removeManufacturer(String manufacturerName) {
        Manufacturer manufacturer=manufacturerBusiness.getManufacturer(manufacturerName);

        if(manufacturer.getId()==null){
            String message=String.format("Manufacturer '%s' can't be deleted, as Manufacturer does not exists",manufacturerName);
            Formatter.printException(message);
        }else{
            manufacturerDAO.deleteManufacturer(manufacturer);
            Formatter.printSuccessfullExecution("Manufacturer removed successfully");
        }
    }

}

package com.nokia.business;

import com.nokia.dao.ManufacturerDAO;
import com.nokia.dao.ManufacturerDAOImpl;
import com.nokia.entity.Manufacturer;

import java.util.Optional;

public class ManufacturerBusinessImpl implements ManufacturerBusiness{
    private final ManufacturerDAO manufacturerDAO;
    public ManufacturerBusinessImpl(ManufacturerDAOImpl manufacturerDAO) {
        this.manufacturerDAO = manufacturerDAO;
    }

    @Override
    public Manufacturer getManufacturer(String manufacturerName) {
        Optional<Manufacturer> manufacturer=manufacturerDAO.getByName(manufacturerName);

        Manufacturer newManufacturer=null;
        if(manufacturer.isEmpty()){
            newManufacturer=new Manufacturer(null,manufacturerName,null);
        }
        else
            newManufacturer=manufacturer.get();
        return newManufacturer;
    }
}

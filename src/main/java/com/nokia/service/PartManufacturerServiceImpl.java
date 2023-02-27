package com.nokia.service;

import com.nokia.business.PartManufacturerBusiness;
import com.nokia.business.PartManufacturerBusinessImpl;
import com.nokia.dao.*;
import com.nokia.entity.Manufacturer;
import com.nokia.entity.Part;
import com.nokia.entity.PartManufacturer;
import com.nokia.utils.Formatter;

import java.util.List;
import java.util.Optional;

public class PartManufacturerServiceImpl implements PartManufacturerService {
    private final PartManufacturerDAO partManufacturerDAO;
    private final PartManufacturerBusiness partManufacturerBusiness;
    private final ManufacturerDAO manufacturerDAO;
    private final PartDAO partDAO;

    public PartManufacturerServiceImpl(PartManufacturerDAOImpl partManufacturerDAO,
                                       PartManufacturerBusinessImpl partManufacturerBusiness, ManufacturerDAOImpl manufacturerDAO,
                                       PartDAOImpl partDAO) {
        this.partManufacturerDAO = partManufacturerDAO;
        this.partManufacturerBusiness = partManufacturerBusiness;
        this.manufacturerDAO = manufacturerDAO;
        this.partDAO = partDAO;
    }

    @Override
    public void addQuantity(String partName, String manufacturerName, int quantity, float price) {
        Optional<Part> part = getPart(partName);
        Optional<Manufacturer> manufacturer = getManufacturer(manufacturerName);

        if (part.isEmpty()) {
            String message = String.format("Part with Part Name '%s' does not exist", partName);
            Formatter.printException(message);
            return;
        }

        if (manufacturer.isEmpty()) {
            String message = String.format("Manufacturer with Manufacturer Name '%s' does not exist", manufacturerName);
            Formatter.printException(message);
            return;
        }

        PartManufacturer inputPartManufacturer = new PartManufacturer(null, part.get(), manufacturer.get(), quantity, price);
        PartManufacturer partManufacturer = partManufacturerBusiness.getPartManufacturer(inputPartManufacturer);
        try {
            partManufacturerDAO.savePartManufacturer(partManufacturer);
            Formatter.printSuccessfullExecution("\nQuantity updated successfully");
        } catch (Exception e) {
//      String exceptionMessage=String.format("Part with Part Name '%s' does not exist",partName);
//      Formatter.printException(exceptionMessage);
            e.printStackTrace();
        }
    }

    @Override
    public List<PartManufacturer> listQuantity(String partName, String manufacturerName) {
        List<PartManufacturer> partManufacturerList = partManufacturerBusiness.getPartManufacturerList(partName, manufacturerName);
        return partManufacturerList;
    }

    public Optional<Part> getPart(String partName) {
        try {
            return partDAO.getByName(partName);
        } catch (Exception ex) {
            String exceptionMessage = String.format("Part with Name '%s' does not exist", partName);
            Formatter.printException(exceptionMessage);
            return Optional.empty();
        }
    }

    public Optional<Manufacturer> getManufacturer(String manufacturerName) {
        try {
            return manufacturerDAO.getByName(manufacturerName);
        } catch (Exception ex) {
            String exceptionMessage = String.format("Manufacturer with Name '%s' does not exist", manufacturerName);
            Formatter.printException(exceptionMessage);
            return Optional.empty();
        }
    }

}

package com.nokia.business;

import com.nokia.dao.*;
import com.nokia.entity.Manufacturer;
import com.nokia.entity.Part;
import com.nokia.entity.PartManufacturer;
import com.nokia.utils.Formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PartManufacturerBusinessImpl implements PartManufacturerBusiness {
    private final PartDAO partDAO;
    private final ManufacturerDAO manufacturerDAO;

    private final PartManufacturerDAO partManufacturerDAO;

    public PartManufacturerBusinessImpl(PartDAOImpl partDAO, ManufacturerDAOImpl manufacturerDAO,
                                        PartManufacturerDAOImpl partManufacturerDAO) {
        this.partDAO = partDAO;
        this.manufacturerDAO = manufacturerDAO;
        this.partManufacturerDAO = partManufacturerDAO;
    }

    public PartManufacturer getPartManufacturer(PartManufacturer inputPartManufacturer) {
        PartManufacturer newPartManufacturer = null;

        String partName = inputPartManufacturer.getPart().getName();
        String manufacturerName = inputPartManufacturer.getManufacturer().getName();

        try {
            Optional<PartManufacturer> fetchedPartManufacturer = getPartManufacturer(partName, manufacturerName);
            newPartManufacturer = processPartManufacturerQuantity(fetchedPartManufacturer, inputPartManufacturer);
            return newPartManufacturer;
        } catch (Exception e) {
            return newPartManufacturer;
        }
    }

    @Override
    public PartManufacturer processPartManufacturerQuantity(Optional<PartManufacturer> fetchedPartManufacturer, PartManufacturer inputPartManufacturer) {
        if (fetchedPartManufacturer.isPresent()) {
            fetchedPartManufacturer.get().setQuantity(
                    fetchedPartManufacturer.get().getQuantity() + inputPartManufacturer.getQuantity());
            fetchedPartManufacturer.get().setPrice(inputPartManufacturer.getPrice());
            return fetchedPartManufacturer.get();
        } else {
            return inputPartManufacturer;
        }
    }

    @Override
    public List<PartManufacturer> getPartManufacturerList(String partName,
                                                          String manufacturerName) {

        if (partName.isEmpty()) {
            Formatter.printException("Part Name can't be empty ! Please enter Part Name");
            return new ArrayList<>();
        }

        Optional<Part> part = getPart(partName);
        Optional<Manufacturer> manufacturer = getManufacturer(manufacturerName);

        if (part.isEmpty()) {
            String message = String.format("Part with Name '%s' does not exists", partName);
            Formatter.printException(message);
            return new ArrayList<>();
        }

        List<PartManufacturer> partManufacturerList;
        if (manufacturer.isPresent())
            partManufacturerList = partManufacturerDAO.listQuantity(partName, manufacturerName);
        else
            partManufacturerList = partManufacturerDAO.listQuantity(partName, "");

        return partManufacturerList;
    }

    @Override
    public PartManufacturer getPartManufacturerWithUpdatedQuantiity(List<PartManufacturer> partManufacturerList,
                                                                    PartManufacturer boughtPartManufacturer) {

        for (PartManufacturer partManufacturer : partManufacturerList) {
            if (partManufacturer.getId() == boughtPartManufacturer.getId()) {
                partManufacturer.setQuantity(partManufacturer.getQuantity() - boughtPartManufacturer.getQuantity());
                return partManufacturer;
            }
        }
        return null;
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

    public Optional<PartManufacturer> getPartManufacturer(String partName, String manufacturerName) {
        try {
            return partManufacturerDAO.getByPartAndManufacturer(partName, manufacturerName);
        } catch (Exception ex) {
            String exceptionMessage = String.format("PartManufacturer with Part name '%1$s' and Manufacturer name '%2$s' does not exist", partName, manufacturerName);
            Formatter.printException(exceptionMessage);
            return Optional.empty();
        }
    }
}

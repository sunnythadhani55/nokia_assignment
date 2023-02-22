package com.nokia.dao;

import com.nokia.entity.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerDAO {
    public void saveManufacturer(Manufacturer manufacturer);
    public void deleteManufacturer(Manufacturer manufacturer);
    public Optional<Manufacturer> getByName(String manufaturerName);
    public List<Manufacturer> getAll();

}

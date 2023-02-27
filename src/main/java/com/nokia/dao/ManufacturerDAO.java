package com.nokia.dao;

import com.nokia.entity.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerDAO {
    void saveManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(Manufacturer manufacturer);

    Optional<Manufacturer> getByName(String manufaturerName);

    List<Manufacturer> getAll();

}

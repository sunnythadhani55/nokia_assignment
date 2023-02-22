package com.nokia.dao;

import com.nokia.entity.PartManufacturer;
import java.util.List;
import java.util.Optional;

public interface PartManufacturerDAO {
    Optional<PartManufacturer> getByPartAndManufacturer(String partName, String manufacturerName);
    void savePartManufacturer(PartManufacturer newPartManufacturer);

    List<PartManufacturer> listQuantity(String partName, String manufacturerName);
}

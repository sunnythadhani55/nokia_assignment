package com.nokia.business;

import com.nokia.entity.Manufacturer;
import com.nokia.entity.Part;
import com.nokia.entity.PartManufacturer;

import java.util.List;
import java.util.Optional;

public interface PartManufacturerBusiness {
    PartManufacturer getPartManufacturer(PartManufacturer inputPartManufacturer);

    PartManufacturer processPartManufacturerQuantity(
            Optional<PartManufacturer> fetchedPartManufacturer,
            PartManufacturer inputPartManufacturer);

    Optional<Manufacturer> getManufacturer(String manufacturerName);

    Optional<Part> getPart(String partName);

    List<PartManufacturer> getPartManufacturerList(String partName, String manufacturerName);

    PartManufacturer getPartManufacturerWithUpdatedQuantiity(
            List<PartManufacturer> partManufacturerList,
            PartManufacturer boughtPartManufacturers);
}

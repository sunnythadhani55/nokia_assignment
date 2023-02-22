package com.nokia.business;

import com.nokia.entity.Manufacturer;
import com.nokia.entity.Part;

import java.util.List;
import java.util.Optional;

public interface ManufacturerBusiness {
    public Manufacturer getManufacturer(String manufacturerName);
}

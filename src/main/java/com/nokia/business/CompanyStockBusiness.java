package com.nokia.business;

import com.nokia.entity.Company;
import com.nokia.entity.CompanyStock;
import com.nokia.entity.PartManufacturer;

import java.util.List;

public interface CompanyStockBusiness {
    List<PartManufacturer> getBoughtPartManufacturers(
            List<PartManufacturer> partManufacturerList,
            Company company,
            int required_quantity);

    CompanyStock getCompanyStock(CompanyStock inputCompanyStock);
}

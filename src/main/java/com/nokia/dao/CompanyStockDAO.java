package com.nokia.dao;

import com.nokia.entity.Company;
import com.nokia.entity.CompanyStock;
import java.util.List;
import java.util.Optional;

public interface CompanyStockDAO {

  Optional<CompanyStock> getByPartNameAndManufacturerName(String partName, String manufacturerName);

  void saveCompanyStock(CompanyStock companyStock);

  List<CompanyStock> listPart(Company company);
}

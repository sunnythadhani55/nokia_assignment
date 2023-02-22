package com.nokia.dao;

import com.nokia.entity.Company;
import com.nokia.entity.CompanyStock;

public interface CompanyDAO {
  Company getCompany();
  void updateBalance(Company company);
}

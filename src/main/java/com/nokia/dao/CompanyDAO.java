package com.nokia.dao;

import com.nokia.entity.Company;

public interface CompanyDAO {
    Company getCompany();

    void updateBalance(Company company);
}

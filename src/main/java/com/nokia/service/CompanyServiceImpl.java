package com.nokia.service;

import com.nokia.dao.CompanyDAO;
import com.nokia.dao.CompanyDAOImpl;
import com.nokia.entity.Company;
import com.nokia.utils.Formatter;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyDAO companyDAO;

    public CompanyServiceImpl(CompanyDAOImpl companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    public void addMoney(Double money) {

        try {
            Company company = companyDAO.getCompany();
            double newBalance = company.getBalance() + money;
            company.setBalance(newBalance);
            companyDAO.updateBalance(company);
            Formatter.printSuccessfullExecution("Balance added successfullly....");
            Formatter.printSuccessfullExecution("Updated balance: " + newBalance);
        } catch (Exception ex) {
            Formatter.printException("Balance could not be updated");
        }
    }
}

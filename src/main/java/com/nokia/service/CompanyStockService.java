package com.nokia.service;

import com.nokia.entity.CompanyStock;

import java.util.List;

public interface CompanyStockService {

    void buyParts(String partName, int quantity);

    List<CompanyStock> listParts();
}

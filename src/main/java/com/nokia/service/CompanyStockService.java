package com.nokia.service;

import com.nokia.entity.CompanyStock;
import java.util.List;

public interface CompanyStockService {

  public void buyParts(String partName, int quantity);

  public List<CompanyStock> listParts();
}

package com.nokia.service;

import com.nokia.entity.PartManufacturer;
import java.util.List;

public interface PartManufacturerService {
  public void addQuantity(String partName, String manufacturerName, int quantity, float price);

  public List<PartManufacturer> listQuantity(String partName, String manufacturerName);
}

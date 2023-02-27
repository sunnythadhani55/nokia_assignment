package com.nokia.business;

import com.nokia.dao.CompanyStockDAOImpl;
import com.nokia.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CompanyStockBusinessTest {

    CompanyStockDAOImpl companyStockDAO;
    CompanyStockBusinessImpl companyStockBusiness;
    Part part;
    Manufacturer manufacturer;
    Company company;
    List<Manufacturer> manufacturerList;
    List<PartManufacturer> partManufacturerList;

    @Before
    public void setup() {
        companyStockDAO = Mockito.mock(CompanyStockDAOImpl.class);
        companyStockBusiness = new CompanyStockBusinessImpl(companyStockDAO);

        part = new Part(1L, "Part 1", null);
        manufacturer = new Manufacturer(1L, "Manufacturer 1", null);

        company = new Company(1L, 140.0, "Nokia", null);

        manufacturerList = Arrays.asList(
                new Manufacturer(1L, "Manufacturer 1", null),
                new Manufacturer(2L, "Manufacturer 2", null),
                new Manufacturer(3L, "Manufacturer 3", null),
                new Manufacturer(4L, "Manufacturer 4", null)
        );

        partManufacturerList = Arrays.asList(
                new PartManufacturer(1L, part, manufacturerList.get(0), 10, 15f),
                new PartManufacturer(2L, part, manufacturerList.get(1), 6, 7.5f),
                new PartManufacturer(3L, part, manufacturerList.get(2), 5, 6.7f),
                new PartManufacturer(4L, part, manufacturerList.get(3), 12, 8.5f)
        );
    }

    @Test
    public void testGetBoughtPartManufacturers_When_Enough_Parts_And_Balance_Available_Return_BoughtPartManufacturerList() {
        int required_quantity = 13;

        List<PartManufacturer> expectedBoughtPartManufacturerList = Arrays.asList(
                new PartManufacturer(3L, part, manufacturerList.get(2), 5, 6.7f),
                new PartManufacturer(2L, part, manufacturerList.get(1), 6, 7.5f),
                new PartManufacturer(4L, part, manufacturerList.get(3), 2, 8.5f)
        );

        List<PartManufacturer> actualBoughtPartManufacturerList =
                companyStockBusiness.getBoughtPartManufacturers(partManufacturerList, company, required_quantity);

        Assert.assertEquals(expectedBoughtPartManufacturerList.size(), actualBoughtPartManufacturerList.size());
        Assert.assertNotEquals(actualBoughtPartManufacturerList.size(), 0);
        Assert.assertEquals(expectedBoughtPartManufacturerList, actualBoughtPartManufacturerList);
    }

    @Test
    public void testGetBoughtPartManufacturers_When_Enough_Parts_Are_Not_Available_Return_Empty_PartManufacturerList() {
        int required_quantity = 40;

        List<PartManufacturer> expectedBoughtPartManufacturerList = new ArrayList<>();
        List<PartManufacturer> actualBoughtPartManufacturerList =
                companyStockBusiness.getBoughtPartManufacturers(partManufacturerList, company, required_quantity);

        Assert.assertEquals(expectedBoughtPartManufacturerList.size(), actualBoughtPartManufacturerList.size());
        Assert.assertEquals(expectedBoughtPartManufacturerList, actualBoughtPartManufacturerList);
    }

    @Test
    public void testGetBoughtPartManufacturers_When_Enough_Balance_Is_Not_Available_Return_Empty_PartManufacturerList() {
        int required_quantity = 20;

        List<PartManufacturer> expectedBoughtPartManufacturerList = new ArrayList<>();
        List<PartManufacturer> actualBoughtPartManufacturerList =
                companyStockBusiness.getBoughtPartManufacturers(partManufacturerList, company, required_quantity);

        Assert.assertEquals(expectedBoughtPartManufacturerList.size(), actualBoughtPartManufacturerList.size());
        Assert.assertEquals(expectedBoughtPartManufacturerList, actualBoughtPartManufacturerList);
    }

    @Test
    public void testGetCompanyStock_When_CompanyStock_Exists_Then_Return_CompanyStock_With_Updated_Quantity() {
        int boughtPartQunatiy = 5;

        CompanyStock inputCompanyStock =
                new CompanyStock(1L, part, manufacturer, company, boughtPartQunatiy);

        CompanyStock fetchedCompanyStock =
                new CompanyStock(1L, part, manufacturer, company, 17);

        Mockito.when(companyStockDAO.getByPartNameAndManufacturerName(part.getName(), manufacturer.getName()))
                .thenReturn(Optional.of(fetchedCompanyStock));

        CompanyStock expectedCompanyStock =
                new CompanyStock(1L, part, manufacturerList.get(0), company, 22);

        CompanyStock actualCompanyStock = companyStockBusiness.getCompanyStock(inputCompanyStock);

        Assert.assertEquals(expectedCompanyStock.getId(), actualCompanyStock.getId());
        Assert.assertEquals(expectedCompanyStock.getPart(), actualCompanyStock.getPart());
        Assert.assertEquals(expectedCompanyStock.getManufacturer(), actualCompanyStock.getManufacturer());
        Assert.assertEquals(expectedCompanyStock.getCompany(), actualCompanyStock.getCompany());
        Assert.assertEquals(expectedCompanyStock.getQuantity(), actualCompanyStock.getQuantity());
    }

    @Test
    public void testGetCompanyStock_When_CompanyStock_Does_Not_Exists_Then_Return_Input_CompanyStock() {
        int boughtPartQunatiy = 5;

        CompanyStock inputCompanyStock =
                new CompanyStock(1L, part, manufacturer, company, boughtPartQunatiy);

    Mockito.when(companyStockDAO.getByPartNameAndManufacturerName(part.getName(),manufacturer.getName()))
        .thenReturn(Optional.empty());

        CompanyStock expectedCompanyStock =
                new CompanyStock(1L, part, manufacturerList.get(0), company, boughtPartQunatiy);

        CompanyStock actualCompanyStock = companyStockBusiness.getCompanyStock(inputCompanyStock);

        Assert.assertEquals(expectedCompanyStock.getId(), actualCompanyStock.getId());
        Assert.assertEquals(expectedCompanyStock.getPart(), actualCompanyStock.getPart());
        Assert.assertEquals(expectedCompanyStock.getManufacturer(), actualCompanyStock.getManufacturer());
        Assert.assertEquals(expectedCompanyStock.getCompany(), actualCompanyStock.getCompany());
        Assert.assertEquals(expectedCompanyStock.getQuantity(), actualCompanyStock.getQuantity());
    }
}

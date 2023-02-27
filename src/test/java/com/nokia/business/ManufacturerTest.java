package com.nokia.business;

import com.nokia.dao.ManufacturerDAOImpl;
import com.nokia.entity.Manufacturer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class ManufacturerTest {

    ManufacturerDAOImpl manufacturerDAO;

    ManufacturerBusinessImpl manufacturerBusiness;

    @Before
    public void setup() {
        manufacturerDAO = Mockito.mock(ManufacturerDAOImpl.class);
        manufacturerBusiness = new ManufacturerBusinessImpl(manufacturerDAO);
    }

    @Test
    public void testGetManufacturer_When_Manufacturer_Exists_Return_Existing_Manufacturer() {
        String manufacturerName = "Cofos Limited";

        Manufacturer manufacturer = new Manufacturer(1L, "Cofos Limited", null);

        Mockito.when(manufacturerDAO.getByName(manufacturerName))
                .thenReturn(Optional.of(manufacturer));

        Manufacturer actualManufacturer = manufacturerBusiness.getManufacturer(manufacturerName);

        Assert.assertNotNull(actualManufacturer);
        Assert.assertEquals(manufacturer, actualManufacturer);
    }

    @Test
    public void testGetManufacturer_When_Manufacturer_Does_Not_Exists_Return_New_Manufacturer() {
        String manufacturerName = "Cofos Limited";

        Manufacturer manufacturer = new Manufacturer(null, "Cofos Limited", null);

        Mockito.when(manufacturerDAO.getByName(manufacturerName))
                .thenReturn(Optional.empty());

        Manufacturer actualManufacturer = manufacturerBusiness.getManufacturer(manufacturerName);

        Assert.assertNotNull(actualManufacturer);
        Assert.assertEquals(manufacturer, actualManufacturer);
    }
}

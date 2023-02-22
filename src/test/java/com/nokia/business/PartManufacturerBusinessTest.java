package com.nokia.business;

import com.nokia.dao.ManufacturerDAOImpl;
import com.nokia.dao.PartDAOImpl;
import com.nokia.dao.PartManufacturerDAOImpl;
import com.nokia.entity.Company;
import com.nokia.entity.Manufacturer;
import com.nokia.entity.Part;
import com.nokia.entity.PartManufacturer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PartManufacturerBusinessTest {
  PartDAOImpl partDAO;
  ManufacturerDAOImpl manufacturerDAO;
  PartManufacturerDAOImpl partManufacturerDAO;

  PartManufacturerBusinessImpl partManufacturerBusiness;

  Part part;

  Manufacturer manufacturer;
  Company company;
  List<Manufacturer> manufacturerList;
  List<PartManufacturer> partManufacturerList;
  @Before
  public void setup() {
    partDAO=Mockito.mock(PartDAOImpl.class);
    manufacturerDAO = Mockito.mock(ManufacturerDAOImpl.class);
    partManufacturerDAO=Mockito.mock(PartManufacturerDAOImpl.class);
    partManufacturerBusiness=new PartManufacturerBusinessImpl(partDAO,manufacturerDAO,partManufacturerDAO);

//    new Part(1L,"ABC",null);
//    new Manufacturer(1L, "Cofos Limited",null);

    part=new Part(1L, "Part 1" ,null);

    company=new Company(1L,140.0,"Nokia",null);
    manufacturer= new Manufacturer(1L, "Manufacturer 1",null);

    manufacturerList=Arrays.asList(
        new Manufacturer(1L,"Manufacturer 1",null),
        new Manufacturer(2L,"Manufacturer 2",null),
        new Manufacturer(3L, "Manufacturer 3",null),
        new Manufacturer(4L,"Manufacturer 4",null)
    );

    partManufacturerList=Arrays.asList(
        new PartManufacturer(1L,part,manufacturerList.get(0),10,15f),
        new PartManufacturer(2L,part,manufacturerList.get(1),6,7.5f),
        new PartManufacturer(3L,part,manufacturerList.get(2),5,6.7f),
        new PartManufacturer(4L,part,manufacturerList.get(3),12,8.5f)
    );
  }

  @Test
  public void testGetPartManufacturer_When_PartManufacturer_Exits_Return_PartManufacturer() {
    String partName = "Part 1";
    String manufacturerName = "Manufacturer 1";
    int quantity = 10;
    float price = 5.5f;

    Optional<Part> part = Optional.of(new Part(1L, partName,null));
    Optional<Manufacturer> manufacturer = Optional.of(new Manufacturer(1L, manufacturerName,null));

    PartManufacturer inputPartManufacturer=new PartManufacturer(null,part.get(),manufacturer.get(),quantity,price);

    PartManufacturer fetchedPartManufacturer = new PartManufacturer(1L, part.get(), manufacturer.get(), 15, 8.4f);
    Mockito.when(partManufacturerDAO.getByPartAndManufacturer(partName, manufacturerName)).thenReturn(
        Optional.of(fetchedPartManufacturer));

    PartManufacturer expectedPartManufacturer=new PartManufacturer(1L,part.get(),manufacturer.get(),25,price);
    PartManufacturer actualPartManufacturer = partManufacturerBusiness.getPartManufacturer(inputPartManufacturer);

    Assert.assertEquals(expectedPartManufacturer.getPart(), actualPartManufacturer.getPart());
    Assert.assertEquals(expectedPartManufacturer.getManufacturer(), actualPartManufacturer.getManufacturer());
    Assert.assertEquals(expectedPartManufacturer.getQuantity(), actualPartManufacturer.getQuantity());
    Assert.assertEquals(expectedPartManufacturer.getPrice(), actualPartManufacturer.getPrice(), 0.0);
  }

  @Test
  public void testGetPartManufacturer_When_PartManufacturer_Does_Not_Exits_Return_PartManufacturer() {
    String partName = "Part 1";
    String manufacturerName = "Manufacturer 1";
    int quantity = 10;
    float price = 5.5f;

    Optional<Part> part = Optional.of(new Part(1L, partName,null));
    Optional<Manufacturer> manufacturer = Optional.of(new Manufacturer(1L, manufacturerName,null));

    PartManufacturer inputPartManufacturer=new PartManufacturer(null,part.get(),manufacturer.get(),quantity,price);

    Mockito.when(partManufacturerDAO.getByPartAndManufacturer(partName, manufacturerName)).thenReturn(Optional.empty());

    PartManufacturer actualPartManufacturer = partManufacturerBusiness.getPartManufacturer(inputPartManufacturer);

    Assert.assertEquals(inputPartManufacturer.getPart(), actualPartManufacturer.getPart());
    Assert.assertEquals(inputPartManufacturer.getManufacturer(), actualPartManufacturer.getManufacturer());
    Assert.assertEquals(inputPartManufacturer.getQuantity(), actualPartManufacturer.getQuantity());
    Assert.assertEquals(inputPartManufacturer.getPrice(), actualPartManufacturer.getPrice(), 0.0);
  }

  @Test
  public void testProcessPartManufacturerQuantity_When_FetchedPartManufacturer_Exists_Return_PartManufacturer(){
    String partName = "Part 1";
    String manufacturerName = "Manufacturer 1";
    int quantity = 10;
    float price = 5.5f;

    Optional<Part> part = Optional.of(new Part(1L, partName,null));
    Optional<Manufacturer> manufacturer = Optional.of(new Manufacturer(1L, manufacturerName,null));

    PartManufacturer inputPartManufacturer=new PartManufacturer(null,part.get(),manufacturer.get(),20,10.7f);
    PartManufacturer fetchedPartManufacturer=new PartManufacturer(1L,part.get(),manufacturer.get(),quantity,price);

    PartManufacturer expectedPartManufacturer=new PartManufacturer(1L,part.get(),manufacturer.get(),30,10.7f);
    PartManufacturer actualPartManufacturer=partManufacturerBusiness.processPartManufacturerQuantity(Optional.of(fetchedPartManufacturer),inputPartManufacturer);

    Assert.assertEquals(expectedPartManufacturer.getPart(), actualPartManufacturer.getPart());
    Assert.assertEquals(expectedPartManufacturer.getManufacturer(), actualPartManufacturer.getManufacturer());
    Assert.assertEquals(expectedPartManufacturer.getQuantity(), actualPartManufacturer.getQuantity());
    Assert.assertEquals(expectedPartManufacturer.getPrice(), actualPartManufacturer.getPrice(), 0.0);
  }

  @Test
  public void testProcessPartManufacturerQuantity_When_FetchedPartManufacturer_Does_Not_Exists_Return_PartManufacturer(){
    String partName = "Part 1";
    String manufacturerName = "Manufacturer 1";
    int quantity = 10;
    float price = 5.5f;

    Optional<Part> part = Optional.of(new Part(1L, partName,null));
    Optional<Manufacturer> manufacturer = Optional.of(new Manufacturer(1L, manufacturerName,null));

    PartManufacturer inputPartManufacturer=new PartManufacturer(null,part.get(),manufacturer.get(),quantity,price);
    Optional<PartManufacturer> fetchedPartManufacturer=Optional.empty();

    PartManufacturer actualPartManufacturer=partManufacturerBusiness.processPartManufacturerQuantity(fetchedPartManufacturer,inputPartManufacturer);

    Assert.assertEquals(inputPartManufacturer.getPart(), actualPartManufacturer.getPart());
    Assert.assertEquals(inputPartManufacturer.getManufacturer(), actualPartManufacturer.getManufacturer());
    Assert.assertEquals(inputPartManufacturer.getQuantity(), actualPartManufacturer.getQuantity());
    Assert.assertEquals(inputPartManufacturer.getPrice(), actualPartManufacturer.getPrice(), 0.0);
  }

  @Test
  public void testGetPartManufacturerList_When_Part_And_Manufacturer_Exists_Return_PartManufacturerList(){
    String partName = "Part 1";
    String manufacturerName = "Manufacturer 1";
    int quantity = 10;
    float price = 5.5f;

    Optional<Part> part = Optional.of(new Part(1L, partName,null));
    Optional<Manufacturer> manufacturer = Optional.of(new Manufacturer(1L, manufacturerName,null));

    List<PartManufacturer> partManufacturerList= Arrays.asList(
        new PartManufacturer(1L,part.get(),manufacturer.get(),quantity,price)
    );

    Mockito.when(partDAO.getByName(partName)).thenReturn(part);
    Mockito.when(manufacturerDAO.getByName(manufacturerName)).thenReturn(manufacturer);
    Mockito.when(partManufacturerDAO.listQuantity(partName,manufacturerName)).thenReturn(partManufacturerList);

    List<PartManufacturer> actualPartManufacturerList=partManufacturerBusiness.getPartManufacturerList(partName,manufacturerName);

    Assert.assertEquals(partManufacturerList.size(),actualPartManufacturerList.size());
    Assert.assertEquals(partManufacturerList,actualPartManufacturerList);
  }

  @Test
  public void testGetPartManufacturerList_When_Part_Exists_But_Manufacturer_Name_Does_Not_Exists_Return_PartManufacturerList(){
    String partName = "part 1";
    String manufacturerName = "";

    Optional<Part> part = Optional.of(new Part(1L, partName,null));

    List<Manufacturer> manufacturerList=Arrays.asList(
        new Manufacturer(1L,"Manufacturer 1",null),
        new Manufacturer(2L,"Manufacturer 2",null),
        new Manufacturer(3L,"Manufacturer 3",null)
    );

    List<PartManufacturer> partManufacturerList= Arrays.asList(
        new PartManufacturer(1L,part.get(),manufacturerList.get(0),5,8.4f),
        new PartManufacturer(2L,part.get(),manufacturerList.get(1),20,7.8f),
        new PartManufacturer(3L,part.get(),manufacturerList.get(2),10,9.1f)
    );

    Mockito.when(partDAO.getByName(partName)).thenReturn(part);
    Mockito.when(manufacturerDAO.getByName(manufacturerName)).thenReturn(Optional.empty());
    Mockito.when(partManufacturerDAO.listQuantity(partName,manufacturerName)).thenReturn(partManufacturerList);

    List<PartManufacturer> actualPartManufacturerList=partManufacturerBusiness.getPartManufacturerList(partName,manufacturerName);

    Assert.assertEquals(partManufacturerList.size(),actualPartManufacturerList.size());
    Assert.assertEquals(partManufacturerList,actualPartManufacturerList);
  }

  @Test
  public void testGetPartManufacturerList_When_Part_Does_Not_Exists_Return_Empty_PartManufacturerList(){
    String partName = "";
    String manufacturerName = "Manufacturer 1";

    Optional<Manufacturer> manufacturer = Optional.of(new Manufacturer(1L, manufacturerName,null));

    Mockito.when(partDAO.getByName(partName)).thenReturn(Optional.empty());
    Mockito.when(manufacturerDAO.getByName(manufacturerName)).thenReturn(manufacturer);

    List<PartManufacturer> expectedPartManufacturerList=new ArrayList<>();
    List<PartManufacturer> actualPartManufacturerList=partManufacturerBusiness.getPartManufacturerList(partName,manufacturerName);

    Assert.assertEquals(expectedPartManufacturerList.size(),actualPartManufacturerList.size());
    Mockito.verify(partManufacturerDAO,Mockito.times(0)).listQuantity(manufacturerName,partName);
  }

  @Test
  public void testGetPartManufacturerWithUpdatedQuantiity_When_PartManufacerList_Contains_BoughtPartManufacturer_Then_Return_PartManufacturerWithUpdatedQuantiity(){
    PartManufacturer boughtPartManufacturer=
        new PartManufacturer(1L,part,manufacturer,7,15f);

    PartManufacturer expectedPartManufacturerWithQuantity=
        new PartManufacturer(1L,part,manufacturer,3,15f);

    PartManufacturer actualPartManufacturerWithQuantity=
        partManufacturerBusiness.getPartManufacturerWithUpdatedQuantiity(partManufacturerList,boughtPartManufacturer);

    Assert.assertNotNull(actualPartManufacturerWithQuantity);
    Assert.assertEquals(expectedPartManufacturerWithQuantity.getId(),actualPartManufacturerWithQuantity.getId());
    Assert.assertEquals(expectedPartManufacturerWithQuantity.getPart(),actualPartManufacturerWithQuantity.getPart());
    Assert.assertEquals(expectedPartManufacturerWithQuantity.getManufacturer(),actualPartManufacturerWithQuantity.getManufacturer());
    Assert.assertEquals(expectedPartManufacturerWithQuantity.getQuantity(),actualPartManufacturerWithQuantity.getQuantity());
    Assert.assertEquals(expectedPartManufacturerWithQuantity.getPrice(),actualPartManufacturerWithQuantity.getPrice(),0.0);
  }

  @Test
  public void testGetPartManufacturerWithUpdatedQuantiity_When_PartManufacerList_Does_Not_Contains_BoughtPartManufacturer_Then_Return_Null(){
    PartManufacturer boughtPartManufacturer=
        new PartManufacturer(10L,part,manufacturer,7,15f);

    PartManufacturer actualPartManufacturerWithQuantity=
        partManufacturerBusiness.getPartManufacturerWithUpdatedQuantiity(partManufacturerList,boughtPartManufacturer);

    Assert.assertNull(actualPartManufacturerWithQuantity);
  }

  @Test
  public void testGetPart_When_Part_Exists_Return_Part(){
    String partName="Part 1";

    Mockito.when(partDAO.getByName(partName))
        .thenReturn(Optional.of(part));

    Optional<Part> excpectedPart=Optional.of(part);
    Optional<Part> actualPart=partManufacturerBusiness.getPart(partName);

    Assert.assertEquals(excpectedPart.get().getId(),actualPart.get().getId());
    Assert.assertEquals(excpectedPart.get().getName(),actualPart.get().getName());
  }

  @Test
  public void testGetPart_When_RunTimeException_Thrown_Return_Empty_Part(){
    String partName="Part 10";

    Mockito.when(partDAO.getByName(partName))
        .thenThrow(RuntimeException.class);

    Optional<Part> actualPart=partManufacturerBusiness.getPart(partName);

    Assert.assertEquals(Optional.empty(),actualPart);
  }

  @Test
  public void testGetManufacturer_When_Manufacturer_Exists_Return_Manufacturer(){
    String manufacturerName="Manufacturer 1";

    Mockito.when(manufacturerDAO.getByName(manufacturerName))
        .thenReturn(Optional.of(manufacturer));

    Optional<Manufacturer> excpectedManufacturer=Optional.of(manufacturer);
    Optional<Manufacturer> actualManufacturer=partManufacturerBusiness.getManufacturer(manufacturerName);

    Assert.assertEquals(excpectedManufacturer.get().getId(),actualManufacturer.get().getId());
    Assert.assertEquals(excpectedManufacturer.get().getName(),actualManufacturer.get().getName());
  }

  @Test
  public void testGetManufacturer_When_RunTimeException_Thrown_Return_Empty_Manufacturer(){
    String manufacturerName="Part 10";

    Mockito.when(manufacturerDAO.getByName(manufacturerName))
        .thenThrow(RuntimeException.class);

    Optional<Manufacturer> actualManufacturer=partManufacturerBusiness.getManufacturer(manufacturerName);

    Assert.assertEquals(Optional.empty(),actualManufacturer);
  }

  @Test
  public void testGetPartManufacturer_When_PartManufacturer_Exists_Return_PartManufacturer(){
    String partName="Part 1";
    String manufacturerName="Manufacturer 1";

    Mockito.when(partManufacturerDAO.getByPartAndManufacturer(partName,manufacturerName))
        .thenReturn(Optional.of(partManufacturerList.get(0)));

    Optional<PartManufacturer> expectedPartManufacturer=Optional.of(partManufacturerList.get(0));
    Optional<PartManufacturer> actualPartManufacturer=partManufacturerBusiness.getPartManufacturer(partName,manufacturerName);

    Assert.assertEquals(expectedPartManufacturer.get().getId(),actualPartManufacturer.get().getId());
    Assert.assertEquals(expectedPartManufacturer.get().getPart(),actualPartManufacturer.get().getPart());
    Assert.assertEquals(expectedPartManufacturer.get().getManufacturer(),actualPartManufacturer.get().getManufacturer());
    Assert.assertEquals(expectedPartManufacturer.get().getQuantity(),actualPartManufacturer.get().getQuantity());
    Assert.assertEquals(expectedPartManufacturer.get().getPrice(),actualPartManufacturer.get().getPrice(),0.0);
  }

  @Test
  public void testGetPartManufacturer_When_RunTimeException_Thrown_Return_Empty_PartManufacturer(){
    String partName="Part 1";
    String manufacturerName="Manufacturer 1";

    Mockito.when(partManufacturerDAO.getByPartAndManufacturer(partName,manufacturerName))
        .thenThrow(RuntimeException.class);

    Optional<PartManufacturer> actualPartManufacturer=partManufacturerBusiness.getPartManufacturer(partName,manufacturerName);

    Assert.assertEquals(Optional.empty(),actualPartManufacturer);
  }
}

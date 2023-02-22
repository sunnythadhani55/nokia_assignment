package com.nokia.business;

import com.nokia.dao.PartDAOImpl;
import com.nokia.entity.Part;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class PartBusinessTest {

    PartDAOImpl partDAO;
    PartBusinessImpl partBusiness;

    @Before
    public void setup() {
        partDAO = Mockito.mock(PartDAOImpl.class);
        partBusiness=new PartBusinessImpl(partDAO);
    }

    @Test
    public void testGetPart_When_Part_Exists_Return_Existing_Part(){
        String partName="ABC";

        Part part=new Part(1L,"ABC",null);

        Mockito.when(partDAO.getByName(partName))
                .thenReturn(Optional.of(part));

        Part actualPart=partBusiness.getPart(partName);

        Assert.assertNotNull(actualPart);
        Assert.assertEquals(part,actualPart);
    }
    @Test
    public void testGetPart_When_Part_Does_Not_Exists_Return_New_Part(){
        String partName="ABC";

        Part part=new Part(null,"ABC",null);

        Mockito.when(partDAO.getByName(partName))
                .thenReturn(Optional.empty());

        Part actualPart=partBusiness.getPart(partName);

        Assert.assertNotNull(actualPart);
        Assert.assertEquals(part,actualPart);
    }
}

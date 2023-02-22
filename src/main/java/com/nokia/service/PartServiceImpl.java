package com.nokia.service;

import com.nokia.business.PartBusiness;
import com.nokia.business.PartBusinessImpl;
import com.nokia.dao.PartDAO;
import com.nokia.dao.PartDAOImpl;
import com.nokia.entity.Part;
import com.nokia.utils.Formatter;

public class PartServiceImpl implements PartService{
    private final PartDAO partDAO;
    private final PartBusiness partBusiness;

    public PartServiceImpl(PartDAOImpl partDAO,PartBusinessImpl partBusiness) {
        this.partDAO=partDAO;
        this.partBusiness=partBusiness;
    }

    @Override
    public void addPart(String partName) {
        Part part=partBusiness.getPart(partName);

        if (part.getId()==null) {
            partDAO.savePart(part);
            Formatter.printSuccessfullExecution("\nPart added Successfully....");
        }else{
            String exceptionMessage=String.format("Duplicate Part '%s' can't be added.....",partName);
            Formatter.printException(exceptionMessage);
        }
    }
}

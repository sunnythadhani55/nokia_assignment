package com.nokia.business;

import com.nokia.dao.PartDAO;
import com.nokia.dao.PartDAOImpl;
import com.nokia.entity.Part;

import java.util.Optional;

public class PartBusinessImpl implements PartBusiness {

    private final PartDAO partDAO;

    public PartBusinessImpl(PartDAOImpl partDAO) {
        this.partDAO = partDAO;
    }

    @Override
    public Part getPart(String partName) {
        Optional<Part> part = partDAO.getByName(partName);

        Part newPart = null;
        if (part.isEmpty()) {
            newPart = new Part(null, partName, null);
        } else
            newPart = part.get();

        return newPart;
    }

}

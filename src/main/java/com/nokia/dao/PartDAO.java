package com.nokia.dao;

import com.nokia.entity.Part;

import java.util.List;
import java.util.Optional;

public interface PartDAO {
    public void savePart(Part part);
    public Optional<Part> getByName(String partName);

    public List<Part> getAll();
}

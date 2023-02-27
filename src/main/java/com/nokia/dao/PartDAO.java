package com.nokia.dao;

import com.nokia.entity.Part;

import java.util.List;
import java.util.Optional;

public interface PartDAO {
    void savePart(Part part);

    Optional<Part> getByName(String partName);

    List<Part> getAll();
}

package com.nokia.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartManufacturer> partManufacturerList;

    public Part(Long id, String name, List<PartManufacturer> partManufacturerList) {
        this.id = id;
        this.name = name;
        this.partManufacturerList = partManufacturerList;
    }

    public Part() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PartManufacturer> getPartManufacturerList() {
        return partManufacturerList;
    }

    public void setPartManufacturerList(List<PartManufacturer> partManufacturerList) {
        this.partManufacturerList = partManufacturerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;
        Part part = (Part) o;
        return Objects.equals(getId(), part.getId()) && Objects.equals(getName(), part.getName()) && Objects.equals(getPartManufacturerList(), part.getPartManufacturerList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPartManufacturerList());
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

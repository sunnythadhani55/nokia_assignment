package com.nokia.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PartManufacturer> partManufacturerList;

    public Manufacturer() {
    }

    public Manufacturer(Long id, String name, List<PartManufacturer> partManufacturerList) {
        this.id = id;
        this.name = name;
        this.partManufacturerList = partManufacturerList;
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
        if (!(o instanceof Manufacturer)) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPartManufacturerList(), that.getPartManufacturerList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPartManufacturerList());
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.nokia.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company_stock")
public class CompanyStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public CompanyStock() {
    }

    public CompanyStock(Long id, Part part, Manufacturer manufacturer, Company company, int quantity) {
        this.id = id;
        this.part = part;
        this.manufacturer = manufacturer;
        this.company = company;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompanyStock)) {
            return false;
        }
        CompanyStock that = (CompanyStock) o;
        return getId() == that.getId() && getQuantity() == that.getQuantity() && Objects.equals(
                getPart(), that.getPart()) && Objects.equals(getManufacturer(),
                that.getManufacturer()) && Objects.equals(getCompany(), that.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPart(), getManufacturer(), getCompany(), getQuantity());
    }

    @Override
    public String toString() {
        return "CompanyStock{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}

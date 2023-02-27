package com.nokia.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "part_manufacturers")
public class PartManufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private int quantity;

    private float price;

    public PartManufacturer() {
    }

    public PartManufacturer(Long id, Part part, Manufacturer manufacturer, int quantity, float price) {
        this.id = id;
        this.part = part;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartManufacturer)) return false;
        PartManufacturer that = (PartManufacturer) o;
        return getQuantity() == that.getQuantity() && Float.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getPart(), that.getPart()) && Objects.equals(getManufacturer(), that.getManufacturer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPart(), getManufacturer(), getQuantity(), getPrice());
    }

    @Override
    public String toString() {
        return "PartManufacturer{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

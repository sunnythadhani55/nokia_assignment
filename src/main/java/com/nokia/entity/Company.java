package com.nokia.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;


@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private Double balance;

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CompanyStock> companyStockList;

    public Company() {
    }

    public Company(Long id, Double balance, String name, List<CompanyStock> companyStockList) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.companyStockList = companyStockList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompanyStock> getCompanyStockList() {
        return companyStockList;
    }

    public void setCompanyStockList(List<CompanyStock> companyStockList) {
        this.companyStockList = companyStockList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(getId(), company.getId()) && Objects.equals(getBalance(), company.getBalance()) && Objects.equals(getName(), company.getName()) && Objects.equals(getCompanyStockList(), company.getCompanyStockList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBalance(), getName(), getCompanyStockList());
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }
}

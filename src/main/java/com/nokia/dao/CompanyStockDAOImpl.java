package com.nokia.dao;

import com.nokia.entity.Company;
import com.nokia.entity.CompanyStock;
import com.nokia.utils.DBSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CompanyStockDAOImpl implements CompanyStockDAO {
    private final SessionFactory sessionFactory = DBSessionFactory.getDBSessionFactory(CompanyStock.class);

    @Override
    public Optional<CompanyStock> getByPartNameAndManufacturerName(String partName, String manufacturerName) {
        Session session = sessionFactory.openSession();
        String hql = "FROM CompanyStock cs where part.name=:partName AND manufacturer.name=:manufacturerName";
        Query<CompanyStock> query = session.createQuery(hql, CompanyStock.class);
        query.setParameter("partName", partName);
        query.setParameter("manufacturerName", manufacturerName);

        List<CompanyStock> companyStockList = query.list();
        session.close();

        if (companyStockList.size() > 0)
            return Optional.of(companyStockList.get(0));
        else
            return Optional.empty();
    }

    @Override
    public void saveCompanyStock(CompanyStock companyStock) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(companyStock);
        transaction.commit();
        session.close();
    }

    @Override
    public List<CompanyStock> listPart(Company company) {
        Session session = sessionFactory.openSession();
        String hql = "SELECT cs " +
                "FROM CompanyStock cs " +
                "WHERE cs.company.name = :companyName ";
        Query query = session.createQuery(hql, CompanyStock.class);
        query.setParameter("companyName", company.getName());
        List<CompanyStock> companyStockList = query.list();
        return companyStockList;
    }
}

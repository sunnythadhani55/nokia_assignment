package com.nokia.dao;

import com.nokia.entity.Manufacturer;
import com.nokia.utils.DBSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ManufacturerDAOImpl implements ManufacturerDAO {
    private final SessionFactory sessionFactory = DBSessionFactory.getDBSessionFactory(Manufacturer.class);

    @Override
    public void saveManufacturer(Manufacturer manufacturer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(manufacturer);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteManufacturer(Manufacturer manufacturer) {
        Session session = sessionFactory.openSession();
        session.remove(manufacturer);

        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }

    public Optional<Manufacturer> getByName(String manufaturerName) {
        Session session = sessionFactory.openSession();

        Query<Manufacturer> query = session.createQuery("SELECT m FROM Manufacturer m WHERE m.name = :manufacturerName", Manufacturer.class);
        query.setParameter("manufacturerName", manufaturerName);
        List<Manufacturer> manufacturers = query.list();

        session.close();
        if (manufacturers.size() > 0)
            return Optional.of(manufacturers.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<Manufacturer> getAll() {
        Session session = sessionFactory.openSession();
        List<Manufacturer> manufacturerList = session.createQuery("FROM Manufacturer", Manufacturer.class).list();
        session.close();
        return manufacturerList;
    }

}

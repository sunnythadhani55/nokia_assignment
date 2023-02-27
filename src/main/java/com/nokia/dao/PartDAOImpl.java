package com.nokia.dao;

import com.nokia.entity.Part;
import com.nokia.utils.DBSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PartDAOImpl implements PartDAO {
    private final SessionFactory sessionFactory = DBSessionFactory.buildSessionFactory(Part.class);

    @Override
    public void savePart(Part part) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(part);
        transaction.commit();

        session.close();
    }

    public Optional<Part> getByName(String partName) {
        Session session = sessionFactory.openSession();

        Query<Part> query = session.createQuery("SELECT p FROM Part p WHERE p.name = :partName", Part.class);
        query.setParameter("partName", partName);
        List<Part> parts = query.list();

        session.close();

        if (parts.size() > 0)
            return Optional.of(parts.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<Part> getAll() {
        Session session = sessionFactory.openSession();
        List<Part> partList = session.createQuery("FROM Part", Part.class).list();

        session.close();
        return partList;
    }

}

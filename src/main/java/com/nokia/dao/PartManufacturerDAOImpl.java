package com.nokia.dao;

import com.nokia.entity.PartManufacturer;
import com.nokia.utils.DBSessionFactory;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PartManufacturerDAOImpl implements PartManufacturerDAO {

    private SessionFactory sessionFactory= DBSessionFactory.getDBSessionFactory(PartManufacturer.class);
    @Override
    public Optional<PartManufacturer> getByPartAndManufacturer(String partName, String manufacturerName) {
        Session session=sessionFactory.openSession();
        String hql="FROM PartManufacturer pm where part.name=:partName AND manufacturer.name=:manufacturerName";
        Query<PartManufacturer> query=session.createQuery(hql, PartManufacturer.class);
        query.setParameter("partName",partName);
        query.setParameter("manufacturerName",manufacturerName);

        List<PartManufacturer> partManufacturerList = query.list();
        session.close();

        if(partManufacturerList.size()>0)
            return Optional.of(partManufacturerList.get(0));
        else
            return Optional.empty();
    }
    @Override
    public void savePartManufacturer(PartManufacturer partManufacturer) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

        session.saveOrUpdate(partManufacturer);
        transaction.commit();
        session.close();
    }
    @Override
    public List<PartManufacturer> listQuantity(String partName, String manufacturerName) {
        Session session=sessionFactory.openSession();
        String hql = "SELECT pm " +
                "FROM PartManufacturer pm " +
                "WHERE pm.part.name = :partName ";

        if (manufacturerName != null && !manufacturerName.isEmpty()) {
            hql += "AND pm.manufacturer.name = :manufacturerName";
        }

        Query<PartManufacturer> query = session.createQuery(hql,PartManufacturer.class);
        query.setParameter("partName", partName);

        if (manufacturerName != null && !manufacturerName.isEmpty()) {
            query.setParameter("manufacturerName", manufacturerName);
        }

        List<PartManufacturer> partManufacturerList = query.list();
        session.close();
        return partManufacturerList;
    }
}

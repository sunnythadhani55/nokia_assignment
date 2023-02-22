package com.nokia.dao;

import com.nokia.entity.Company;
import com.nokia.utils.DBSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CompanyDAOImpl implements CompanyDAO{
  private SessionFactory sessionFactory= DBSessionFactory.getDBSessionFactory(Company.class);

  @Override
  public Company getCompany() {
    Session session=sessionFactory.openSession();
    List<Company> companyList= session.createQuery("FROM Company",Company.class).list();

    session.close();

    if(companyList.size()>0)
      return companyList.get(0);
    else{
      Company company=addCompany();
      return company;
    }
  }

  private Company addCompany() {
    Company company=new Company(null,5000.0,"Nokia",null);

    Session session=sessionFactory.openSession();
    Transaction transaction=session.beginTransaction();
    session.save(company);
    transaction.commit();
    session.close();
    return company;
  }

  @Override
  public void updateBalance(Company company) {
    Session session=sessionFactory.openSession();
    Transaction transaction=session.beginTransaction();
    session.saveOrUpdate(company);
    transaction.commit();
    session.close();
  }

}

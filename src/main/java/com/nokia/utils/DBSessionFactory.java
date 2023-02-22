package com.nokia.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DBSessionFactory {

  private static SessionFactory sessionFactory = null;
  public static SessionFactory getDBSessionFactory(Class clas){
    try{
      return new Configuration().configure().addAnnotatedClass(clas).buildSessionFactory();
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  public static SessionFactory buildSessionFactory(Class clas) {
    Logger log = Logger.getLogger("org.hibernate");
    log.setLevel(Level.OFF);
    try {
      if (sessionFactory == null) {

        return new Configuration().configure().addAnnotatedClass(clas)
            .buildSessionFactory();
      }
      return sessionFactory;
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

}

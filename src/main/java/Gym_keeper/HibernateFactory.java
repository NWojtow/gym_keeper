package Gym_keeper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class HibernateFactory {
    public SessionFactory getSessionFactory(){
//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder registryBuilder =
//                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        SessionFactory sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        return sessionFactory;
    }

    public boolean exists (Class claz, String idKey, Object idValue){
        Session session = getSessionFactory().openSession();
        Boolean temp = session.createCriteria(claz).add(Restrictions.eq(idKey, idValue)).setProjection(Projections.property(idKey)).uniqueResult()!=null;
        session.close();
        return temp;
    }
}


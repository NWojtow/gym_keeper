package Gym_keeper.DAO;

import Gym_keeper.Entity.User;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {

public void add(User user) {
    HibernateFactory hibernateFactory = new HibernateFactory();
    Session session = hibernateFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try {
        session.save(user);
        session.getTransaction().commit();
    } catch (Exception e) {
        transaction.rollback();
        e.printStackTrace();
        throw new RuntimeException();
    } finally {
        session.close();
    }
}

public void delete(int id){
    HibernateFactory hibernateFactory = new HibernateFactory();
    Session session = hibernateFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try{
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    } catch (Exception e){
        transaction.rollback();
        e.printStackTrace();
        throw new RuntimeException();
    } finally{
        session.close();
    }
}

public User read (int id){
    HibernateFactory hibernateFactory = new HibernateFactory();
    Session session = hibernateFactory.getSessionFactory().openSession();
    try{
        User user = (User) session.get(User.class,id);
        return user;
    } catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException();
    }finally{
        session.close();
    }
}

}

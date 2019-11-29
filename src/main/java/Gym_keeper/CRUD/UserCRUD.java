package Gym_keeper.CRUD;

import Gym_keeper.Entity.User;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public class UserCRUD {

public void add(User user) {
    HibernateFactory hibernateFactory = new HibernateFactory();
    Session session = hibernateFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try{
        if(hibernateFactory.exists(User.class,"username",user.getUsername())){
            throw new RuntimeException();
        }
    }catch(RuntimeException e ){
        e.printStackTrace();
        throw new EntityExistsException();
    }
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
        if(!hibernateFactory.exists(User.class,"id",id)){
            throw new EntityNotFoundException();
        }
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

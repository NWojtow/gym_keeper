package Gym_keeper.CRUD;

import Gym_keeper.Entity.User;
import Gym_keeper.Entity.User_data;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public class User_dataCRUD {
    public void add(User_data data){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            if(hibernateFactory.exists(User_data.class,"id",data.getId())){
                throw new EntityExistsException();
            }}
            catch(EntityExistsException e){
                e.printStackTrace();
                throw new EntityExistsException();
            }
        try{
            session.save(data);
            session.getTransaction().commit();
        }catch(Exception e ){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            session.close();
        }
    }

    public User_data read(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        if(!hibernateFactory.exists(User_data.class,"user_data_id",id)){
            throw new EntityNotFoundException();
        }
        try{
            User_data user_data = (User_data) session.get(User_data.class, id);
            return user_data;
        }catch(Exception e ){
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            session.close();
        }
    }

    public void delete(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            User_data user_Data = (User_data) session.get(User_data.class, id);
            session.delete(user_Data);
            session.getTransaction().commit();
        }catch(Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            session.close();
        }
    }
}

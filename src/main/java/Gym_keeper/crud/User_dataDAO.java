package Gym_keeper.crud;

import Gym_keeper.entitiy.User_data;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Repository
public class User_dataDAO {

    private HibernateFactory hibernateFactory = new HibernateFactory();
    private SessionFactory sessionFactory = hibernateFactory.getSessionFactory();

    public void add(User_data data){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        checkIfUserDataExists(data.getId());
        try{
            session.save(data);
            session.getTransaction().commit();
        }catch(Exception e ){
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }finally{
            session.close();
        }
    }

    public User_data read(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
            checkIfUserNotDataExists(id);
        try{
            User_data user_data = (User_data) session.get(User_data.class, id);
            return user_data;
        }catch(Exception e ){
            e.printStackTrace();
            throw e;
        }finally{
            session.close();
        }
    }

    public void delete(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        checkIfUserNotDataExists(id);

        try{
            User_data user_Data = (User_data) session.get(User_data.class, id);
            session.delete(user_Data);
            session.getTransaction().commit();
        }catch(Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }finally{
            session.close();
        }
    }

    private void checkIfUserNotDataExists(Integer id){
        if(!hibernateFactory.exists(User_data.class,"user_data_id",id)){
            throw new EntityNotFoundException();
        }
    }

    private void checkIfUserDataExists(Integer id){
        if(hibernateFactory.exists(User_data.class,"user_data_id",id)){
            throw new EntityExistsException();
        }
    }
}

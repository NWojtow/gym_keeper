package Gym_keeper.CRUD;

import Gym_keeper.Entity.Serie;
import Gym_keeper.Entity.User;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityNotFoundException;

public class SerieCRUD {

    public void add(Serie serie){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(serie);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            session.close();
        }
    }

    public Serie read(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        try{
            if(!hibernateFactory.exists(Serie.class,"rep_id",id)){
                throw new EntityNotFoundException();
            }
            Serie temp = (Serie) session.get(Serie.class, id);
            return temp;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        finally{
            session.close();
        }
    }

    public void delete(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Serie temp = (Serie) session.get(Serie.class, id);
            session.delete(temp);
            transaction.commit();
        }catch(Exception e ){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            session.close();
        }
    }

}

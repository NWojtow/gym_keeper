package Gym_keeper.crud;

import Gym_keeper.entitiy.DaoUser;
import Gym_keeper.entitiy.Serie;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Repository
public class SerieDAO {

    public void add(Serie serie){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(hibernateFactory.exists(DaoUser.class,"rep_id",serie.getRep_id())){
            throw new EntityExistsException();
        }
        try{
            session.save(serie);
            transaction.commit();
        }
        catch (Exception e){
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
        if(!hibernateFactory.exists(Serie.class,"rep_id",id)){
            throw new EntityNotFoundException();
        }
        try{

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

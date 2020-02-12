package Gym_keeper.crud;

import Gym_keeper.entitiy.Serie;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Repository
public class SerieDAO {

    private    HibernateFactory hibernateFactory = new HibernateFactory();
    private SessionFactory sessionFactory = hibernateFactory.getSessionFactory();

    public void add(Serie serie){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        checkIfSerieExists(serie.getRep_id());
        try{
            session.save(serie);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }finally{
            session.close();
        }
    }

    public Serie read(int id){
        Session session = sessionFactory.openSession();

        checkIfSerieNotExists(id);

        try{
            Serie temp = (Serie) session.get(Serie.class, id);
            return temp;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        finally{
            session.close();
        }
    }

    public void delete(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Serie temp = (Serie) session.get(Serie.class, id);
            session.delete(temp);
            transaction.commit();
        }catch(Exception e ){
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }finally{
            session.close();
        }
    }

    private void checkIfSerieExists(Integer id){
        if(hibernateFactory.exists(Serie.class,"rep_id", id)){
            throw new EntityExistsException();
        }
    }

    private void checkIfSerieNotExists(Integer id){
        if(!hibernateFactory.exists(Serie.class,"rep_id",id)){
            throw new EntityNotFoundException();
        }
    }
}

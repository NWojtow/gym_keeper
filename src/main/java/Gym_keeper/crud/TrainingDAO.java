package Gym_keeper.crud;

import Gym_keeper.entitiy.Training;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityNotFoundException;

public class TrainingDAO {

    public void add(Training training){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(training);
            session.getTransaction().commit();
        }
        catch(Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }
        finally{
            session.close();
        }
    }

    public Training read(int id) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        if(!hibernateFactory.exists(Training.class,"training_id",id)){
            throw new EntityNotFoundException();
        }
        try{

            Training temp = (Training) session.get(Training.class, id);
            return temp;
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        finally{
            session.close();
        }
    }

    public void delete (int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Training temp = (Training) session.get(Training.class, id);
            session.delete(temp);
            session.getTransaction().commit();
        }
        catch(Exception e ){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }
        finally{
            session.close();
        }
    }

}

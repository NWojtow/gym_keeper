package Gym_keeper.CRUD;

import Gym_keeper.Entity.Exercise;
import Gym_keeper.Entity.Serie;
import Gym_keeper.Entity.User;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityNotFoundException;

public class ExerciseCRUD {
    public void add(Exercise exercise){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(exercise);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            session.close();
        }
    }

    public Exercise read(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        try{
            if(!hibernateFactory.exists(Exercise.class,"exercise_id",id)){
                throw new EntityNotFoundException();
            }
            Exercise temp = (Exercise) session.get(Exercise.class, id);
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
            Exercise temp = (Exercise) session.get(Exercise.class, id);
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

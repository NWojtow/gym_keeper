package Gym_keeper.crud;

import Gym_keeper.entitiy.Exercise;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class ExerciseDAO {

    HibernateFactory hibernateFactory = new HibernateFactory();
    SessionFactory sessionFactory = hibernateFactory.getSessionFactory();

    public void add(Exercise exercise){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(exercise);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }finally{
            session.close();
        }
    }

    public Exercise read(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();

        checkIfExerciseNotExists(id);

        try{
            Exercise temp = (Exercise) session.get(Exercise.class, id);
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
            Exercise temp = (Exercise) session.get(Exercise.class, id);
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

    private void checkIfExerciseNotExists(Integer id){
        if(!hibernateFactory.exists(Exercise.class,"exercise_id",id)){
            throw new EntityNotFoundException();
        }
    }
}

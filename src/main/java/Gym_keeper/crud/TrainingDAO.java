package Gym_keeper.crud;

import Gym_keeper.entitiy.Training;
import Gym_keeper.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class TrainingDAO {

    private HibernateFactory hibernateFactory = new HibernateFactory();
    private SessionFactory sessionFactory = hibernateFactory.getSessionFactory();

    public void add(Training training){
        Session session = sessionFactory.openSession();
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
        Session session = hibernateFactory.getSessionFactory().openSession();

        checkIfTrainingNotExists(id);

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
        Session session = sessionFactory.openSession();
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

    private void checkIfTrainingNotExists(Integer id){
        if(!hibernateFactory.exists(Training.class,"training_id",id)){
            throw new EntityNotFoundException();
        }
    }

}

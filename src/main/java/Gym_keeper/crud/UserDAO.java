package Gym_keeper.crud;

import Gym_keeper.entitiy.DaoUser;
import Gym_keeper.HibernateFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public class UserDAO implements CrudRepository<DaoUser, Integer> {

    private HibernateFactory hibernateFactory = new HibernateFactory();
    private SessionFactory sessionFactory = hibernateFactory.getSessionFactory();

    public void add(DaoUser daoUser) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    checkIfUserExistsByUsername(daoUser.getUsername());

    try {
        session.save(daoUser);
        session.getTransaction().commit();
    } catch (Exception e) {
        transaction.rollback();
        e.printStackTrace();
        throw e;
    } finally {
        session.close();
    }
}

public void delete(int id){
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    checkIfUserNotExistsById(id);

    try{
        DaoUser daoUser = (DaoUser) session.get(DaoUser.class, id);
        session.delete(daoUser);
        session.getTransaction().commit();
    } catch (Exception e){
        transaction.rollback();
        e.printStackTrace();
        throw new RuntimeException();
    } finally{
        session.close();
    }
}

public DaoUser read (int id){
    Session session = hibernateFactory.getSessionFactory().openSession();
        checkIfUserNotExistsById(id);
    try{
        DaoUser daoUser = (DaoUser) session.get(DaoUser.class, id);
        return daoUser;
    } catch(Exception e){
        e.printStackTrace();
        throw e;
    }finally{
        session.close();
    }
}

    public DaoUser read (String username){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();

        checkIfUserNotExistsByUsername(username);
        try{
            Criteria criteria = session.createCriteria(DaoUser.class);
            DaoUser daoUser = (DaoUser) criteria.add(Restrictions.eq("username",username)).uniqueResult();
            return daoUser;
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            session.close();
        }
    }

    @Override
    public <S extends DaoUser> S save(S s) {
       Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        checkIfUserExistsByUsername(s.getUsername());
        try {
            session.save(s);
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
        return s;
    }

    @Override
    public <S extends DaoUser> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<DaoUser> findById(Integer integer) {
        return null;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<DaoUser> findAll() {
        return null;
    }

    @Override
    public Iterable<DaoUser> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(DaoUser daoUser) {

    }

    @Override
    public void deleteAll(Iterable<? extends DaoUser> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    private void checkIfUserExistsByUsername(String username){
        if(hibernateFactory.exists(DaoUser.class,"username", username)){
            throw new EntityExistsException();
        }
    }
    private void checkIfUserNotExistsByUsername(String username){
        if(!hibernateFactory.exists(DaoUser.class,"username", username)){
            throw new EntityNotFoundException();
        }
    }

    private void checkIfUserNotExistsById(Integer id){
        if(!hibernateFactory.exists(DaoUser.class,"id", id)){
            throw new EntityNotFoundException();
        }
    }
}

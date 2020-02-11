package Gym_keeper.crud;

import Gym_keeper.entitiy.DaoUser;
import Gym_keeper.HibernateFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public class UserDAO implements CrudRepository<DaoUser, Integer> {

public void add(DaoUser daoUser) {
    HibernateFactory hibernateFactory = new HibernateFactory();
    Session session = hibernateFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    if(hibernateFactory.exists(DaoUser.class,"username", daoUser.getUsername())){
            throw new EntityExistsException();
        }

    try {
        session.save(daoUser);
        session.getTransaction().commit();
    } catch (Exception e) {
        transaction.rollback();
        e.printStackTrace();
        throw new RuntimeException();
    } finally {
        session.close();
    }
}

public void delete(int id){
    HibernateFactory hibernateFactory = new HibernateFactory();
    Session session = hibernateFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
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
    HibernateFactory hibernateFactory = new HibernateFactory();
    Session session = hibernateFactory.getSessionFactory().openSession();
    if(!hibernateFactory.exists(DaoUser.class,"id",id)){
        throw new EntityNotFoundException();
    }
    try{
        DaoUser daoUser = (DaoUser) session.get(DaoUser.class,id);
        return daoUser;
    } catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException();
    }finally{
        session.close();
    }
}

    public DaoUser read (String username){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        if(!hibernateFactory.exists(DaoUser.class,"username", username)){
            throw new EntityNotFoundException();
        }
        try{
            Criteria criteria = session.createCriteria(DaoUser.class);
            DaoUser daoUser = (DaoUser) criteria.add(Restrictions.eq("username",username)).uniqueResult();
            return daoUser;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            session.close();
        }
    }

    @Override
    public <S extends DaoUser> S save(S s) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if(hibernateFactory.exists(DaoUser.class,"username", s.getUsername())){
            throw new EntityExistsException();
        }

        try {
            session.save(s);
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException();
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
        return Optional.empty();
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
}

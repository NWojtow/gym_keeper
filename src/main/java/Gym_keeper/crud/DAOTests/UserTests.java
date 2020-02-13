package Gym_keeper.crud.DAOTests;

import Gym_keeper.crud.UserDAO;
import Gym_keeper.entitiy.DaoUser;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    @Test
    @Transactional
    public void userNotFoundTest(){
        UserDAO userDAO = new UserDAO();

        assertThrows(EntityNotFoundException.class, () ->{
            userDAO.read(0);
        });
    }

    @Test
    @Transactional
    public void userExistsTest(){
        //given
        UserDAO userDAO = new UserDAO();
        DaoUser testUser = new DaoUser();
        testUser.setType("USER");
        testUser.setUsername("testUser");
        testUser.setPasswd("testUser");

        userDAO.add(testUser);

        //then
        assertThrows(EntityExistsException.class, () ->{
            userDAO.add(testUser);
        });

        userDAO.delete(userDAO.read("testUser").getId());
    }

    @Test
    @Transactional
    public void userByUsernameNotFounTest(){
        UserDAO userDAO = new UserDAO();

        assertThrows(EntityNotFoundException.class, () ->{
            userDAO.read("TestUsername");
        });
    }
}

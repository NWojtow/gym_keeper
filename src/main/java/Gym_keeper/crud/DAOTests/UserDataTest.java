package Gym_keeper.crud.DAOTests;
import Gym_keeper.crud.User_dataDAO;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


public class UserDataTest {

    @Test
    @Transactional
    public void userDataNotFound(){
        User_dataDAO userDataDAO = new User_dataDAO();

        assertThrows(EntityNotFoundException.class, () ->{
            userDataDAO.read(0);
        });
    }
}

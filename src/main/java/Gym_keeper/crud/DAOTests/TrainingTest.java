package Gym_keeper.crud.DAOTests;

import Gym_keeper.crud.TrainingDAO;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


public class TrainingTest {
    @Test
    @Transactional
    public void trainignNotFound(){
        TrainingDAO trainingDAO = new TrainingDAO();
        assertThrows(EntityNotFoundException.class, () ->{
            trainingDAO.read(0);
        });
    }
}

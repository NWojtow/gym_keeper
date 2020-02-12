package Gym_keeper.crud.DAOTests;

import Gym_keeper.crud.ExerciseDAO;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

    @Test
    @Transactional
    public void serieReadNotExist(){
        //given
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        assertThrows(EntityNotFoundException.class, () ->{
            exerciseDAO.read(0);
        });
    }

}

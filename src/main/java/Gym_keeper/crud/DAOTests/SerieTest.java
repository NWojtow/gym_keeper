package Gym_keeper.crud.DAOTests;

import Gym_keeper.crud.SerieDAO;
import Gym_keeper.entitiy.Serie;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;


public class SerieTest {

    @Test
    @Transactional
    public void serieReadNotExist(){
        //given
        SerieDAO serieDAO = new SerieDAO();

       assertThrows(RuntimeException.class, () ->{
            serieDAO.read(0);
        });
    }
}

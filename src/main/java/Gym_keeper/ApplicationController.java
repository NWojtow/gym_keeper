package Gym_keeper;

import Gym_keeper.CRUD.ExerciseCRUD;
import Gym_keeper.CRUD.TrainingCRUD;
import Gym_keeper.CRUD.UserCRUD;
import Gym_keeper.Entity.Exercise;
import Gym_keeper.Entity.Training;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Gym_keeper.Entity.User;
import com.google.gson.Gson;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;


@RestController
public class ApplicationController {

    @RequestMapping(value="/user", method = RequestMethod.PUT)
    public ResponseEntity<String> addUser(@RequestBody String userData){

        Gson gson = new Gson();
        UserCRUD userDao = new UserCRUD();

        User temp = gson.fromJson(userData, User.class);
        try {
            userDao.add(temp);
        }catch(EntityExistsException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }catch(RuntimeException e){
            e.printStackTrace();
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value ="user/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUser(@PathVariable("id")int id){
        UserCRUD userDao = new UserCRUD();
        Gson gson = new Gson();
        User temp;

        try {
            temp = userDao.read(id);
        }
        catch(EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(gson.toJson(temp),HttpStatus.OK);
    }

    @RequestMapping(value ="training/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTraining(@PathVariable("id")int id){
        TrainingCRUD trainingCRUD = new TrainingCRUD();
        Gson gson = new Gson();
        Training temp;

        try {
            temp = trainingCRUD.read(id);
        }
        catch(EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(gson.toJson(temp),HttpStatus.OK);
    }

    @RequestMapping(value="training/exercise/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getExercise(@PathVariable("id")int id){
        ExerciseCRUD exerciseCRUD = new ExerciseCRUD();
        Gson gson = new Gson();
        Exercise temp;
        try{
            temp = exerciseCRUD.read(id);
        }catch(EntityNotFoundException e ){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(gson.toJson(temp), HttpStatus.OK);
    }

}

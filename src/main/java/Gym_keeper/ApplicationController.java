package Gym_keeper;

import Gym_keeper.CRUD.ExerciseDAO;
import Gym_keeper.CRUD.SerieDAO;
import Gym_keeper.CRUD.TrainingDAO;
import Gym_keeper.CRUD.UserDAO;
import Gym_keeper.Entity.Exercise;
import Gym_keeper.Entity.Serie;
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

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<String> addUser(@RequestBody String userData) {

        Gson gson = new Gson();
        UserDAO userDao = new UserDAO();
        User temp;
        try {
            temp = gson.fromJson(userData, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            userDao.add(temp);
        } catch (EntityExistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUser(@PathVariable("id") int id) {
        UserDAO userDao = new UserDAO();
        Gson gson = new Gson();
        User temp;

        try {
                    temp = userDao.read(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(gson.toJson(temp), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTraining(@PathVariable("id") int id) {
        TrainingDAO trainingDAO = new TrainingDAO();
        Gson gson = new Gson();
        Training temp;

        try {
            temp = trainingDAO.read(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(gson.toJson(temp), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getExercise(@PathVariable("id") int id) {
        ExerciseDAO exerciseCRUD = new ExerciseDAO();
        Gson gson = new Gson();
        Exercise temp;
        try {
            temp = exerciseCRUD.read(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(gson.toJson(temp), HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value = "training/exercise/rep/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getRep(@PathVariable("id") int id) {
        SerieDAO serieDAO = new SerieDAO();
        Gson gson = new Gson();
        Serie temp;
        try {
            temp = serieDAO.read(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(gson.toJson(temp), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise/rep", method = RequestMethod.POST)
    public ResponseEntity<String> postRep (@RequestBody String data) {
        SerieDAO serieDAO = new SerieDAO();
        Gson gson = new Gson();
        Serie temp;
        try{
            temp = gson.fromJson(data, Serie.class);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            serieDAO.add(temp);
        }
        catch(EntityExistsException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);


    }

}

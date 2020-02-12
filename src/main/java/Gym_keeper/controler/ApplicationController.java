package Gym_keeper.controler;

import Gym_keeper.crud.*;
import Gym_keeper.entitiy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import javax.persistence.EntityNotFoundException;


@CrossOrigin
@RestController
public class ApplicationController {

    @Autowired
    ExceptionController exceptionController;

  private  UserDAO userDAO = new UserDAO();
  private  ExerciseDAO exerciseDAO = new ExerciseDAO();
  private  SerieDAO serieDAO = new SerieDAO();
  private  TrainingDAO trainingDAO = new TrainingDAO();
  private  User_dataDAO userDataDAO = new User_dataDAO();

  private  Gson gson = new Gson();

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUser(@PathVariable("id") int id) {
        DaoUser temp;
        try {
            temp = userDAO.read(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(gson.toJson(temp), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
            userDAO.delete(id);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value="user/userdata", method = RequestMethod.POST)
    public ResponseEntity<String> putUserData(@RequestBody String userData){
        User_data entity = gson.fromJson(userData, User_data.class);
            userDataDAO.add(entity);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "user/userdata/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getUserData(@PathVariable("id") int id){
       User_data response =  userDataDAO.read(id);

        return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "user/userdata/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserData(@PathVariable("id") int id){
        userDataDAO.delete(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training", method = RequestMethod.POST)
    public ResponseEntity<String> postTraining(@RequestBody String training){
        Training temp = gson.fromJson(training, Training.class);
        trainingDAO.add(temp);

        return  new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "training/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTraining(@PathVariable("id") int id) {
        Training temp;

        temp = trainingDAO.read(id);
        return new ResponseEntity(gson.toJson(temp), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTraining(@PathVariable("id") int id){
        trainingDAO.delete(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise", method = RequestMethod.POST)
    public ResponseEntity<String> postExercise(@RequestBody String exercise){
        exerciseDAO.add(gson.fromJson(exercise, Exercise.class));

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getExercise(@PathVariable("id") int id) {
        Exercise temp;
        temp = exerciseDAO.read(id);

        return new ResponseEntity(gson.toJson(temp), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteExercise(@PathVariable int id){
        exerciseDAO.delete(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise/rep/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getRep(@PathVariable("id") int id) {
        Serie temp;
        temp = serieDAO.read(id);

        return new ResponseEntity(gson.toJson(temp), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise/rep", method = RequestMethod.POST)
    public ResponseEntity<String> postRep(@RequestBody String data) {
        Serie temp;
        temp = gson.fromJson(data, Serie.class);

        serieDAO.add(temp);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "training/exercise/rep{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRep(@PathVariable("id") int id){
        serieDAO.delete(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

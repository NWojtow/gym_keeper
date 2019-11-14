package Gym_keeper;

import Gym_keeper.DAO.UserDAO;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Gym_keeper.Entity.User;
import com.google.gson.Gson;

import javax.xml.ws.Response;


@RestController
public class ApplicationController {

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addUser(@RequestBody String userData){

        Gson gson = new Gson();
        UserDAO userDao = new UserDAO();

        User temp = gson.fromJson(userData, User.class);
        try {
            userDao.add(temp);
        }catch(RuntimeException e){
            e.printStackTrace();
        return "Error: User Exists";
        }
        return gson.toJson(temp);
    }

    @RequestMapping(value ="get/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id")int id){
        UserDAO userDao = new UserDAO();
        Gson gson = new Gson();


        User temp = userDao.read(id);

        return gson.toJson(temp);
    }

}

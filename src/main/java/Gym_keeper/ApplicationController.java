package Gym_keeper;

import org.springframework.web.bind.annotation.*;
import Gym_keeper.Entity.User;
import com.google.gson.Gson;


@RestController
public class ApplicationController {

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addUser(@RequestBody String userData){
        User temp = gson.fromJson(userData, User.class);
    }

}

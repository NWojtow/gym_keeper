package Gym_keeper.service;

import Gym_keeper.crud.UserDAO;
import Gym_keeper.dto.UserDTO;
import Gym_keeper.entitiy.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class JWTUserService implements UserDetailsService {


    @Autowired
    private PasswordEncoder bCryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DaoUser daoUser;
        UserDAO userDAO = new UserDAO();
       try{
           daoUser = userDAO.read(username);
       } catch(EntityNotFoundException e){
           e.printStackTrace();
           throw new UsernameNotFoundException("User not found");
       }
        return new User(daoUser.getUsername(), daoUser.getPasswd(), getAutorities(daoUser));
    }
    public DaoUser save(UserDTO user){
        UserDAO userDAO = new UserDAO();

        DaoUser newDaoUser = new DaoUser();
        newDaoUser.setUsername(user.getUsername());
        newDaoUser.setPasswd(bCryptEncoder.encode(user.getPassword()));
        newDaoUser.setType("USER");

       return userDAO.save(newDaoUser);
    }

    public DaoUser saveAdmin(UserDTO userData){
        UserDAO userDAO = new UserDAO();

        DaoUser newDaoUser = new DaoUser();
        newDaoUser.setUsername(userData.getUsername());
        newDaoUser.setPasswd(bCryptEncoder.encode(userData.getPassword()));
        newDaoUser.setType("ADMIN");

        return userDAO.save(newDaoUser);
    }

    private Set getAutorities(DaoUser daoUser){
        Set autorities = new HashSet();

        autorities.add(new SimpleGrantedAuthority("ROLE_" + daoUser.getType()));

        return autorities;
    }
}

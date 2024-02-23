package com.Security.demo.Service;


import com.Security.demo.Models.User;
import com.Security.demo.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> getUser(Integer id){
        Optional<User> user = userRepo.findById(id);
        return user;

    }

    public User createUser(Integer id,String username,String password){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        userRepo.save(user);
        return user;
    }

}

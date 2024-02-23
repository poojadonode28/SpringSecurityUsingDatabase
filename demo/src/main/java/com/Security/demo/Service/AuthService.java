package com.Security.demo.Service;


import com.Security.demo.Models.Session;
import com.Security.demo.Models.Status;
import com.Security.demo.Models.User;
import com.Security.demo.Repository.SessionRepo;
import com.Security.demo.Repository.UserRepo;
import com.Security.demo.dtos.UserDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Objects;

@Service
public class AuthService {

    //private BCryptPasswordEncoder bcrypt;
    private UserRepo userRepo;
    private SessionRepo sessionRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(BCryptPasswordEncoder bcrypt,
                       UserRepo userRepo,SessionRepo sessionRepo) {
        this.userRepo=userRepo;
        this.sessionRepo=sessionRepo;
        this.bCryptPasswordEncoder=bcrypt;
    }

    public ResponseEntity<String> login(String username, String password){
        User user = userRepo.findByUsername(username);
        if(Objects.isNull(user)){
            return new ResponseEntity<>("logged in failed",HttpStatus.NOT_FOUND);
        }
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("logged in failed",HttpStatus.NOT_FOUND);

        }
        String token = RandomStringUtils.randomAlphanumeric(30);
        Session session =new Session();
        session.setStatus(Status.ACTIVATED);
        session.setToken(token);
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        session.setUser(user);
        sessionRepo.save(session);
        MultiValueMapAdapter<String,String> map = new MultiValueMapAdapter<>(new HashMap<>());
        map.add(HttpHeaders.SET_COOKIE,"auth-token:"+token);
        return new ResponseEntity<>("logged in successfully",map, HttpStatus.OK);


    }

    public ResponseEntity<String> signUp(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(user);
        return new ResponseEntity<>("Signed Up successfully",HttpStatus.OK);

    }

    public ResponseEntity<String> validate(String token,String username){
        Session s = sessionRepo.findByToken(token);
        if(Objects.isNull(s)){
            return new ResponseEntity<>("Session not found",HttpStatus.NOT_FOUND);
        }
        if(!s.getUser().getUsername().equals(username)){
            return new ResponseEntity<>("Session not found",HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>("Validated",HttpStatus.OK);
    }


}

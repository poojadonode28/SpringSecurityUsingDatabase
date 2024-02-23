package com.Security.demo.Controller;


import com.Security.demo.Models.Status;
import com.Security.demo.Service.AuthService;
import com.Security.demo.dtos.LoginRequestDto;
import com.Security.demo.dtos.UserDto;
import com.Security.demo.dtos.ValidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto){
        return authService.signUp(userDto.getUsername(), userDto.getPassword());
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody ValidateDto validateDto){
        return authService.validate(validateDto.getToken(), validateDto.getUsername());
    }
}

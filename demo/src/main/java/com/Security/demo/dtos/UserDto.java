package com.Security.demo.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;

    public UserDto from(String username,String password){
        UserDto userDto =new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        return userDto;
    }
}

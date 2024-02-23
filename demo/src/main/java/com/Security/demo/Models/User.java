package com.Security.demo.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends BaseModel{
    private String username;
    private String password;

}

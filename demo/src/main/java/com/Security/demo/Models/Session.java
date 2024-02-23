package com.Security.demo.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Session extends BaseModel{
    private String token;
    private Status status;
    @ManyToOne
    private User user;
}

package com.Security.demo.Repository;

import com.Security.demo.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session,Integer> {

     Session findByToken(String token);

}

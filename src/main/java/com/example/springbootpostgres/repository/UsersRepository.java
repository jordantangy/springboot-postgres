package com.example.springbootpostgres.repository;


import com.example.springbootpostgres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,String> {

    User findByEmail(String email);
    User findByUsername(String username);

}

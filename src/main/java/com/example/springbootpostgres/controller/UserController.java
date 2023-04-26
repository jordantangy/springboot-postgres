package com.example.springbootpostgres.controller;

import com.example.springbootpostgres.httpexception.UserException;
import com.example.springbootpostgres.model.Email;
import com.example.springbootpostgres.model.Role;
import com.example.springbootpostgres.model.User;
import com.example.springbootpostgres.service.EmailService;
import com.example.springbootpostgres.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Object> uploadUser(@Valid @RequestBody User user){
//        return service.addUser(user);
//    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{uid}")
    public Optional<User> getUserById(@PathVariable String uid){
        return service.getUserById(uid);
    }

    @GetMapping("/email/{email}")
    public Optional<User> findUserByEmail(@PathVariable String email){
        return  service.findByEmail(email);
    }



    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ObjectNode JSONObject){
        String username = JSONObject.get("username").asText().toString();
        String password = JSONObject.get("password").asText().toString();
        System.out.println(username);
        User user = service.findByUsername(username);
        try {
            if (user == null) {
                throw new UserException("User not found");
            }
        }catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return service.checkCredentials(user,password,user.getPassword());
    }

    @PutMapping("/update/{uid}")
    public User updateUser(@PathVariable String uid, @RequestBody User user){
        return service.updateUser(uid,user);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Object> deleteUser(@PathVariable String uid){
        return service.deleteUser(uid);
    }

    @PutMapping("updatepassword/{uid}")
    public ResponseEntity<Object>  changePassword(@PathVariable String uid, @RequestBody ObjectNode JSONObject){
        String newPassword = JSONObject.get("password").asText().toString();
        return service.updatePassword(uid,newPassword);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllUsers(){
        return service.deleteAll();
    }

    @PatchMapping("/updaterole/{uid}")
    public User updateRole(@PathVariable String uid, @RequestBody ObjectNode JSONObject){
        String role = JSONObject.get("role").asText().toString();
        return service.updateRole(uid,Role.valueOf(role));
    }


}

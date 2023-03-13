package com.example.springbootpostgres.service;

import com.example.springbootpostgres.httpexception.UserException;
import com.example.springbootpostgres.model.User;
import com.example.springbootpostgres.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UsersRepository repository;


    public String encryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    @Transactional
    public ResponseEntity<Object> addUser(User user) {
        boolean isUnique = repository.findAll().stream()
                                               .noneMatch(existingUser -> existingUser.getUsername().equals(user.getUsername()));
        try{

            if(isUnique){
                String password = user.getPassword();
                String encodedPassword = encryptPassword(password);
                user.setPassword(encodedPassword);
                repository.save(user);
            }
            else{
                throw new UserException("Username already exists, choose another username");
            }
        }catch (UserException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(String uid) {
        Optional<User> user = repository.findById(uid);
        return user;
    }

    private User checkUserExists(String uid) {
        Optional<User> optionalUser = getUserById(uid);
        if (optionalUser.isEmpty()) {
            throw new UserException("User not found");
        }
        return optionalUser.get();
    }


    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }


    public ResponseEntity<Object> checkCredentials(User user, String password, String hashedPassword) {

        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean passwordMatches = encoder.matches(password, hashedPassword);
            if (!passwordMatches) {
                throw new UserException("username or password is not correct");
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body("User logged in :" + user);
            }
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public User updateUser(String uid,User user){

        User existingUser = checkUserExists(uid);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setPassword(encryptPassword(user.getPassword()));
        return repository.save(existingUser);
    }

    public ResponseEntity<Object> deleteUser(String uid){

        User user = checkUserExists(uid);
        repository.deleteById(uid);
        return ResponseEntity.ok(user.getUsername() + " deleted successfully");

    }


    public ResponseEntity<Object> updatePassword(String uid, String newPassword) {
        User user = checkUserExists(uid);
        user.setPassword(encryptPassword(newPassword));
        repository.save(user);
        return ResponseEntity.ok(user.getUsername() + " successfully modified password");
    }

    public ResponseEntity<Object> deleteAll(){
        try{
            repository.deleteAll();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}

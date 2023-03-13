package com.example.springbootpostgres.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotBlank(message = "The username field is required")
    private String username;
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "The password field is required")
    private String password;



}

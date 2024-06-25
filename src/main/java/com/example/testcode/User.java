package com.example.testcode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    Long id;

    String username;
    String firstName;
    String LastName;
    String Email;

    Long createAt;
    Long lastModified;

    public User(String username,String firstName,String LastName,String Email) {
        this.username = username;
        this.firstName = firstName;
        this.LastName = LastName;
        this.Email = Email;
    }

    public User() {

    }

    public Long setId(Long id) {
        return  this.id = id;
    }

    public Long setCreateAt(Long createAt) {
        return  this.createAt = createAt;
    }

    public Long setLastModified(Long lastModified) {
        return  this.lastModified = lastModified;
    }

    public String getUsername() {
        return  this.username;
    }

}

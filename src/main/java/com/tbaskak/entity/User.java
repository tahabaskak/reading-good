package com.tbaskak.entity;

import com.tbaskak.constant.Constant;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @Id
    private String id;
    @NotNull
    @NotEmpty
    @Size(min = Constant.MIN_LENGTH,max = Constant.MAX_LENGTH)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = Constant.MIN_LENGTH,max = Constant.MAX_LENGTH)
    private String lastName;
    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = Constant.MIN_LENGTH)
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

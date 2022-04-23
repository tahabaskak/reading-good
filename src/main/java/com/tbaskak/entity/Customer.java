package com.tbaskak.entity;

import com.tbaskak.constant.Constant;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer {
    @Id
    private String id;
    @Email
    @NotNull
    @NotEmpty
    private String email;
    @NotEmpty
    @NotNull
    @Size(min = Constant.MIN_LENGTH,max = Constant.MAX_LENGTH)
    private String firstName;
    @NotEmpty
    @NotNull
    @Size(min = Constant.MIN_LENGTH,max = Constant.MAX_LENGTH)
    private String lastName;

    public Customer(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

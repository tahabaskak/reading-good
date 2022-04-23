package com.tbaskak.model.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerEmailDto {
    @NotNull
    @NotEmpty
    @Email
    private String email;

    public CustomerEmailDto() {
    }

    public CustomerEmailDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

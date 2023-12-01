package com.restapi.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Optional;

@Getter
@Setter
public class LoginRequest {

    private String username;

    private String email;

    @NotEmpty
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;
}

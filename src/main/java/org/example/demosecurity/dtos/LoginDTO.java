package org.example.demosecurity.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDTO {

    private String login;

    private String pass;

}

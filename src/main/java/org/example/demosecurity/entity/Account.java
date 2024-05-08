package org.example.demosecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.demosecurity.entity.common.Roles;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@Builder
public class Account {

    @Id
    private String login;

    private String pass;

    @Enumerated(value = EnumType.STRING)
    private Roles role;

}

package com.ca.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OID_PKFld")
    private Long oidPkFld;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

package com.ca.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @Column(name = "OID_PKFld")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oidPkFld;

    @Column(name = "name", length = 15)
    private String name;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "Mobile_Fld", length = 25, unique = true)
    private String mobileFld;


}

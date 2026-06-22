package com.elarsenaldecamisetas.app.entity;

import java.util.ArrayList;
import java.util.List;

import com.elarsenaldecamisetas.app.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="surname", nullable=false)
    private String surname;

    @Column(name="email", nullable=false, unique=true)
    private String email;

    @JsonIgnore
    @Column(name="password", length=60, nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable=false, length=20)
    private Role role = Role.CLIENTE;
    
    @Column(name="address", nullable=false)
    private String address;

    @Column(name="city", nullable=false)
    private String city;

    @Column(name="cp", nullable=false)
    private String cp;

    @Column(name="country", nullable=false)
    private String country;

    @Column(name="phone_number", nullable=false)
    private String phoneNumber;

    @OneToMany(mappedBy="user")
    private List <Order> orders = new ArrayList<>();

}

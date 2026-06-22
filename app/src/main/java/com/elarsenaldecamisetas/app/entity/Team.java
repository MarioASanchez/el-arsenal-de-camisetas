package com.elarsenaldecamisetas.app.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Teams")
@Data
public class Team {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="image", nullable=false)
    private String image;

    @ManyToOne
    @JoinColumn(name="league_id", nullable=false)
    private League league;

    @OneToMany(mappedBy="team")
    private List <Product> products;


}

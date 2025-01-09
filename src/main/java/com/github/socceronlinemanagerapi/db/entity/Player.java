package com.github.socceronlinemanagerapi.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "players", schema = "soccer_manager")
@Getter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "market_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal marketValue;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}

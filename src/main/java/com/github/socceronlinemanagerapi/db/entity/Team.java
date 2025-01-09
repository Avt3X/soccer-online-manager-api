package com.github.socceronlinemanagerapi.db.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "teams", schema = "soccer_manager")
@Getter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Setter
    @Column(nullable = false, length = 100)
    private String country;

    @Column(name = "budget", nullable = false, precision = 15, scale = 2)
    private BigDecimal budget;

    @Column(name = "team_value", precision = 15, scale = 2)
    private BigDecimal teamValue;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Player> players;
}

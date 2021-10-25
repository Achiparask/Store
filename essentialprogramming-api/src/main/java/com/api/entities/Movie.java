package com.api.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Movie")
@javax.persistence.Table(name = "Movies")

public class Movie {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Code")
    private String code;
}

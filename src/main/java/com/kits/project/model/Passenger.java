package com.kits.project.model;

import javax.persistence.*;

/**
 * Created by Lupus on 10/30/2018.
 */
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

}

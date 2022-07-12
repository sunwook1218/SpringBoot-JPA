package com.example.demo.dailystudy.jpa.jpabook.idmapping;

import javax.persistence.*;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

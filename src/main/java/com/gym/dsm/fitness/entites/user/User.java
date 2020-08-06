package com.gym.dsm.fitness.entites.user;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String studentNumber;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String password;

    private boolean sex;
}

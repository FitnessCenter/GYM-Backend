package com.gym.dsm.fitness.entities.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Builder
public class User {
    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String studentNumber;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String password;

    private boolean sex;
}

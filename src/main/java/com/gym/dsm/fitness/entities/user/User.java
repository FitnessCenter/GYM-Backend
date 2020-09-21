package com.gym.dsm.fitness.entities.user;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.exerciseApply.ExerciseApply;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
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

    @Column
    @ColumnDefault("0")
    private Integer numberOfDaysExercised;

    @OneToMany(mappedBy = "appliedUser", cascade = CascadeType.ALL)
    private List<EquipmentApply> equipmentApplies;

    @OneToOne(mappedBy = "appliedUser")
    private ExerciseApply exerciseApply;

    public User updatePassword(String password) {
        this.password = password;
        return this;
    }

    public void increaseNumberOfDaysExercised(){
        this.numberOfDaysExercised += 1;
    }
}

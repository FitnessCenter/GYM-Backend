package com.gym.dsm.fitness.entities.exerciseApply;


import com.gym.dsm.fitness.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "applied_user_id", referencedColumnName = "id", nullable = false)
    private User appliedUser;

    @Column(nullable = false)
    private Integer exerciseTime;
}

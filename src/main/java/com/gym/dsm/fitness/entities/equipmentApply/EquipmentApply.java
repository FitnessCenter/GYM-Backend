package com.gym.dsm.fitness.entities.equipmentApply;


import com.gym.dsm.fitness.entities.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "applied_user_id", nullable = false)
    private User appliedUser;

    @Column(nullable = false)
    private Boolean sex;

    @Column(nullable = false)
    private Integer numberOfApply;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String equipmentName;

    @Column(nullable = false)
    private String purchaseLink;

}

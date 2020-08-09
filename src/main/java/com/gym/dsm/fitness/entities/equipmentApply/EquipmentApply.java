package com.gym.dsm.fitness.entities.equipmentApply;


import com.gym.dsm.fitness.entities.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class EquipmentApply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "applied_user_id", nullable = false)
    private User appliedUser;

    private Integer numberOfApply;
    private Integer price;
    private String equipmentName;
    private String purchaseLink;

}

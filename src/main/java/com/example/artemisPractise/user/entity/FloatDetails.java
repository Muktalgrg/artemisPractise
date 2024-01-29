package com.example.artemisPractise.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FloatDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double floatBalance;


//    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "merchant_id")
//    @Column(nullable = false,unique = true)
    private Merchant merchant;

    @OneToOne
    @JoinColumn(name = "businessUser_id")
//    @Column(nullable = false,unique = true)
    private BusinessUser businessUser;

}

package com.example.artemisPractise.user.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "merchant")
    private FloatDetails floatDetails;

    public void setFloatDetails(FloatDetails floatDetails){
        this.floatDetails = floatDetails;
        floatDetails.setBusinessUser(this);
    }






}

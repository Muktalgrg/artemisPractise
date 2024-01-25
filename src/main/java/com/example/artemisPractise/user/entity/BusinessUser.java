package com.example.artemisPractise.user.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    @JsonBackReference
    private Merchant merchant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "merchant")
    private FloatDetails floatDetails;

    private String email;

    public void setFloatDetails(FloatDetails floatDetails){
        this.floatDetails = floatDetails;
        floatDetails.setBusinessUser(this);
    }

}

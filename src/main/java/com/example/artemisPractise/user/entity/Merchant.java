package com.example.artemisPractise.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merchantAccount")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "merchant")
    private FloatDetails floatDetails;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private List<BusinessUser> businessUserList;

    private String email;



    public void addBusinessUser(BusinessUser businessUser){
        if(businessUserList.isEmpty())
            businessUserList = new ArrayList<>();
        businessUserList.add(businessUser);
        businessUser.setMerchant(this);
    }

//    public void setFloatBalance(double amount){
//        this.floatDetails.setFloatBalance(amount);
//    }

    public void setFloatDetails(FloatDetails floatDetails){
        this.floatDetails = floatDetails;
        floatDetails.setMerchant(this);
    }

}

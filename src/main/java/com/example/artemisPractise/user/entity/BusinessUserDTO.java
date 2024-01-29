package com.example.artemisPractise.user.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class BusinessUserDTO implements Serializable {

    public String name;
    public String email;
}

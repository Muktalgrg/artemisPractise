package com.example.artemisPractise.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDTO implements Serializable {

    private String email;
    private String name;

}

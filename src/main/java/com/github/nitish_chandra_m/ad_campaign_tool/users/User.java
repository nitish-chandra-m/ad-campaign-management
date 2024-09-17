package com.github.nitish_chandra_m.ad_campaign_tool.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;

    @Email
    private String email;

}

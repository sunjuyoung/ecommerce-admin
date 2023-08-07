package com.example.admin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Builder
@ToString(exclude = {"password","id"})
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",
            initialValue = 1,
            allocationSize = 1,
            sequenceName = "user_id_seq")
    private Long id;

    private String username;

    private String password;

    private String email;

    private String role;

    private String image;




}

package com.example.admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @SequenceGenerator(name = "store_id_seq",
            initialValue = 1,
            allocationSize = 1,
            sequenceName = "store_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "store_id_seq")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    //domain create
    public Store(String name, User user) {
        this.name = name;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}

package com.progly.progly.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "user")
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @CreatedDate
    @Column
    protected Timestamp createdDate;

    @PrePersist
    protected void createDateTimestamp() {
        createdDate = Timestamp.from(Instant.now());
    }
}
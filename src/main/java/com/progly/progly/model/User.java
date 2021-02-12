package com.progly.progly.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @NotEmpty(message = "Benutzername darf nicht leer sein.")
    private String username;

    @Column(unique = true)
    @Email(message = "Bitte eine gültige Email-Adresse eingeben.")
    @NotNull
    @NotEmpty(message = "Email darf nicht leer sein.")
    private String email;

    @Column
    @NotNull
    private String password;

    @Transient
    private String retypePassword;

    @CreatedDate
    @Column
    protected Timestamp createdDate;

    @PrePersist
    protected void createDateTimestamp(){
        createdDate = Timestamp.from(Instant.now());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
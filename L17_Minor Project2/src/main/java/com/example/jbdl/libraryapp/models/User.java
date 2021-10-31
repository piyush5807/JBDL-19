package com.example.jbdl.libraryapp.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {

    private static String delimiter = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username; // basically it's an email id of the student or admin in our case
    private String password;
    private String authorities;

    private int userTypeId; // studentId if user is student else adminId;
    private boolean isStudent; // TODO: Remove this

    // ADM::5 select * from map.get(id_arr[0]) where id = id_arr[1]
    // STD::10

    private Date createdOn;

//    @OneToOne
//    private Student student;
//
//    @OneToOne
//    private Admin admin;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream(this.authorities.split(delimiter))
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

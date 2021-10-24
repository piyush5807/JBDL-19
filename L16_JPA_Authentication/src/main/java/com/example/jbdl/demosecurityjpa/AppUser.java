package com.example.jbdl.demosecurityjpa;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser implements UserDetails {

    private static final String DELIMITER = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String authorities;

    @CreationTimestamp
    private Date createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        String[]authoritiesList = this.authorities.split(DELIMITER);

//        return Arrays.stream(authoritiesList)
//                .map(x -> new SimpleGrantedAuthority(x))
//                .collect(Collectors.toList());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for(String auth : authoritiesList){
            grantedAuthorityList.add(new SimpleGrantedAuthority(auth));
        }

        return grantedAuthorityList;
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
        // if current time - createdAt > 60 days, make the credentials as expired - One use case
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

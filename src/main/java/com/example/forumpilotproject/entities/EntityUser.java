package com.example.forumpilotproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class EntityUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private String email;
    private String phoneNumber;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)//сам создает таблицу с енамом,
    // fetch = FetchType.EAGER - подгружает сразу роль
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    //создаем таблицу для набора ролей,
    // которая будет будет соединяться с таблицей пользователей
    @Enumerated(EnumType.STRING)

    private Set<Role> roles;//коллекция сет состоит из объектом енам Role, который лежит в этом же пакете

    public boolean isActive() {
        return active;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }
}

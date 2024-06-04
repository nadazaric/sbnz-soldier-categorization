package com.ftn.sbnz.model.feature_auth;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;
import java.util.HashSet;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
@Table(name = "my_user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "role",
    //         joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    //         inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String name, @NotNull String username, @NotNull String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }  
}
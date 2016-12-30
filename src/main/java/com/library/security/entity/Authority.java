package com.library.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.entity.UserEntity;
import com.library.entity.abstracts.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "authority")
public class Authority extends AbstractEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @Column(name = "is_default")
    @JsonIgnore
    private boolean defaultAuthority = false;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    private Authority() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public void setDefaultAuthority(boolean defaultAuthority) {
        this.defaultAuthority = defaultAuthority;
    }

    public boolean isDefaultAuthority() {
        return defaultAuthority;
    }
}

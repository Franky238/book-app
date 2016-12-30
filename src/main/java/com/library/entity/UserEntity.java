package com.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.entity.abstracts.AbstractEntity;
import com.library.security.entity.Authority;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity extends AbstractEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @Column
    @NotBlank
    @JsonIgnore
    private String password;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(nullable = false)
    private Long credits = 0L;

    @Column
    @NotNull
    private Boolean enabled = true;

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate = new Date();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<FileEntity> files;

    public UserEntity(String username, String password, String email, Long credits) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.credits = credits;
    }

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserEntity() {} // for repository

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setDefaultAuthority(Authority authority) {
        List<UserEntity> users = new ArrayList<UserEntity>();
        users.add(this);

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(authority);

        this.setAuthorities(authorities);
        authority.setUsers(users);
    }

    public void setDefaultAuthority(List<Authority> authorities) {
        List<UserEntity> users = new ArrayList<UserEntity>();
        users.add(this);

        this.setAuthorities(authorities);

        for (Authority authority : authorities) {
            authority.setUsers(users);
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Set<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<FileEntity> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", credits=" + credits +
                '}';
    }
}

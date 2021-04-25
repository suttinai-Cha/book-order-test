package com.scb.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date birthDate;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    @OneToMany (cascade=CascadeType.REMOVE,mappedBy="account",fetch = FetchType.LAZY)
    private Set<Orders> order;

    public Set<Orders> getOrder() {
        return order;
    }

    public void setOrder(Set<Orders> order) {
        this.order = order;
    }

    private boolean enabled = true;

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

package com.project.sports_app_backend.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {
    private Long id;
    private UserType type;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String description;
    private int phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_SPORT_USER",
            joinColumns = {@JoinColumn(name= "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SPORT_ID", referencedColumnName = "SPORT_ID")}
    )
    private List<SportEntity> sports = new ArrayList<>();

    @OneToMany(
            targetEntity = ReservationEntity.class,
            mappedBy = "userEntity",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<ReservationEntity> reservation = new ArrayList<>();








    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<SportEntity> getSports() {
        return sports;
    }

    public void setSports(List<SportEntity> sports) {
        this.sports = sports;
    }

    public List<ReservationEntity> getReservation() {
        return reservation;
    }

    public void setReservation(List<ReservationEntity> reservation) {
        this.reservation = reservation;
    }
}

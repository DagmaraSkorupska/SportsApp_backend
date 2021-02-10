package com.project.sports_app_backend.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    private List<SportEntity> sports = new ArrayList<>();
    @Access(AccessType.PROPERTY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_SPORT_USER",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SPORT_ID", referencedColumnName = "SPORT_ID")})
    public List<SportEntity> getSports() {
        return sports;
    }

    private List<ReservationEntity> reservation = new ArrayList<>();
    @Access(AccessType.PROPERTY)
    @OneToMany(
            targetEntity = ReservationEntity.class,
            mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<ReservationEntity> getReservation() {
        return reservation;
    }

    public UserEntity(UserType type, String firstName, String lastName, String email, String password, String description, String phone, List<SportEntity> sports, List<ReservationEntity> reservation) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.phone = phone;
        this.sports = sports;
        this.reservation = reservation;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public void setSports(List<SportEntity> sports) {
        this.sports = sports;
    }

    public void setReservation(List<ReservationEntity> reservation) {
        this.reservation = reservation;
    }
}

package com.project.sports_app_backend.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "USERS")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID",unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    private List<WorkoutEntity> workout = new ArrayList<>();
    @Access(AccessType.PROPERTY)
    @OneToMany(
            targetEntity = WorkoutEntity.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    public List<WorkoutEntity> getWorkout() {
        return workout;
    }

    private List<Reservation> reservation = new ArrayList<>() ;
    @Access(AccessType.PROPERTY)
    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "userEntity",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    public List<Reservation> getReservation() {
        return reservation;
    }

    public UserEntity(UserType type, String firstName, String lastName, String email, String password, String description, String phone) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.phone = phone;
    }

    public UserEntity() {
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


    public void setWorkout(List<WorkoutEntity> workout) {
        this.workout = workout;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(type.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", type=" + type +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", workout=" + workout +
                ", reservation=" + reservation +
                '}';
    }

    //todo pozmieniac dostepy
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

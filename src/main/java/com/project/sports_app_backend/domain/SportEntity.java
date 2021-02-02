package com.project.sports_app_backend.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SPORTS")
@NoArgsConstructor
public class SportEntity {

    private List<WorkoutEntity> workouts;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name="SPORT_TYPE", nullable = false)
    private String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_SPORT_USER",
            joinColumns = {@JoinColumn(name = "SPORT_ID", referencedColumnName = "SPORT_ID")},
            inverseJoinColumns = {@JoinColumn(name= "USER_ID", referencedColumnName = "USER_ID")}
    )
    private List<UserEntity> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="WORKOUT")
    public List<WorkoutEntity> getWorkouts() {
        return workouts;
    }


    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
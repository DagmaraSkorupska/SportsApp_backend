package com.project.sports_app_backend.domain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sports")
@Table(name="SPORTS")
public class SportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPORT_ID",unique = true, nullable = false)
    private Long id;

    @Column(name="SPORT_TYPE", nullable = false)
    private String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    private List<UserEntity> users = new ArrayList<>();
    @Access(AccessType.PROPERTY)
//    cascade = CascadeType.PERSIST,
    @ManyToMany( mappedBy = "sports")
    public List<UserEntity> getUsers() {
        return users;
    }

    private WorkoutEntity workouts;
    @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name="WORKOUTS_ID")
    public WorkoutEntity getWorkouts() {
        return workouts;
    }

    public void setWorkouts(WorkoutEntity workouts) {
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

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
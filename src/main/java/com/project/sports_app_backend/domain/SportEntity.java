package com.project.sports_app_backend.domain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "sports")
@Table(name="SPORTS")
public class SportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPORT_ID",unique = true, nullable = false)
    private Long id;

    @Column(name="SPORT_TYPE", nullable = false)
    public String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    private List<UserEntity> users = new ArrayList<>();
    @Access(AccessType.PROPERTY)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "sports", fetch = FetchType.EAGER)
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

    public SportEntity() {
    }

    public SportEntity(String name, String description, List<UserEntity> users, WorkoutEntity workouts) {
        this.name = name;
        this.description = description;
        this.users = users;
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

    @Override
    public String toString() {
        return "SportEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", workouts=" + workouts +
                '}';
    }
}
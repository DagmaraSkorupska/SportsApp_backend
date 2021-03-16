package com.project.sports_app_backend.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",unique = true, nullable = false)
    private long id;

    @Column(name = "TO_PAY", nullable = false)
    private double toPay;

    @Column(name = "DAY_WORKOUT", nullable = false)
    private String day;

    @Column(name = "HOUR_WORKOUT", nullable = false)
    private String hour;

    private UserEntity userEntity;
    @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userId) {
        this.userEntity = userId;
    }

    private WorkoutEntity workoutEntity;
    @Access(AccessType.PROPERTY)
    @OneToOne( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKOUT_ID")
    public WorkoutEntity getWorkoutEntity() {
        return workoutEntity;
    }

    public void setWorkoutEntity(WorkoutEntity workoutEntity) {
        this.workoutEntity = workoutEntity;
    }

    public Reservation(double toPay, String day, String hour, UserEntity userEntity, WorkoutEntity workoutEntity) {
        this.toPay = toPay;
        this.day = day;
        this.hour = hour;
        this.userEntity = userEntity;
        this.workoutEntity = workoutEntity;
    }

    public Reservation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getToPay() {
        return toPay;
    }

    public void setToPay(double toPay) {
        this.toPay = toPay;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}

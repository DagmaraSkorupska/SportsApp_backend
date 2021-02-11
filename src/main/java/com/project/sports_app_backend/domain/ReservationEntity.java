package com.project.sports_app_backend.domain;

import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVATIONS")
@NoArgsConstructor

public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "TO_PAY", nullable = false)
    private double toPay;

    @GeneratedValue
    @Column(name = "DATE_RESERVATION", nullable = false)
    private Date date;

    private UserEntity userEntity = new UserEntity();

    @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userId) {
        this.userEntity = userId;
    }

    private WorkoutEntity workoutEntity = new WorkoutEntity();
    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "WORKOUT_ID")
    public WorkoutEntity getWorkoutEntity() {
        return workoutEntity;
    }

    public void setWorkoutEntity(WorkoutEntity workoutEntity) {
        this.workoutEntity = workoutEntity;
    }


    public ReservationEntity(double toPay, Date date, UserEntity userEntity, WorkoutEntity workoutEntity) {
        this.toPay = toPay;
        this.date = new Date();
        this.userEntity = userEntity;
        this.workoutEntity = workoutEntity;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

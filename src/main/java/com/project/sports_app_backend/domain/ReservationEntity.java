package com.project.sports_app_backend.domain;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

    private UserEntity userId;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private long id;

    @OneToOne(mappedBy = "reservation")
    private WorkoutEntity workout;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public UserEntity getUserEntity() {
        return userId;
    }

    @Column(name = "TO_PAY", nullable = false)
    private double toPay;

    @Column(name = "DATE_RESERVATION", nullable = false)
    private Date date;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutEntity workout) {
        this.workout = workout;
    }


    public void setUserEntity(UserEntity userId) {
        this.userId = userId;
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

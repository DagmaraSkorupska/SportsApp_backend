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

    private UserEntity userEntity;
    @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Column(name = "TO_PAY", nullable = false)
    private double toPay;

    @Column(name = "DATE_RESERVATION", nullable = false)
    private Date date;

    public ReservationEntity(double toPay, Date date, WorkoutEntity workoutEntity, UserEntity userEntity  ) {
        this.toPay = toPay;
        this.date = new Date();
        this.userEntity = userEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserEntity(UserEntity userId) {
        this.userEntity = userId;
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

package com.project.sports_app_backend.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "WORKOUT")
@NoArgsConstructor
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_WORKOUT", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DURATION_MINUTE", nullable = false)
    private int durationMin;

    @Column(name = "PRICE_PER_HOUR", nullable = false)
    private double price1h;

    @Column(name = "DATE_OF_WORKOUT", nullable = false)
    private Date date;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    private List<SportEntity> sport = new ArrayList<>();
    @Access(AccessType.PROPERTY)
    @OneToMany(targetEntity = SportEntity.class,
            mappedBy = "workouts",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<SportEntity> getSport() {
        return sport;
    }

    public void setSport(List<SportEntity> sport) {
        this.sport = sport;
    }

    private ReservationEntity reservationEntity;
    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RESERVATION_ID")
    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
    }



    public WorkoutEntity(String name, String description, int durationMin, double price1h, Date date, String address) {
        this.name = name;
        this.description = description;
        this.durationMin = durationMin;
        this.price1h = price1h;
        this.date = new Date();
        this.address = address;
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

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public double getPrice1h() {
        return price1h;
    }

    public void setPrice1h(double price1h) {
        this.price1h = price1h;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }





}

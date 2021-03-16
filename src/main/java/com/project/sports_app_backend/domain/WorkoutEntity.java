package com.project.sports_app_backend.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "WORKOUT")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WORKOUT_ID",unique = true, nullable = false)
    private Long id;

//    public enum TypeSport {
//        Swim, Run, Bike, Gym, Tennis
//    }

    @Column(name = "NAME_WORKOUT", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DURATION_MINUTE", nullable = false)
    private int durationMin;

    @Column(name = "PRICE_PER_HOUR", nullable = false)
    private double price1h;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    private UserEntity user;
    @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name="USER_ID")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(name = "DAY_WORKOUT", nullable = false)
    private String day;

    @Column(name = "HOUR_WORKOUT", nullable = false)
    private String hour;

//    @Enumerated(EnumType.STRING)
//    private WorkoutEntity.TypeSport typeSport;


//    private ReservationEntity reservationEntity = new ReservationEntity();
//    @Access(AccessType.PROPERTY)
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "RESERVATION_ID")
//    public ReservationEntity getReservationEntity() {
//        return reservationEntity;
//    }
//
//    public void setReservationEntity(ReservationEntity reservationEntity) {
//        this.reservationEntity = reservationEntity;
//    }



    public WorkoutEntity( String name, String description, int durationMin, double price1h, String address, String day, String hour, UserEntity user) {
        this.name = name;
        this.description = description;
        this.durationMin = durationMin;
        this.price1h = price1h;
        this.address = address;
        this.day = day;
        this.hour = hour;
        this.user = user;

    }

    public WorkoutEntity() {
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public TypeSport getTypeSport() {
//        return typeSport;
//    }
//
//    public void setTypeSport(TypeSport typeSport) {
//        this.typeSport = typeSport;
//    }


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

    @Override
    public String toString() {
        return "WorkoutEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", durationMin=" + durationMin +
                ", price1h=" + price1h +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }


}

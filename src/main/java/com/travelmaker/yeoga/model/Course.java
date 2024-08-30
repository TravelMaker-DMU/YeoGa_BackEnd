package com.travelmaker.yeoga.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table

public class Course {

    @Id
    @Column(unique = true, nullable = false)
    private String COURSEID;

    @Column
    private String COURSETITLE;

    @Column
    private int RECOMMENDATION;

    @Column
    private Date CREATIONDATE;

    @Id
    @ManyToOne
    @JoinColumn(name = "THEMEID", referencedColumnName = "THEMEID")
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "LODGINGID", referencedColumnName = "LODGINGID")
    private Lodging lodging;

    @ManyToOne
    @JoinColumn(name = "RESTAURANTID", referencedColumnName = "RESTAURANTID")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "CITYID", referencedColumnName = "CITYID")
    private City city;


}

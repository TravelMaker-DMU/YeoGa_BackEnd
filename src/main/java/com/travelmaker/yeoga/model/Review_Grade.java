package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table
public class Review_Grade {
    @Id
    @Column
    private String REVIEWID;

    @Column
    private Integer GRADE;

    @Column
    private Long REVIEW;

    @Column
    private Date WRITEDATE;

    @Id
    @ManyToOne
    @JoinColumn(name = "COURSEID", referencedColumnName = "COURSEID")
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "UUID", referencedColumnName = "UUID")
    private ACCOUNT account;

}

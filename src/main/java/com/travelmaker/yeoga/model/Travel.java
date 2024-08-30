package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Data
@Table
public class Travel {
    @Id
    @Column
    private String TRAVELID;

    @Id
    @ManyToOne
    @JoinColumn(name = "COURSEID", referencedColumnName = "COURSEID")
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "UUID", referencedColumnName = "UUID")
    private ACCOUNT account;

    @Column
    private String DEPATURE;

    @Column
    private String ARRIVAL;

    @Id
    @ManyToOne
    @JoinColumn(name = "PATHID", referencedColumnName = "PATHID")
    private Path path;

    @Column
    private String RECCHECK;
}

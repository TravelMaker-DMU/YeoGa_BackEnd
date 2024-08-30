package com.travelmaker.yeoga.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="CALLENDER")

public class Callender {

    @Id
    @Column
    private String CALLENDERID;

    @Column
    private String SCHEDULE;

    @Column
    private String COURSEID;

    @ManyToOne
    @JoinColumn(name = "UUID", referencedColumnName = "UUID")
    private ACCOUNT account;

    @ManyToOne
    @JoinColumn(name = "PATHID", referencedColumnName = "PATHID")
    private Path path;

}

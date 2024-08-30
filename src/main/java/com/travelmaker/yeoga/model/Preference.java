package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Data
@Table
public class Preference {

    @Id
    @Column
    private String PREFERENCEID;

    @Column
    private String PREFERENCEVALUE;

    @Id
    @ManyToOne
    @JoinColumn(name = "UUID", referencedColumnName = "UUID")
    private ACCOUNT account;

    @Id
    @ManyToOne
    @JoinColumn(name = "THEMEID", referencedColumnName = "THEMEID")
    private Theme theme;
}

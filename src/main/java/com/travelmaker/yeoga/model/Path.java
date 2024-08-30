package com.travelmaker.yeoga.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table

public class Path {
    @Id
    private String PATHID;

    @Id
    private String TRANSPORTATION;

    @Id
    private String DEPATURE;

    @Id
    private String ARRIVAL;
}
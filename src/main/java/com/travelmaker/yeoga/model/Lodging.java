package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table
public class Lodging {
    @Id
    @Column
    private String LODGINGID;

    @Column
    private String LODGINGNAME;

    @Column
    private String LODGINGADDRESS;

    @Column
    private String LODGINGTYPE;
}

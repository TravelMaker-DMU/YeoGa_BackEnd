package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table
public class City {
    @Id
    @Column
    String CITYID;

    @Column
    String CITYNAME;

}

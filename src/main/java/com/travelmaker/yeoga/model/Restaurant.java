package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table
public class Restaurant {
    @Id
    @Column
    private String RESTAURANTID;

    @Column
    private String RESTAURANTNAME;

    @Column
    private String RESTAURANTADDRESS;

    @Column
    private String RESTAURANTTYPE;
}

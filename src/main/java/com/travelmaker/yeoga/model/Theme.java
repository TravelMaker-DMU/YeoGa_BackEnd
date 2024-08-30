package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table
public class Theme {
    @Id
    @Column
    private String THEMEID;

    @Column
    private String THEMENAME;
}

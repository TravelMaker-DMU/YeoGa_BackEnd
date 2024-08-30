package com.travelmaker.yeoga.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name ="BOOKMARK")

public class Bookmark {

    @Id
    @Column
    String BOOKMARKID;

    @Column
    String BOOKMARKTITLE;

    @Column
    Date BMCDATE;

    @ManyToOne
    @JoinColumn(name = "UUID", referencedColumnName = "UUID")
    private ACCOUNT account;

}

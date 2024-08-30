package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;
import com.travelmaker.yeoga.model.ACCOUNT;

@Entity
@Data
@Table
public class RecentView {

    @ManyToOne
    @JoinColumn(name = "UUID", referencedColumnName = "UUID")
    private ACCOUNT account;

    @ManyToOne
    @JoinColumn(name = "COURSEID", referencedColumnName = "COURSEID")
    private Course course;

    public ACCOUNT getAccount() {
        return account;
    }

    public void setAccount(ACCOUNT account) {
        this.account = account;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

package com.kits.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SCEDULE")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
}

package com.kits.project.DTOs;

import java.util.Date;

public class RequestDateDTO {
    public Date startDate;
    public Date endDate;

    public RequestDateDTO() {}

    public RequestDateDTO(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

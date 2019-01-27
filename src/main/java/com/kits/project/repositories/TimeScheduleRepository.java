package com.kits.project.repositories;

import com.kits.project.model.TimeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeScheduleRepository extends JpaRepository<TimeSchedule, Long> {

    @Query(value="select * from timeschedule inner join timeschedule_working_day_schedule as twd on cast(working_day_schedule AS TIME) > CURTIME() AND cast(working_day_schedule AS TIME)<ADDTIME(now(), '2000');",nativeQuery = true)
    List<TimeSchedule> findCurrentDepartures();
}

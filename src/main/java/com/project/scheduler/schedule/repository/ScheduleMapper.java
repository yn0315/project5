package com.project.scheduler.schedule.repository;

import com.project.scheduler.schedule.domain.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    // 월별 스케쥴 가져오기
    List<Schedule> getScheduleList(String[] codes);

    // 일정 하나 가져오기
    Schedule getSchedule(int scdNo);

    // 스케쥴 등록
    boolean insertSchedule(Schedule schedule);

    // 스케쥴 삭제
    boolean deleteSchedule(int scdNo);

    // 스케쥴 수정
    boolean modifySchedule(Schedule schedule);
}

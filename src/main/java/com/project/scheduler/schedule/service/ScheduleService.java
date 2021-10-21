package com.project.scheduler.schedule.service;

import com.project.scheduler.employee.domain.Employee;
import com.project.scheduler.employee.repository.EmployeeMapper;
import com.project.scheduler.schedule.domain.Schedule;
import com.project.scheduler.schedule.repository.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final EmployeeMapper employeeMapper;

    // 스케쥴 가져오기
    public List<Schedule> getScheduleList(String type, int empNo){
        Employee employee = employeeMapper.getEmployee(empNo);
        String[] types = spliteType(type);
        String[] codes = new String[types.length];
        for (int i = 0; i < codes.length; i++) {
            codes[i] = makeScdCode(types[i], employee);
        }
        List<Schedule> scheduleList = scheduleMapper.getScheduleList(codes);
        return scheduleList;
    }

    // 일정 하나 들고오기
    public Schedule getSchedule(int empNo) {
        return scheduleMapper.getSchedule(empNo);
    }

    // 일정 등록
    public boolean insertSchedule(String type, Schedule schedule, int empNo){
        Employee employee = employeeMapper.getEmployee(empNo);

        schedule.setWriterEmpNo(empNo);
        // 날짜 변환
        try {
            schedule.setStartDate(stringToDate(schedule.getStartDay(), schedule.getStartTime()));
            schedule.setEndDate(stringToDate(schedule.getEndDay(), schedule.getEndTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 코드 생성
        schedule.setScdCode(makeScdCode(type, employee));

        return scheduleMapper.insertSchedule(schedule);
    }

    // 스케쥴 삭제
    public boolean deleteSchedule(int scdNo){
        return scheduleMapper.deleteSchedule(scdNo);
    }

    // 스케쥴 수정
    public boolean modifySchedule(Schedule schedule){
        try {
            schedule.setStartDate(stringToDate(schedule.getStartDay(), schedule.getStartTime()));
            schedule.setEndDate(stringToDate(schedule.getEndDay(), schedule.getEndTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info(schedule);
        return scheduleMapper.modifySchedule(schedule);
    }

    // String(날짜와 시간)을 Date타입으로 변환
    private Date stringToDate(String day, String time) throws ParseException {
        String dateStr = day.concat(time);
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        Date date = fm.parse(dateStr);
        return date;
    }

    // 일정 코드 생성
    private String makeScdCode(String type, Employee employee) {
        String code;
        switch (type) {
            case "C":
                code = type;
                break;
            case "D":
                code = type + "-" + employee.getDep().getDptNo();
                break;
            case "P":
                code = type + "-" + employee.getPos().getPosNo();
                break;
            default:
                code = "";
        }
        return code;
    }

    // 타입 잘라내기
    private String[] spliteType(String type) {
        String[] types = type.trim().split(" ");
        return types;
    }
}

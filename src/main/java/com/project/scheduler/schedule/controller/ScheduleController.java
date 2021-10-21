package com.project.scheduler.schedule.controller;

import com.project.scheduler.schedule.domain.Schedule;
import com.project.scheduler.schedule.service.ScheduleService;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Log4j2
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 추가 페이지 이동
    @GetMapping("/add/{empNo}")
    public String add(@PathVariable int empNo, Model model) {
        model.addAttribute("empNo", empNo);
        return "/add";
    }

    // 추가 기능
    @PostMapping("/add-schedule/{empNo}")
    public String scdAdd(Schedule schedule, String type, @PathVariable int empNo) {
        scheduleService.insertSchedule(type, schedule, empNo);
        return "redirect:/schedule/calendar/" + empNo;
    }

    // 일정표 페이지 이동
    @GetMapping("/calendar/{empNo}")
    public String calendar(@PathVariable int empNo, Model model) {
        model.addAttribute("empNo", empNo);
        return "/calendar";
    }

    // 일정 가져오는 기능
    @GetMapping("/get-schedule/{empNo}")
    @ResponseBody
    public ResponseEntity<List<Schedule>> data (String type, @PathVariable int empNo) {
        List<Schedule> schedules = scheduleService.getScheduleList(type, empNo);
        log.info(schedules);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // 수정 페이지 이동
    @GetMapping("/modify/{empNo}")
    public String modify(@PathVariable int empNo, int scdNo, Model model) {
        model.addAttribute("empNo", empNo);
        Schedule schedule = scheduleService.getSchedule(scdNo);
        model.addAttribute("schedule", schedule);
        return "/modify";
    }

    // 수정 기능
    @PostMapping("/mod-schedule/{empNo}")
    public String modify(@PathVariable int empNo, Schedule schedule) {
        scheduleService.modifySchedule(schedule);
        return "redirect:/schedule/calendar/" + empNo;
    }

    // 삭제 기능
    @GetMapping("/del-schedule/{scdNo}")
    @ResponseBody
    public  ResponseEntity<String> delete(@PathVariable int scdNo) {
        log.info(scdNo);
        return scheduleService.deleteSchedule(scdNo)
                ? new ResponseEntity<>("deleteSuccess", HttpStatus.OK)
                : new ResponseEntity<>("deleteFail", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
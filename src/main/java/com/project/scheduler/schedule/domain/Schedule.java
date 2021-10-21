package com.project.scheduler.schedule.domain;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    
    // DB 연동 데이터
    private int scdNo; // 스케쥴 번호
    private String scdTitle; // 스케쥴 제목
    private int writerEmpNo; // 작성자 사번
    private String scdCode; // 스케쥴 코드 (A-00, A : 회사 일정, D : 부서 일정, P: 개인 일정 - 부서(사원) 번호)
    private Date startDate; // 스케쥴 시작 날짜
    private Date endDate; // 스케쥴 종료 날짜

    // full calendar 로 보내기 위한 데이터
    private String start; // 시작 날짜 문자열
    private String end; // 종료 날짜 문자열
    private String color;
    
    // 클라이언트로 부터 받는 데이터
    private String startDay;//스케쥴 날짜(월,일)
    private String startTime;//스케쥴시작시간
    private String endDay;//스케쥴 종료
    private String endTime;//스케쥴종료시간

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        String[] startStrArray= dateToString(startDate);
        start = startStrArray[0];
        startDay = startStrArray[1];
        startTime = startStrArray[2];
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        String[] endStrArray= dateToString(endDate);
        end = endStrArray[0];
        endDay = endStrArray[1];
        endTime = endStrArray[2];
    }

    public void setScdCode(String scdCode) {
        this.scdCode = scdCode;
        String type = scdCode.substring(0,1);
        switch (type) {
            case "C":
                this.color = "red";
                break;
            case "D":
                this.color = "black";
                break;
            case "P":
            default:
                this.color = "blue";
        }
    }

    private String[] dateToString(Date date) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String[] changedDate = new String[3];
        changedDate[0] = dayFormat.format(date) + "T" + timeFormat.format(date);
        changedDate[1] = dayFormat.format(date); // 날짜
        changedDate[2] = timeFormat.format(date); // 시간
        return changedDate;
    }
}

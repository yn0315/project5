package com.project.scheduler.employee.domain;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private int empNo; // 사원번호
    private String empName; // 사원 이름
    private Department dep; // 부서 정보
    private Position pos; // 직급 정보
}

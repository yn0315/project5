package com.project.scheduler.employee.domain;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    
    private int dptNo; // 부서 번호
    private String dptName; // 부서 이름
}

package com.project.scheduler.employee.domain;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private int posNo; // 직급 번호
    private String posName; // 직급 명
}

package com.project.scheduler.employee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("사번 1을 확인하여 1이 나와야 한다.")
    void successLogin() {
        System.out.println(employeeService.checkEmpNo(1));
    }

    @Test
    @DisplayName("사번 10을 확인하여 0이 나와야 한다.")
    void failLogin() {
        System.out.println(employeeService.checkEmpNo(10));    }

}
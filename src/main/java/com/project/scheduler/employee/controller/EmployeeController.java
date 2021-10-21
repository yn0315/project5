package com.project.scheduler.employee.controller;

import com.project.scheduler.employee.domain.Employee;
import com.project.scheduler.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Log4j2
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public String main() {
        return "/login";
    }
    
    // 로그인
    @PostMapping("/login")
    public String login(int empNo, String password) {
        if(employeeService.checkEmpNo(empNo)) {
            if(employeeService.getPassword(empNo).equals(password)) {
                // 로그인 성공
                log.info(empNo + "님 로그인성공!!");
                return "redirect:/schedule/calendar/" + empNo;
            }
            // 패스워드 불일치
        }
        log.info(empNo + "님 로그인실패!!");
        // 없는 사번
        return "redirect:";
    }
    
    // 사원 정보 들고오기
    @GetMapping("/get-empInfo/{empNo}")
    @ResponseBody
    public ResponseEntity<Employee> getInfo(@PathVariable int empNo) {
        Employee employee = employeeService.getEmployee(empNo);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}

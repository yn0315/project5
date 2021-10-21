package com.project.scheduler.employee.service;

import com.project.scheduler.employee.domain.Employee;
import com.project.scheduler.employee.repository.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    // 사원정보 가져오기 - 사번을 통해 사원 정보 가져옴
    public Employee getEmployee(int empNo) {
       return employeeMapper.getEmployee(empNo);
    }

    // 사번 확인
    public boolean checkEmpNo(int empNo) {
        if (employeeMapper.checkEmpNo(empNo) == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    // 비밀번호 가져오기
    public String getPassword(int empNo) {
        return employeeMapper.getPassword(empNo);
    }

    // 이후 사원등록

    // 사원 정보 수정
    boolean modifyEmployee(Employee employee) {
        return employeeMapper.modifyEmployee(employee);
    }

    // 사원 정보 삭제
    boolean deleteEmployee(int empNo){
        return employeeMapper.deleteEmployee(empNo);
    }

}

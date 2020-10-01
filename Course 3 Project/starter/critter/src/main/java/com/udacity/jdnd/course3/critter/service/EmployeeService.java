package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.time.DayOfWeek;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmployee(Long id){
        return employeeRepository.getOne(id);
    }

    public void setDaysAvailable(Set<DayOfWeek> availableDays, Long employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElse(null);

        if (employee == null){
            return;
        }

        employee.setDaysAvailable(availableDays);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> employeeSkills){
        List<Employee> employees = new ArrayList<>();
        List<Employee> matchedEmployees = new ArrayList<>();
        employees = employeeRepository.getEmployeesByDaysAvailable(date.getDayOfWeek());

        for (Employee employee : employees){
            if(employee.getSkills().containsAll(employeeSkills)){
                matchedEmployees.add(employee);
            }
        }
        return matchedEmployees;
    }
}

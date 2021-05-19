package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.exceptions.CritterException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;


/**
 * The type Employee service.
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Save employee employee.
     *
     * @param employee the employee
     * @return the employee
     */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Find by id employee.
     *
     * @param id the id
     * @return the employee
     */
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new CritterException("Employee",id));
    }

    /**
     * Gets by availability.
     *
     * @param dayOfWeek the day of week
     * @return the by availability
     */
    public List<Employee> getByAvailability(DayOfWeek dayOfWeek) {
        return employeeRepository.getAllByDaysAvailableContains(dayOfWeek);
    }
}


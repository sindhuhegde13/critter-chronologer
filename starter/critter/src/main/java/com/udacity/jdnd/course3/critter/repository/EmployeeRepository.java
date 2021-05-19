package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

/**
 * The interface Employee repository.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    /**
     * Gets all by days available contains.
     *
     * @param dayOfWeek the day of week
     * @return the all by days available contains
     */
    List<Employee> getAllByDaysAvailableContains(DayOfWeek dayOfWeek);
}

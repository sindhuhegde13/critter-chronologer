package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Schedule repository.
 */
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    /**
     * Gets all by pet in.
     *
     * @param pets the pets
     * @return the all by pet in
     */
    List<Schedule> getAllByPetIn(List<Pet> pets);

    /**
     * Gets all by pet contains.
     *
     * @param pet the pet
     * @return the all by pet contains
     */
    List<Schedule> getAllByPetContains(Pet pet);

    /**
     * Gets all by employee contains.
     *
     * @param employee the employee
     * @return the all by employee contains
     */
    List<Schedule> getAllByEmployeeContains(Employee employee);
}

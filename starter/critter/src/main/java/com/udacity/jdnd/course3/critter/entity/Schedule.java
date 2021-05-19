package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * The type Schedule.
 */
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employee;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pet;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public List<Employee> getEmployee() {
        return employee;
    }

    /**
     * Sets employee.
     *
     * @param employee the employee
     */
    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    /**
     * Gets pet.
     *
     * @return the pet
     */
    public List<Pet> getPet() {
        return pet;
    }

    /**
     * Sets pet.
     *
     * @param pet the pet
     */
    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets activities.
     *
     * @return the activities
     */
    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    /**
     * Sets activities.
     *
     * @param activities the activities
     */
    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}

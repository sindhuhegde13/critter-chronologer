package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exceptions.CritterException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Scheduler service.
 */
@Service
@Transactional
public class SchedulerService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;


    /**
     * Create schedule schedule.
     *
     * @param schedule    the schedule
     * @param employeeIds the employee ids
     * @param petIds      the pet ids
     * @return the schedule
     */
    public Schedule createSchedule(Schedule schedule, List<Employee> employeeIds, List<Pet> petIds) {
        schedule.setEmployee(employeeIds);
        schedule.setPet(petIds);
        return scheduleRepository.save(schedule);
    }

    /**
     * Send employee employee.
     *
     * @param id the id
     * @return the employee
     */
    public Employee sendEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new CritterException("Employee",id));
    }

    /**
     * Send pet pet.
     *
     * @param id the id
     * @return the pet
     */
    public Pet sendPet(Long id) {
        return petRepository.findById(id).orElseThrow(()->new CritterException("Pet",id));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    /**
     * Find schedule for pet list.
     *
     * @param petId the pet id
     * @return the list
     */
    public List<Schedule> findScheduleForPet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(()->new CritterException("Pet",petId));
        return scheduleRepository.getAllByPetContains(pet);
    }

    /**
     * Find schedule for employee list.
     *
     * @param empId the emp id
     * @return the list
     */
    public List<Schedule> findScheduleForEmployee(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()->new CritterException("Employee",empId));
        return scheduleRepository.getAllByEmployeeContains(employee);
    }

    /**
     * Find schedule for customer list.
     *
     * @param customerId the customer id
     * @return the list
     */
    public List<Schedule> findScheduleForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CritterException("Customer",customerId));
        return scheduleRepository.getAllByPetIn(customer.getPets());
    }
}

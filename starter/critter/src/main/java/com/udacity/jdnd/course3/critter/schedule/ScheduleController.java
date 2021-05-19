package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.SchedulerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private SchedulerService schedulerService;

    /**
     * Create schedule schedule dto.
     *
     * @param scheduleDTO the schedule dto
     * @return the schedule dto
     */
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertDTOToEntity(scheduleDTO);
        Schedule schedule1 = schedulerService.createSchedule(schedule,schedule.getEmployee(),schedule.getPet());
        return convertEntityToDTO(schedule1);
    }

    /**
     * Gets all schedules.
     *
     * @return the all schedules
     */
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertToList(schedulerService.findAll());
    }

    /**
     * Gets schedule for pet.
     *
     * @param petId the pet id
     * @return the schedule for pet
     */
    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertToList(schedulerService.findScheduleForPet(petId));
    }

    /**
     * Gets schedule for employee.
     *
     * @param employeeId the employee id
     * @return the schedule for employee
     */
    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return convertToList(schedulerService.findScheduleForEmployee(employeeId));
    }

    /**
     * Gets schedule for customer.
     *
     * @param customerId the customer id
     * @return the schedule for customer
     */
    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return convertToList(schedulerService.findScheduleForCustomer(customerId));
    }

    private Schedule convertDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        if(scheduleDTO.getEmployeeIds()!=null){
            schedule.setEmployee(scheduleDTO.getEmployeeIds().stream().map(this::sendEmployee).collect(Collectors.toList()));
        }
        if(scheduleDTO.getPetIds()!=null){
            schedule.setPet(scheduleDTO.getPetIds().stream().map(this::sendPet).collect(Collectors.toList()));
        }
        BeanUtils.copyProperties(scheduleDTO,schedule);
        return schedule;
    }

    private Employee sendEmployee(Long id) {
        return schedulerService.sendEmployee(id);
    }
    private Pet sendPet(Long id) {
        return schedulerService.sendPet(id);
    }

    private ScheduleDTO convertEntityToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        if(schedule.getEmployee()!=null){
            scheduleDTO.setEmployeeIds(schedule.getEmployee().stream().map(Employee::getId).collect(Collectors.toList()));
        }
        if(schedule.getPet()!=null){
            scheduleDTO.setPetIds(schedule.getPet().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        BeanUtils.copyProperties(schedule,scheduleDTO);
        return scheduleDTO;
    }

    private List<ScheduleDTO> convertToList(List<Schedule> scheduleList) {
        return scheduleList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
}

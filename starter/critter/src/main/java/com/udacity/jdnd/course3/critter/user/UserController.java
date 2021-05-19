package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Save customer customer dto.
     *
     * @param customerDTO the customer dto
     * @return the customer dto
     */
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertDTOToEntity(customerDTO);
        List<Long> petList = customerDTO.getPetIds();
        Customer customer1 = customerService.saveCustomer(customer,petList);
        return convertEntityToDTO(customer1);
    }

    /**
     * Get all customers list.
     *
     * @return the list
     */
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList =  customerService.findAllCustomers();
        return convertToList(customerList);
    }

    /**
     * Get owner by pet customer dto.
     *
     * @param petId the pet id
     * @return the customer dto
     */
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getCustomerByPet(petId);
        return convertEntityToDTO(customer);
    }

    /**
     * Save employee employee dto.
     *
     * @param employeeDTO the employee dto
     * @return the employee dto
     */
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertDTOToEntityEmployee(employeeDTO);
        Employee employee1 = employeeService.saveEmployee(employee);
        return convertEntityToDTOEmployee(employee1);
    }

    /**
     * Gets employee.
     *
     * @param employeeId the employee id
     * @return the employee
     */
    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEntityToDTOEmployee(employeeService.findById(employeeId));
    }

    /**
     * Sets availability.
     *
     * @param daysAvailable the days available
     * @param employeeId    the employee id
     */
    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
       Employee employee = employeeService.findById(employeeId);
       employee.setDaysAvailable(daysAvailable);
       employeeService.saveEmployee(employee);
    }

    /**
     * Find employees for service list.
     *
     * @param employeeDTO the employee dto
     * @return the list
     */
    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> employeeSkills = employeeDTO.getSkills();
        List<Employee> employeeList = employeeService.getByAvailability(dayOfWeek);
        List<Employee> employeeList1 = new ArrayList<>();
        if(employeeList!=null && !employeeList.isEmpty()) {
            for (Employee employee : employeeList) {
                if (employee.getSkills().containsAll(employeeSkills)) {
                    employeeList1.add(employee);
                }
            }
        }
        return convertToListEmployee(employeeList1);
    }

    private Customer convertDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    private CustomerDTO convertEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        if(customer.getPets()!=null) {
            customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    private List<CustomerDTO> convertToList(List<Customer> customerList) {
        return customerList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private Employee convertDTOToEntityEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return employee;
    }

    private EmployeeDTO convertEntityToDTOEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee,employeeDTO);
        return employeeDTO;
    }

    private List<EmployeeDTO> convertToListEmployee(List<Employee> employeeList) {
        return employeeList.stream().map(this::convertEntityToDTOEmployee).collect(Collectors.toList());
    }

}

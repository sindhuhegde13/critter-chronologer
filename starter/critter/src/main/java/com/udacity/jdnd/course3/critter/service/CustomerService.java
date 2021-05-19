package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exceptions.CritterException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer service.
 */
@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    /**
     * Save customer customer.
     *
     * @param customer the customer
     * @param petIds   the pet ids
     * @return the customer
     */
    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        if (petIds != null && !petIds.isEmpty()) {
            List<Pet> petList = new ArrayList<>();
            for (Long petId : petIds) {
                Pet pet = petRepository.findById(petId).orElseThrow(() -> new CritterException("Pet",petId));
                petList.add(pet);
            }
            customer.setPets(petList);
        }
        return customerRepository.save(customer);
    }

    /**
     * Find by id customer.
     *
     * @param id the id
     * @return the customer
     */
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new CritterException("Customer",id));
    }

    /**
     * Find all customers list.
     *
     * @return the list
     */
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Gets customer by pet.
     *
     * @param petId the pet id
     * @return the customer by pet
     */
    public Customer getCustomerByPet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(()-> new CritterException("Pet", petId));
        return pet.getCustomer();
    }

}

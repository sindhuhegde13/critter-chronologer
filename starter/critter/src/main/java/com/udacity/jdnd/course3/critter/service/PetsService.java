package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exceptions.CritterException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * The type Pets service.
 */
@Service
@Transactional
public class PetsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    /**
     * Save pet pet.
     *
     * @param pets the pets
     * @param id   the id
     * @return the pet
     */
    public Pet savePet(Pet pets, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CritterException("Customer",id));
        pets.setCustomer(customer);
        Pet pets1 = petRepository.save(pets);
        customer.setEachPet(pets1);
        customerRepository.save(customer);
        return pets1;
    }

    /**
     * Find by id pet.
     *
     * @param id the id
     * @return the pet
     */
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(()-> new CritterException("Pet",id));
    }

    /**
     * Find all pets list.
     *
     * @return the list
     */
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }


    /**
     * Find all customer pets list.
     *
     * @param customerId the customer id
     * @return the list
     */
    public List<Pet> findAllCustomerPets(Long customerId) {
        return petRepository.getAllByCustomerId(customerId);
    }


}

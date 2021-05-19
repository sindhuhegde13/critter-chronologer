package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Pet repository.
 */
public interface PetRepository extends JpaRepository<Pet,Long> {
    /**
     * Gets all by customer id.
     *
     * @param customerId the customer id
     * @return the all by customer id
     */
    List<Pet> getAllByCustomerId(Long customerId);
}

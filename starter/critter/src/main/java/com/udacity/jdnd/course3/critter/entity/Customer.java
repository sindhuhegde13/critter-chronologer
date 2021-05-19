package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer.
 */
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phoneNumber;

    @Column(length = 1000)
    private String notes;

    @OneToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    /**
     * Instantiates a new Customer.
     */
    public Customer() {
    }

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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets pets.
     *
     * @return the pets
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * Sets pets.
     *
     * @param petIds the pet ids
     */
    public void setPets(List<Pet> petIds) {
        this.pets = pets;
    }

    /**
     * Sets each pet.
     *
     * @param pet the pet
     */
    public void setEachPet(Pet pet) {
        if(pets == null) {
            pets = new ArrayList<>();
        }
        pets.add(pet);
    }
}

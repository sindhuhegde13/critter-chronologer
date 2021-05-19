package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The type Pet.
 */
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private PetType type;

    @Nationalized
    private String name;
    private LocalDate birthDate;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;

    /**
     * Instantiates a new Pet.
     */
    public Pet() {}

    /**
     * Instantiates a new Pet.
     *
     * @param id        the id
     * @param type      the type
     * @param name      the name
     * @param birthDate the birth date
     * @param notes     the notes
     * @param customer  the customer
     */
    public Pet(long id, PetType type, String name, LocalDate birthDate, String notes, Customer customer) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
        this.customer = customer;
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
     * Gets type.
     *
     * @return the type
     */
    public PetType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(PetType type) {
        this.type = type;
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
     * Gets birth date.
     *
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets birth date.
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

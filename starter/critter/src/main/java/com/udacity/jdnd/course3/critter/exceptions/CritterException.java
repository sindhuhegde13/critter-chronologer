package com.udacity.jdnd.course3.critter.exceptions;

/**
 * The type Critter exception.
 */
public class CritterException extends RuntimeException{
    /**
     * Instantiates a new Critter exception.
     *
     * @param object the object
     * @param id     the id
     */
    public CritterException(String object, Long id) {
        super(object+" id"+id+" does not exist");
    }
}

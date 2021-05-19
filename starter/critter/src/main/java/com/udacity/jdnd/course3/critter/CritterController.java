package com.udacity.jdnd.course3.critter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    /**
     * Test string.
     *
     * @return the string
     */
    @GetMapping("/test")
    public String test(){
        return "Critter Starter installed successfully";
    }
}

package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetsService petsService;

    /**
     * Save pet pet dto.
     *
     * @param petDTO the pet dto
     * @return the pet dto
     */
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Long ownerId = petDTO.getOwnerId();
        Pet pet = convertDTOToEntity(petDTO);
        petsService.savePet(pet, ownerId);
        return convertEntityToDTO(pet);
    }

    /**
     * Gets pet.
     *
     * @param petId the pet id
     * @return the pet
     */
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petsService.findById(petId);
        return convertEntityToDTO(pet);
    }

    /**
     * Get pets list.
     *
     * @return the list
     */
    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petsService.findAllPets();
        return convertToList(pets);
    }

    /**
     * Gets pets by owner.
     *
     * @param ownerId the owner id
     * @return the pets by owner
     */
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petsService.findAllCustomerPets(ownerId);
        return convertToList(petList);
    }

    private Pet convertDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet);
        return pet;
    }

    private PetDTO convertEntityToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        if(pet.getCustomer()!=null) {
            petDTO.setOwnerId(pet.getCustomer().getId());
        }
        return petDTO;
    }

    private List<PetDTO> convertToList(List<Pet> petsList) {
        return petsList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

}

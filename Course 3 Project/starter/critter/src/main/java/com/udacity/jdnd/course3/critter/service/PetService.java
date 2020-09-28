package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public Pet findPet(Long id){
        return petRepository.findById(id).get();
    }

    public List<Pet> findPetsByOwner(Long ownerId){
        return petRepository.findByCustomerId(ownerId);
    }

}

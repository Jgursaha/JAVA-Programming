package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public Pet findPet(Long id){
        return petRepository.getOne(id);
    }

    public List<Pet> findPetsByOwner(Long ownerId){
        return petRepository.getPetsByCustomer_Id(ownerId);
    }

    public List<Pet> getAllPets(){ return petRepository.findAll();}

}

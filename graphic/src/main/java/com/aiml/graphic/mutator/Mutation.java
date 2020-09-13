package com.aiml.graphic.mutator;

import com.aiml.graphic.entity.Dog;
import com.aiml.graphic.exception.BreedNotFoundException;
import com.aiml.graphic.exception.DogNotFoundException;
import com.aiml.graphic.repository.DogRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;

        Iterable<Dog> allDogs = dogRepository.findAll();

        for(Dog d:allDogs){
            if(d.getBreed().equals(breed)){
                dogRepository.delete(d);
                deleted = true;
            }
        }

        if(!deleted){
            throw new BreedNotFoundException("breed Not Found", breed);
        }

        return deleted;
    }

    public Dog updateDogName(String name, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(name);
            dogRepository.save(dog);
            return dog;
        }
        else{
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}

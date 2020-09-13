package com.aiml.graphic.resolver;

import com.aiml.graphic.entity.Dog;
import com.aiml.graphic.exception.DogNotFoundException;
import com.aiml.graphic.repository.DogRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private DogRepository dogRepository;

    public Query(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog findDogByID(Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            return dog;
        }
        else{
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }



}

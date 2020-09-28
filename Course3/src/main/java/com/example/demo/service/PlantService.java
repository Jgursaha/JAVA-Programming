package com.example.demo.service;

import com.example.demo.data.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    public Plant getPlantByName(String name){
        return new Plant();
    }

}

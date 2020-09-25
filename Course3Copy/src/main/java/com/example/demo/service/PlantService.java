package com.example.demo.service;

import com.example.demo.data.Plant;
import com.example.demo.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;
    public Plant getPlantByName(String name){
        return new Plant();
    }

    public Long save(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public Boolean delivered(Long id){
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id, Boolean.TRUE);
    }

    public List<Plant> plantsCheaperThan(BigDecimal price){
        return plantRepository.findByPriceLessThan(price);
    }


}

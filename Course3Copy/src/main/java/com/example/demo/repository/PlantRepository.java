package com.example.demo.repository;

import com.example.demo.data.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    public boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    @Query("select p.delivery.completed from Plant p where p.id = :plantId")
    Boolean deliveryCompleted(Long plantId);

    public List<Plant> findByPriceLessThan(BigDecimal price);
}

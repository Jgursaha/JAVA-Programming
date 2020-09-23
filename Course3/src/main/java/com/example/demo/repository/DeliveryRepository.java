package com.example.demo.repository;

import com.example.demo.data.Delivery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery d){
        entityManager.persist(d);
    }

    public Delivery find(Long id){
        Delivery d = entityManager.find(Delivery.class, id);
        return d;
    }

    public Delivery merge(Delivery d){
        Delivery managedDelivery = entityManager.merge(d);
        return managedDelivery;
    }

    public void delete(Long id){
        Delivery d = entityManager.find(Delivery.class, id);
        entityManager.remove(d);
    }
}

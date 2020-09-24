package com.example.demo.repository;

import com.example.demo.data.Delivery;
import com.example.demo.data.RecipientAndPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

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

    public List<Delivery> findDeliveriesByName(String name){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName",Delivery.class);
        query.setParameter("recipientName", name);
        return query.getResultList();
    }

    //public RecipientAndPrice findRecipientAndPrice(Long id){

    //}
}

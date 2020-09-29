package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c, Pet p where p.customer_id = c.id AND p.id = :petId")
    Customer findByPetId(@Param("petId") Long petId);
}

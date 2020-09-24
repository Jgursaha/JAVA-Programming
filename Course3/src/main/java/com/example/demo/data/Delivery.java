package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
//import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@NamedQueries({
//        @NamedQuery(
//                name = "Delivery.findByName",
//                query = "select d from Delivery d where d.recipientName = :recipientName")
//})


public class Delivery {

    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @Nationalized
    private String recipientName;

    @Column(name="address_full", length = 500)
    private String address;
    private LocalDateTime deliveryTime;

    @Type(type="yes_no")
    private Boolean completed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}

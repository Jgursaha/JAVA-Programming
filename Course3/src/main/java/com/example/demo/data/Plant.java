package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public class Plant {
    @GeneratedValue
    @Id
    @Column(name="id")
    private Long id;

    @JsonView(Views.class)
    @Nationalized
    private String name;

    @JsonView(Views.class)
    @Column(precision=12, scale=4)
    BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

package com.udacity.jdnd.course3.critter.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

//@Entity
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
}

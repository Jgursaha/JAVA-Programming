package com.udacity.jdnd.course3.critter.user;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;
import java.util.Set;
import java.time.DayOfWeek;

@Entity
public class Employee extends User{

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}

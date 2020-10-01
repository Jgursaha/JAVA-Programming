package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);
        Schedule savedSchedule = scheduleService.save(schedule);
        return convertScheduleToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getSchedulesByPet(petService.findPet(petId));
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByEmployee(employeeService.findEmployee(employeeId));
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

        List<Pet> pets = customerService.findById(customerId).getPets();
        List<Schedule> schedules = new ArrayList<>();

        for(Pet pet: pets){
            schedules.addAll(scheduleService.getSchedulesByPet(pet));
        }

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;



    }

    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(scheduleDTO, schedule);

        List<Long> petIds = scheduleDTO.getPetIds();
        if(petIds != null){
            List<Pet> pets = new ArrayList<Pet>();
            for (Long petId: petIds) {
                pets.add(petService.findPet(petId));
            }
            schedule.setPets(pets);
        }

        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        if(employeeIds != null){
            List<Employee> employees = new ArrayList<Employee>();
            for (Long employeeId: employeeIds) {
                employees.add(employeeService.findEmployee(employeeId));
            }
            schedule.setEmployees(employees);
        }

        return schedule;
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Pet> pets = schedule.getPets();
        if(pets != null){
            List<Long> petIds = new ArrayList<Long>();
            for (Pet pet: pets) {
                petIds.add(pet.getId());
            }
            scheduleDTO.setPetIds(petIds);
        }

        List<Employee> employees = schedule.getEmployees();
        if(employees != null){
            List<Long> employeeIds = new ArrayList<Long>();
            for (Employee employee: employees) {
                employeeIds.add(employee.getId());
            }
            scheduleDTO.setEmployeeIds(employeeIds);
        }

        return scheduleDTO;
    }
}

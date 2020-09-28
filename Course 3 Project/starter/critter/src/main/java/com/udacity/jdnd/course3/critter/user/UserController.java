package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        List < Long > petIds = customerDTO.getPetIds();
        List < Pet > pets = new ArrayList < > ();

        if (petIds != null) {
            for (Long petId: petIds) {
                pets.add(petService.findPet(petId));
            }
        }

        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        customer.setPets(pets);
        Customer savedCustomer = customerService.save(customer);
        return convertCustomerToCustomerDTO(savedCustomer);
    }


    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<CustomerDTO>();

        for (Customer customer: customers) {
            System.out.println("Converting customer with id to a DTO: "+customer.getId());
            System.out.println("This customer has number of pets = "+customer.getPets().size());
            customerDTOS.add(convertCustomerToCustomerDTO(customer));
        }

        System.out.println("Size of CUSTOMERDTOS in getAllCustoemrs in UserContrOLLER!!: "+ customerDTOS.size());
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        employee = employeeService.save(employee);
        return convertEmployeeToEmployeeDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return  convertEmployeeToEmployeeDTO(employeeService.findEmployee(employeeId));

    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> pets = customer.getPets();

        if(pets != null) {
            List<Long> petIds = new ArrayList<Long>();

            for (Pet pet : pets) {
                petIds.add(pet.getId());
            }

            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        List<Long> petIds = customerDTO.getPetIds();

        if(petIds != null){
            List<Pet> pets = new ArrayList<Pet>();

            for (Long petId: petIds) {
                pets.add(petService.findPet(petId));
            }

            customer.setPets(pets);
        }

        return customer;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

}

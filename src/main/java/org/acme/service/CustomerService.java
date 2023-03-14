package org.acme.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;

@ApplicationScoped
public class CustomerService {

  @Inject
  private CustomerRepository repository;

  public List<CustomerDTO> findAll(){
    List<CustomerDTO> customers = new ArrayList<>();

    repository.findAll().stream().forEach(item -> {
      customers.add(mapCustomerEntityToDTO(item));
    });
    return customers;
  }

  public CustomerDTO findCustomerById(Long id){
    return mapCustomerEntityToDTO(repository.findById(id));
  }

  public void create(CustomerDTO customerDTO){
    repository.persist(mapCustomerDTOToEntity(customerDTO));
  }

  public void update (Long id, CustomerDTO customerDTO){
    CustomerEntity customerEntity = repository.findById(id);

    customerEntity.setAddress(customerDTO.getAddress());
    customerEntity.setAge(customerDTO.getAge());
    customerEntity.setEmail(customerDTO.getEmail());
    customerEntity.setName(customerDTO.getName());
    customerEntity.setPhone(customerDTO.getPhone());

    repository.persist(customerEntity);
  }

  public void delete (Long id){
    repository.deleteById(id);
  }

  private CustomerDTO mapCustomerEntityToDTO(CustomerEntity customer){

    CustomerDTO customerDTO = new CustomerDTO();

    customerDTO.setAddress(customer.getAddress());
    customerDTO.setAge(customer.getAge());
    customerDTO.setEmail(customer.getEmail());
    customerDTO.setName(customer.getName());
    customerDTO.setPhone(customer.getPhone());

    return customerDTO;
  }

  private CustomerEntity mapCustomerDTOToEntity(CustomerDTO customerDTO){

    CustomerEntity customerEntity = new CustomerEntity();

    customerEntity.setAddress(customerDTO.getAddress());
    customerEntity.setAge(customerDTO.getAge());
    customerEntity.setEmail(customerDTO.getEmail());
    customerEntity.setName(customerDTO.getName());
    customerEntity.setPhone(customerDTO.getPhone());

    return customerEntity;
  }
}

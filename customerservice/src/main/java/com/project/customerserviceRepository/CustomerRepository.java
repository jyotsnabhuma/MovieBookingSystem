package com.project.customerserviceRepository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.customerserviceEntity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


	Optional<Customer> findByPhone(Long phone);

}

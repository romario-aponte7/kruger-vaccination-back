package com.kruger.krugerchallenge.repository;

import com.kruger.krugerchallenge.entity.EmployeeVaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeVaccineRepository extends CrudRepository<EmployeeVaccine, UUID> {

}

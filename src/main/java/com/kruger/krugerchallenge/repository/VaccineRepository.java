package com.kruger.krugerchallenge.repository;

import com.kruger.krugerchallenge.entity.Vaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, UUID> {
}

package com.kruger.krugerchallenge.service.impl;


import com.kruger.krugerchallenge.entity.EmployeeVaccine;
import com.kruger.krugerchallenge.repository.EmployeeVaccineRepository;
import com.kruger.krugerchallenge.service.EmployeeVaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmployeeVaccineImpl implements EmployeeVaccineService {
    @Autowired
    private EmployeeVaccineRepository employeeVaccineRepository;

    @Override
    public EmployeeVaccine saveEmployeeVaccine(EmployeeVaccine employeeVaccine) {
        return employeeVaccineRepository.save(employeeVaccine);
    }
}

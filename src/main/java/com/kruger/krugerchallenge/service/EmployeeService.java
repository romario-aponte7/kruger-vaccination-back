package com.kruger.krugerchallenge.service;


import com.kruger.krugerchallenge.entity.Employee;
import com.kruger.krugerchallenge.entity.EmployeeVaccine;
import com.kruger.krugerchallenge.presentation.Paginator;
import com.kruger.krugerchallenge.presentation.presenter.EmployeePresenter;
import com.kruger.krugerchallenge.presentation.presenter.EmployeeVaccinePresenter;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.UUID;

public interface EmployeeService {

    Paginator getEmployeesPaginated(String searchValue, Date initDate, Date endDate, String[] status, Pageable pageable);

    EmployeePresenter getEmployeeById(UUID employeeId);

    EmployeePresenter toEmployeePresenter(Employee employee);

    EmployeeVaccinePresenter toEmployeeVaccinePresenter(EmployeeVaccine employeeVaccine);

    EmployeeVaccine toEmployeeVaccine(EmployeeVaccinePresenter employeeVaccinePresenter);

    EmployeePresenter saveUpdateEmployee(EmployeePresenter employeePresenter);
}

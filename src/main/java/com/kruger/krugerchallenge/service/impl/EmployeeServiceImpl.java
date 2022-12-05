package com.kruger.krugerchallenge.service.impl;

import com.kruger.krugerchallenge.entity.Employee;
import com.kruger.krugerchallenge.entity.EmployeeVaccine;
import com.kruger.krugerchallenge.presentation.Paginator;
import com.kruger.krugerchallenge.presentation.presenter.EmployeePresenter;
import com.kruger.krugerchallenge.presentation.presenter.EmployeeVaccinePresenter;
import com.kruger.krugerchallenge.repository.EmployeeRepository;
import com.kruger.krugerchallenge.service.EmployeeService;
import com.kruger.krugerchallenge.service.EmployeeVaccineService;
import com.kruger.krugerchallenge.service.VaccineService;
import com.kruger.krugerchallenge.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private EmployeeVaccineService employeeVaccineService;


    @Override
    public Paginator getEmployeesPaginated(String searchValue, Date initDate, Date endDate, String[] status, Pageable pageable) {
        if (initDate != null) {
            initDate = DateUtils.instance().asDate(
                    DateUtils.instance().asLocalDateTime(initDate)
                            .withHour(0).withSecond(0).withMinute(0).withNano(0)
            );
        }
        if (endDate != null) {
            endDate = DateUtils.instance().asDate(
                    DateUtils.instance().asLocalDateTime(endDate).withHour(23).withMinute(59).withSecond(59).withNano(0)
            );
        }
        if ((initDate != null && endDate != null) && initDate.compareTo(endDate) > 0) {
            throw new RuntimeException("Error en el rango de fechas");
        }
        Page<Employee> employees = employeeRepository.findByFilters(searchValue, initDate, endDate, status, pageable);
        List<EmployeePresenter> employeePresenters = new ArrayList<>();
        employees.getContent().forEach(employee -> {
            employeePresenters.add(toEmployeePresenter(employee));
        });
        Set<EmployeePresenter> treeSet = new TreeSet<>(new Comparator<EmployeePresenter>() {
            @Override
            public int compare(EmployeePresenter employeePresenter, EmployeePresenter t1) {
                return employeePresenter.getEmployeeId().compareTo(t1.getEmployeeId()) * -1;
            }
        });
        treeSet.addAll(employeePresenters);
        Paginator paginator = new Paginator(employees.getTotalPages(), employees.getTotalElements(), treeSet);

        return paginator;
    }

    @Override
    public EmployeePresenter toEmployeePresenter(Employee employee) {
        List<EmployeeVaccinePresenter> employeeVaccinePresenter = new ArrayList<>();
        employee.getEmployeeVaccines().forEach(vaccine -> {
            employeeVaccinePresenter.add(toEmployeeVaccinePresenter(vaccine));
        });
        return EmployeePresenter.builder()
                .employeeId(employee.getEmployeeId())
                .dni(employee.getDni())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .mail(employee.getMail())
                .dateOfBirth(employee.getDateOfBirth().toString())
                .address(employee.getAddress())
                .phone(employee.getPhone())
                .status(employee.getStatus())
                .employeeVaccinePresenters(employeeVaccinePresenter)
                .build();
    }

    @Override
    public EmployeeVaccinePresenter toEmployeeVaccinePresenter(EmployeeVaccine employeeVaccine) {
        return EmployeeVaccinePresenter.builder()
                .employeeVaccineId(employeeVaccine.getEmployeeVaccineId())
                .date(employeeVaccine.getDate().toString())
                .dose(employeeVaccine.getDose())
                .vaccinePresenter(vaccineService.toVaccinePresenter(employeeVaccine.getVaccine()))
                .build();
    }

    @Override
    public EmployeeVaccine toEmployeeVaccine(EmployeeVaccinePresenter employeeVaccinePresenter) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = formatter1.parse(employeeVaccinePresenter.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return EmployeeVaccine.builder()
                .employeeVaccineId(employeeVaccinePresenter.getEmployeeVaccineId() != null
                        ? employeeVaccinePresenter.getEmployeeVaccineId() : UUID.randomUUID())
                .date(date1)
                .dose(employeeVaccinePresenter.getDose())
                .vaccine(vaccineService.toVaccine(employeeVaccinePresenter.getVaccinePresenter()))
                .build();
    }

    @Override
    public EmployeePresenter saveUpdateEmployee(EmployeePresenter employeePresenter) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = formatter1.parse(employeePresenter.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<EmployeeVaccine> employeeVaccines = new HashSet<>();
        employeePresenter.getEmployeeVaccinePresenters().forEach(employeeVaccinePresenter -> {
            employeeVaccines.add(toEmployeeVaccine(employeeVaccinePresenter));
        });
        Employee employee = new Employee();
        if (employeePresenter.getEmployeeId() != null) {
            employee = employeeRepository.findById(employeePresenter.getEmployeeId()).get();
        }
        Employee finalEmployee = employee;
        employeeVaccines.forEach(employeeVaccine -> {
            employeeVaccine.setEmployee(finalEmployee);
            employeeVaccineService.saveEmployeeVaccine(employeeVaccine);
        });
        employee.setDni(employeePresenter.getDni());
        employee.setFirstName(employeePresenter.getFirstName());
        employee.setLastName(employeePresenter.getLastName());
        employee.setMail(employeePresenter.getMail());
        employee.setDateOfBirth(date1);
        employee.setAddress(employeePresenter.getAddress());
        employee.setPhone(employeePresenter.getPhone());
        employee.setStatus(employeePresenter.getStatus());

        Employee employeeSaved = employeeRepository.save(employee);
        return toEmployeePresenter(employeeSaved);
    }

    @Override
    public EmployeePresenter getEmployeeById(UUID employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return toEmployeePresenter(employee.get());
        }
        return null;
    }
}

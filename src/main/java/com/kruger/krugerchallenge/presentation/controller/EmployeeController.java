package com.kruger.krugerchallenge.presentation.controller;

import com.kruger.krugerchallenge.presentation.Paginator;
import com.kruger.krugerchallenge.presentation.presenter.EmployeePresenter;
import com.kruger.krugerchallenge.service.EmployeeService;
import com.sun.istack.NotNull;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Generated
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmployeesPaginated")
    public Paginator getEmployeesPaginated(@RequestParam(required = false) String searchValue,
                                           @RequestParam("page") Integer page,
                                           @RequestParam("size") Integer size,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date initDate,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
                                           @RequestParam(name = "status", required = false) String[] status) {
        if (!searchValue.equals("null") && !searchValue.isEmpty()) {
            searchValue = searchValue.replace(' ', '%');
        }

        Pageable pageable = PageRequest.of(page, size);

        return employeeService.getEmployeesPaginated(searchValue, initDate, endDate, status, pageable);
    }

    @GetMapping("/getEmployeeId")
    public EmployeePresenter getEmployeeById(@RequestParam("employeeId") UUID employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("saveUpdateEmployee")
    public EmployeePresenter saveUpdateEmployee(@RequestBody @NotNull EmployeePresenter employeePresenter) {
        return employeeService.saveUpdateEmployee(employeePresenter);
    }
}

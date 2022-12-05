package com.kruger.krugerchallenge.presentation.controller;

import com.kruger.krugerchallenge.presentation.presenter.VaccinePresenter;
import com.kruger.krugerchallenge.service.VaccineService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Generated
@RestController
public class VaccineController {
    @Autowired
    VaccineService vaccineService;

    @GetMapping("/getVaccines")
    public List<VaccinePresenter> getVaccines() {
        return vaccineService.getVaccines();
    }
}

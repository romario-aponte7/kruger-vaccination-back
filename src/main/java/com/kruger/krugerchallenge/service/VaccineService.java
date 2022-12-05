package com.kruger.krugerchallenge.service;


import com.kruger.krugerchallenge.entity.Vaccine;
import com.kruger.krugerchallenge.presentation.presenter.VaccinePresenter;

import java.util.List;

public interface VaccineService {

    VaccinePresenter toVaccinePresenter(Vaccine vaccine);

    Vaccine toVaccine(VaccinePresenter vaccinePresenter);


    List<VaccinePresenter> getVaccines();

}

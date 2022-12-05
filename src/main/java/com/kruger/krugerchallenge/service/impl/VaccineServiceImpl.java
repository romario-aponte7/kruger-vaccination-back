package com.kruger.krugerchallenge.service.impl;


import com.kruger.krugerchallenge.entity.Vaccine;
import com.kruger.krugerchallenge.presentation.presenter.VaccinePresenter;
import com.kruger.krugerchallenge.repository.VaccineRepository;
import com.kruger.krugerchallenge.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public List<VaccinePresenter> getVaccines() {
        Iterable<Vaccine> vaccines  = vaccineRepository.findAll();
        List<VaccinePresenter> vaccinePresenters=new ArrayList<>();
        vaccines.forEach(vaccine -> {
            vaccinePresenters.add(toVaccinePresenter(vaccine));
        });
        return vaccinePresenters;
    }
    @Override
    public VaccinePresenter toVaccinePresenter(Vaccine vaccine) {
        return VaccinePresenter.builder()
                .vaccineId(vaccine.getVaccineId())
                .name(vaccine.getName())
                .description(vaccine.getDescription())
                .build();
    }

    @Override
    public Vaccine toVaccine(VaccinePresenter vaccinePresenter) {
        return Vaccine.builder()
                .vaccineId(vaccinePresenter.getVaccineId())
                .name(vaccinePresenter.getName())
                .description(vaccinePresenter.getDescription())
                .build();
    }
}

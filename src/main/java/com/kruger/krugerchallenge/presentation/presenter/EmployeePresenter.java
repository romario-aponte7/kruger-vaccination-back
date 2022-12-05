package com.kruger.krugerchallenge.presentation.presenter;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeePresenter {
    private UUID employeeId;
    private String dni;
    private String firstName;
    private String lastName;
    private String mail;
    private String dateOfBirth;
    private String address;
    private String phone;
    private String status;
    @Builder.Default
    private List<EmployeeVaccinePresenter> employeeVaccinePresenters = new ArrayList<>();
}

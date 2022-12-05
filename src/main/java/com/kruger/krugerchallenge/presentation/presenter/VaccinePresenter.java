package com.kruger.krugerchallenge.presentation.presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccinePresenter {
    private UUID vaccineId;
    private String name;
    private String description;
}

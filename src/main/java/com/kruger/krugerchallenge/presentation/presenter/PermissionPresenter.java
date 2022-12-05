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
public class PermissionPresenter {
    private UUID permissionId;
    private String name;
    private String domainAction;
}

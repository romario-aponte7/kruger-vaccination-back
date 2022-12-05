package com.kruger.krugerchallenge.presentation.presenter;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPresenter implements Comparable<UserPresenter> {
    private UUID userId;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private String dni;
    @NotNull
    @Builder.Default
    private List<RolePresenter> rolePresenters = new ArrayList<>();

    public int compareTo(UserPresenter userPresenter) {
        return this.userName.compareTo(userPresenter.userName);
    }
}

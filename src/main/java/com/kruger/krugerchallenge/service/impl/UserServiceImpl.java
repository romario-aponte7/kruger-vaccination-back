package com.kruger.krugerchallenge.service.impl;


import com.kruger.krugerchallenge.entity.Permission;
import com.kruger.krugerchallenge.entity.Role;
import com.kruger.krugerchallenge.entity.User;
import com.kruger.krugerchallenge.presentation.presenter.PermissionPresenter;
import com.kruger.krugerchallenge.presentation.presenter.RolePresenter;
import com.kruger.krugerchallenge.presentation.presenter.UserPresenter;
import com.kruger.krugerchallenge.repository.UserRepository;
import com.kruger.krugerchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserPresenter loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return userToPresenter(user.get());

        }
        return null;
    }

    private UserPresenter userToPresenter(User user) {
        List<RolePresenter> rolePresenters = new ArrayList<>();
        user.getRoles().forEach(role -> {
            rolePresenters.add(roleToPresenter(role));
        });
        return UserPresenter.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .fullName(user.getFullName())
                .dni(user.getDni())
                .rolePresenters(rolePresenters)
                .build();

    }

    private RolePresenter roleToPresenter(Role role) {
        List<PermissionPresenter> permissionPresenters = new ArrayList<>();
        role.getPermissions().forEach(permission -> {
            permissionPresenters.add(permissionToPresenter(permission));
        });
        return RolePresenter.builder()
                .roleId(role.getRoleId())
                .name(role.getName())
                .permissionPresenters(permissionPresenters)
                .build();
    }

    private PermissionPresenter permissionToPresenter(Permission permission) {
        return PermissionPresenter.builder()
                .permissionId(permission.getPermissionId())
                .domainAction(permission.getDomainAction())
                .name(permission.getName())
                .build();
    }


}
package com.kruger.krugerchallenge.presentation.controller;


import com.kruger.krugerchallenge.presentation.presenter.UserPresenter;
import com.kruger.krugerchallenge.service.UserService;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Generated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/loginUserPresenter")
    public UserPresenter loginUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        return userService.loginUser(username, password);

    }
}

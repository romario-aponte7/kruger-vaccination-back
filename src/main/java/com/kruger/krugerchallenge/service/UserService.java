package com.kruger.krugerchallenge.service;


import com.kruger.krugerchallenge.presentation.presenter.UserPresenter;

public interface UserService {

    UserPresenter loginUser(String userName, String password);

}

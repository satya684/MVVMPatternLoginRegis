package com.satya.mvvmsatyasetup.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.satya.mvvmsatyasetup.model.LoginModel;
import com.satya.mvvmsatyasetup.network.Service;
import com.satya.mvvmsatyasetup.pojo.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResponse> loginUser;

    public LoginViewModel() {
        loginUser = new MutableLiveData<>();
    }

    public MutableLiveData<LoginResponse> getLoginUserObserver() {
        return loginUser;
    }

    public void loginUser(LoginModel loginModel) {
        Service.getService().doLogin(loginModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginUser.postValue(response.body());
                } else {
                    loginUser.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginUser.postValue(null);
            }
        });
    }

}

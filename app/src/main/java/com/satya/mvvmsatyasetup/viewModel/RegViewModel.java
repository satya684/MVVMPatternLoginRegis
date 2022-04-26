package com.satya.mvvmsatyasetup.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.satya.mvvmsatyasetup.model.RegModel;
import com.satya.mvvmsatyasetup.network.Service;
import com.satya.mvvmsatyasetup.pojo.RegResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegViewModel extends ViewModel {

    private MutableLiveData<RegResponse> createNewUser;

    public RegViewModel() {
        createNewUser = new MutableLiveData<>();
    }

    public MutableLiveData<RegResponse> getCreateUserObserver() {
        return createNewUser;
    }

    public void createNewUser(RegModel regModel) {
//        RegModel request = new RegModel("name", "email", "password");
//        Service.getService().doRegister(regModel);
        //Service.getService().doRegister()
        Service.getService().doRegister(regModel).enqueue(new Callback<RegResponse>() {
            @Override
            public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                if (response.isSuccessful()) {
                    createNewUser.postValue(response.body());
                } else {
                    createNewUser.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<RegResponse> call, Throwable t) {
                createNewUser.postValue(null);
            }
        });
    }

    /*public void makeApiCall(String name, String email, String password) {

        RegModel request = new RegModel(name, email, password);
        Service.getService().doRegister(request);


           }*/
}

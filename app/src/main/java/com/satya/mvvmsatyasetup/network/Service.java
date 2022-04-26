package com.satya.mvvmsatyasetup.network;

import static com.satya.mvvmsatyasetup.network.API.BASE_URL;
import static com.satya.mvvmsatyasetup.network.API.LOGIN;
import static com.satya.mvvmsatyasetup.network.API.REGISTRATION;

import com.satya.mvvmsatyasetup.model.LoginModel;
import com.satya.mvvmsatyasetup.model.RegModel;
import com.satya.mvvmsatyasetup.pojo.LoginResponse;
import com.satya.mvvmsatyasetup.pojo.RegResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    Service INSTANCE = null;

    /**
     * TODO --- For Api Calling */

    static Service getService() {

        if (INSTANCE == null) {

            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(5, TimeUnit.MINUTES)
                            .readTimeout(5, TimeUnit.MINUTES)
                            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Service.class);
        }

        return INSTANCE;
    }

//    @FormUrlEncoded
    @POST(LOGIN)
    Call<LoginResponse> doLogin(@Body LoginModel loginModel);

//    @FormUrlEncoded
    @POST(REGISTRATION)
    Call<RegResponse> doRegister(@Body RegModel regModel);

}

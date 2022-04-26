package com.satya.mvvmsatyasetup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.satya.mvvmsatyasetup.R;
import com.satya.mvvmsatyasetup.model.LoginModel;
import com.satya.mvvmsatyasetup.pojo.LoginResponse;
import com.satya.mvvmsatyasetup.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private com.satya.mvvmsatyasetup.databinding.ActivityLoginBinding loginBinding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        dontHaveAccount();
        initViewModel();
        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }

            private void LoginUser() {
                String userEmail = loginBinding.inputEmail.getText().toString().trim();
                String userPass = loginBinding.inputPassword.getText().toString().trim();
                LoginModel loginModel = new LoginModel(userEmail, userPass);
                loginViewModel.loginUser(loginModel);
            }
        });
    }

    private void initViewModel() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getLoginUserObserver().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse == null){
                    Toast.makeText(LoginActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dontHaveAccount() {
        loginBinding.dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
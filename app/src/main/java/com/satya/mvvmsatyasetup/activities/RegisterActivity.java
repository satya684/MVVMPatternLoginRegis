package com.satya.mvvmsatyasetup.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.satya.mvvmsatyasetup.MainActivity;
import com.satya.mvvmsatyasetup.R;
import com.satya.mvvmsatyasetup.databinding.ActivityRegisterBinding;
import com.satya.mvvmsatyasetup.model.RegModel;
import com.satya.mvvmsatyasetup.pojo.RegResponse;
import com.satya.mvvmsatyasetup.viewModel.RegViewModel;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding registerBinding;
    RegViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        alreadyHaveAccount();
        initViewModel();

        registerBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewUser();
            }
        });
    }

    private void createNewUser() {
        String userName = registerBinding.name.getText().toString().trim();
        String userpass = registerBinding.pass.getText().toString().trim();
        String userEmail = registerBinding.email.getText().toString().trim();
        RegModel regModel = new RegModel(userName,userEmail, userpass);
        viewModel.createNewUser(regModel);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(RegViewModel.class);
        viewModel.getCreateUserObserver().observe(this, new Observer<RegResponse>() {
            @Override
            public void onChanged(RegResponse regResponse) {
                if (regResponse == null) {
                    Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
            }
        });
    }

    private void alreadyHaveAccount() {
        registerBinding.alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
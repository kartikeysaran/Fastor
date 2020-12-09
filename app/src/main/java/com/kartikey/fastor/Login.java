package com.kartikey.fastor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {
    EditText edtPhone, edtOtp;
    CoordinatorLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadUI();

    }
    private void loadUI(){
        edtPhone = findViewById(R.id.activity_login_edt_mobile);
        edtOtp = findViewById(R.id.activity_login_edt_otp);
        findViewById(R.id.activity_login_btn_send_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.activity_login_layout_phone).setVisibility(View.GONE);
                findViewById(R.id.activity_login_layout_otp).setVisibility(View.VISIBLE);
                Snackbar snackbar = Snackbar.make(layout, "OTP Send", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        findViewById(R.id.activity_login_btn_verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(edtOtp.getText().equals(123456)){
                 edtOtp.setError(null);
                 Snackbar snackbar = Snackbar.make(layout, "Successfully Verified", Snackbar.LENGTH_LONG);
                 snackbar.show();
                 startActivity(new Intent(Login.this,Dashboard.class));
             }
             else{
                 Snackbar snackbar = Snackbar.make(layout, "Something went wrong !", Snackbar.LENGTH_LONG);
                 snackbar.show();
                 edtOtp.setError("Invalid Credentials");
             }
            }
        });
    }
}
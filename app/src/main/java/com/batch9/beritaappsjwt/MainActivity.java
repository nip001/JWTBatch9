package com.batch9.beritaappsjwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.batch9.beritaappsjwt.entity.User;
import com.batch9.beritaappsjwt.service.ApiClient;
import com.batch9.beritaappsjwt.service.AuthInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editUsername,editPassword;
    Button btnLogin;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPassword = findViewById(R.id.editPassword);
        editUsername = findViewById(R.id.editUsername);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setPassword(editPassword.getText().toString());
                user.setUsername(editUsername.getText().toString());
                AuthInterface authInterface = ApiClient.getRetrofit().create(AuthInterface.class);
                Call<String> call = authInterface.loginUser(user);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent i = new Intent(MainActivity.this,HomeActivity.class);
                        i.putExtra("token",response.body());
                        Toast.makeText(MainActivity.this,"Login Berhasil",Toast.LENGTH_LONG).show();
                        System.out.println(response.body());
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Tidak Berhasil",Toast.LENGTH_LONG).show();
                        System.out.println(t);
                    }
                });
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
package com.batch9.beritaappsjwt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.batch9.beritaappsjwt.entity.User;
import com.batch9.beritaappsjwt.service.ApiClient;
import com.batch9.beritaappsjwt.service.AuthInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText editRegisterUsername,editRegisterPassword;
    Spinner spnRole;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editRegisterPassword= findViewById(R.id.editRegisterPassword);
        editRegisterUsername= findViewById(R.id.editRegisterUsername);
        spnRole= findViewById(R.id.spnRole);
        btnRegister= findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(
                        editRegisterUsername.getText().toString(),
                        editRegisterPassword.getText().toString(),
                        spnRole.getSelectedItem().toString()
                );
                AuthInterface authInterface = ApiClient.getRetrofit().create(AuthInterface.class);
                Call<String> call = authInterface.daftarUser(user);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Toast.makeText(RegisterActivity.this,response.body(),Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Toast.makeText(RegisterActivity.this,"Tidak Berhasil",Toast.LENGTH_LONG).show();
                        System.out.println(t);
                    }
                });
            }
        });
    }
}
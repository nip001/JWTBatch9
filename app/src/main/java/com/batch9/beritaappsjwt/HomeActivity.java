package com.batch9.beritaappsjwt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.batch9.beritaappsjwt.adapter.BeritaAdapter;
import com.batch9.beritaappsjwt.entity.Berita;
import com.batch9.beritaappsjwt.entity.User;
import com.batch9.beritaappsjwt.service.ApiClient;
import com.batch9.beritaappsjwt.service.AuthInterface;
import com.batch9.beritaappsjwt.utility.JWTUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rv_berita;
    Button btnTambah,btnSearch;
    EditText editSearch;
    String token;
    AuthInterface authInterface;
    User roleUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv_berita=findViewById(R.id.rv_berita);
        btnSearch=findViewById(R.id.btnSearch);
        btnTambah=findViewById(R.id.btnTambah);
        editSearch=findViewById(R.id.editSearch);
        token = "Bearer "+getIntent().getStringExtra("token");

        authInterface = ApiClient.getRetrofit().create(AuthInterface.class);


        listFunction();
        searchTitle();

        try {
            Gson gson = new Gson();
            roleUser = gson.fromJson(JWTUtil.getBodyDecode(getIntent().getStringExtra("token")), User.class);
            System.out.println(roleUser);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(roleUser.getRole().equalsIgnoreCase("ROLE_ADMIN")){
            btnTambah.setVisibility(View.VISIBLE);

            btnTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(HomeActivity.this,"Berhasil masuk ke menu tambah",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void listFunction(){
        Call<ArrayList<Berita>> call = authInterface.getAllBerita(token);
        call.enqueue(new Callback<ArrayList<Berita>>() {
            @Override
            public void onResponse(Call<ArrayList<Berita>> call, Response<ArrayList<Berita>> response) {
                BeritaAdapter beritaAdapter = new BeritaAdapter(response.body(),HomeActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.VERTICAL,false);
                rv_berita.setLayoutManager(layoutManager);
                rv_berita.setAdapter(beritaAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Berita>> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"Tidak Dapat Mengakses",Toast.LENGTH_LONG).show();
                System.out.println(t);
                finish();
            }
        });
    }

    private void searchTitle(){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ArrayList<Berita>> call = authInterface.getAllBeritaByTitle(
                        token,
                        editSearch.getText().toString()
                );
                call.enqueue(new Callback<ArrayList<Berita>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Berita>> call, Response<ArrayList<Berita>> response) {

                        BeritaAdapter beritaAdapter = new BeritaAdapter(response.body(),HomeActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.VERTICAL,false);
                        rv_berita.setLayoutManager(layoutManager);
                        rv_berita.setAdapter(beritaAdapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Berita>> call, Throwable t) {

                        Toast.makeText(HomeActivity.this,"Tidak Dapat Menemukan Title",Toast.LENGTH_LONG).show();
                        System.out.println(t);
                    }
                });
            }
        });
    }
}
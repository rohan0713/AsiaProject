package com.example.asiaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private com.example.asiaproject.countryViewmodel countryViewmodel;
    private com.example.asiaproject.countryRepository countryRepository;
    private List<countries> countryList;
    private countryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvUsers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        countryRepository=new countryRepository(getApplication());
        countryList=new ArrayList<>();
        countryAdapter=new countryAdapter(this, countryList);

        countryViewmodel=new ViewModelProvider(this).get(countryViewmodel.class);
        getcountryList();
        countryViewmodel.GetAllCountries().observe(this, new Observer<List<countries>>() {
            @Override
            public void onChanged(List<countries> countryList) {
                recyclerView.setAdapter(countryAdapter);
                countryAdapter.getAllCountries(countryList);

                Log.d("main", "onChanged: "+ countryList);
            }
        });
    }

    private void getcountryList(){

        Call<List<countries>> moviesInfoCall = Retrofit.GetServices().getCountriesInfo();
        moviesInfoCall.enqueue(new Callback<List<countries>>() {

            @Override
            public void onResponse(Call<List<countries>> call, Response<List<countries>> response) {

                countryRepository.insert(response.body());
            }

            @Override
            public void onFailure(Call<List<countries>> call, Throwable t) {
                Log.d("Error" , t.getMessage());
                Toast.makeText(MainActivity.this, "Try to connect to Internet!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Delete){
            Toast.makeText(this, "Cleared the database...", Toast.LENGTH_SHORT).show();
            countryViewmodel.delete();
            return true;
        }else if(id == R.id.Refresh){
            Toast.makeText(this,"Fetching the data...", Toast.LENGTH_SHORT).show();
            getcountryList();
        }
        return super.onOptionsItemSelected(item);
    }
}
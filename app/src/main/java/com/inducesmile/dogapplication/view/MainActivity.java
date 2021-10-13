package com.inducesmile.dogapplication.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.inducesmile.dogapplication.adapter.DogAdapter;
import com.inducesmile.dogapplication.databinding.ActivityMainBinding;
import com.inducesmile.dogapplication.models.Dog;
import com.inducesmile.dogapplication.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private static final String TAG = "MainActivity";

    private List<Dog> dataset;
    private DogAdapter adapter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

//        binding.recycleviewListdog.setLayoutManager(new LinearLayoutManager(this));
//
//        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
//        viewModel.getData().observe(this, new Observer<List<Dog>>() {
//            @Override
//            public void onChanged(List<Dog> dogs) {
//                dataset = dogs;
//                adapter = new DogAdapter(dataset);
//                binding.recycleviewListdog.setAdapter(adapter);
//
//            }
//        });
    }
}
package com.inducesmile.dogapplication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.dogapplication.R;
import com.inducesmile.dogapplication.adapter.DogAdapter;
import com.inducesmile.dogapplication.databinding.FragmentListBinding;
import com.inducesmile.dogapplication.models.Dog;
import com.inducesmile.dogapplication.viewmodel.MainActivityViewModel;

import java.util.List;

public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";

    private FragmentListBinding fragmentListBinding;
    private DogAdapter dogAdapter;
    private List<Dog> dogList;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list, container, false);
        fragmentListBinding = FragmentListBinding.inflate(inflater,container,false);
        return fragmentListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentListBinding.recycleviewListdog.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<Dog>>() {
            @Override
            public void onChanged(List<Dog> dogs) {
                dogList = dogs;
                dogAdapter = new DogAdapter(dogList);
                fragmentListBinding.recycleviewListdog.setAdapter(dogAdapter);
                dogAdapter.setOnItemClickListener(new DogAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Dog dog = dogs.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("detailData",dog);
                        Navigation.findNavController(v).navigate(R.id.detailFragment,bundle);
                    }
                });
            }
        });

    }
}
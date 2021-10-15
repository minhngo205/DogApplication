package com.inducesmile.dogapplication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.dogapplication.database.entities.DogEntity;
import com.inducesmile.dogapplication.utils.FetchImage;
import com.inducesmile.dogapplication.databinding.FragmentDetailBinding;
import com.inducesmile.dogapplication.models.Dog;


public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";

    private FragmentDetailBinding detailBinding;
    private Handler loadIMGHandler;
    private DogEntity dog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadIMGHandler = new Handler();
        if (getArguments() != null) {
            dog = getArguments().getParcelable("detailData");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detailBinding = FragmentDetailBinding.inflate(inflater,container,false);
        detailBinding.setDog(dog);
        new FetchImage(dog.getImageURL(),detailBinding.dogImage,loadIMGHandler).start();
        // Inflate the layout for this fragment
        return detailBinding.getRoot();
    }
}
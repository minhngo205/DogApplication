package com.inducesmile.dogapplication.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.inducesmile.dogapplication.R;
import com.inducesmile.dogapplication.adapter.DogAdapter;
import com.inducesmile.dogapplication.database.entities.DogEntity;
import com.inducesmile.dogapplication.databinding.FragmentListBinding;
import com.inducesmile.dogapplication.models.Dog;
import com.inducesmile.dogapplication.viewmodel.MainActivityViewModel;

import java.util.List;

public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";

    private FragmentListBinding fragmentListBinding;
    private DogAdapter dogAdapter;
    private List<DogEntity> dogList;
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
        viewModel.getData().observe(getViewLifecycleOwner(), dogEntities -> {
            dogList = dogEntities;
            dogAdapter = new DogAdapter(dogList);
            fragmentListBinding.recycleviewListdog.setAdapter(dogAdapter);
            ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                private final ColorDrawable background = new ColorDrawable(getResources().getColor(R.color.purple_700));
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    fragmentListBinding.recycleviewListdog.post(() -> dogAdapter.showMenu(viewHolder.getAdapterPosition()));
                }
                @Override
                public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                    View itemView = viewHolder.itemView;

                    if (dX > 0) {
                        background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX), itemView.getBottom());
                    } else if (dX < 0) {
                        background.setBounds(itemView.getRight() + ((int) dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    } else {
                        background.setBounds(0, 0, 0, 0);
                    }

                    background.draw(c);
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
            itemTouchHelper.attachToRecyclerView(fragmentListBinding.recycleviewListdog);

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                fragmentListBinding.recycleviewListdog.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                    @Override
//                    public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                        fragmentListBinding.recycleviewListdog.post(new Runnable() {
//                            public void run() {
//                                dogAdapter.closeMenu();
//                            }
//                        });
//                    }
//                });
//            }

            dogAdapter.setOnItemClickListener((position, v) -> {
                DogEntity dogEntity = dogEntities.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("detailData",dogEntity);
                Navigation.findNavController(v).navigate(R.id.detailFragment,bundle);
            });
        });
        fragmentListBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty()){
                    onActionEnd();
                    return;
                }
                dogList.clear();
                dogList.addAll(viewModel.onSearch(charSequence.toString()));
                dogAdapter.notifyDataSetChanged();
//                Log.d(TAG, "onTextChanged: "+charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onActionEnd() {
        dogList.clear();
        dogList.addAll(viewModel.onRestart());
        dogAdapter.notifyDataSetChanged();
    }
}
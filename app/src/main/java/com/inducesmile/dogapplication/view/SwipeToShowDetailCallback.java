package com.inducesmile.dogapplication.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.inducesmile.dogapplication.adapter.DogAdapter;

public class SwipeToShowDetailCallback extends ItemTouchHelper.SimpleCallback {

    private DogAdapter dogAdapter;
    public SwipeToShowDetailCallback(DogAdapter adapter) {
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        dogAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
//        dogAdapter.showDetail(position);
    }
}

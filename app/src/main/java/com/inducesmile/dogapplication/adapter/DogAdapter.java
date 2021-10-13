package com.inducesmile.dogapplication.adapter;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.inducesmile.dogapplication.R;
import com.inducesmile.dogapplication.models.Dog;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder>{

    private static final String TAG = "DogAdapter";

    private final List<Dog> dogList;
    private final Handler loadIMGHandler = new Handler();

    private static ClickListener clickListener;

    public DogAdapter(List<Dog> data) { this.dogList = data; }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {
        Dog d = dogList.get(position);
//        holder.dImage.setImageDrawable(LoadImageFromWebOperations(d.getImageURL()));
        new FetchImage(d.getImageURL(),holder.dImage,loadIMGHandler).start();
        holder.dTitle.setText(d.getName());
//        holder.dOrigin.setText(d.getOrigin()+" - "+d.getBredFor()+" - "+d.getBreedGroup());
        holder.dTemp.setText(d.getTemperament());
//        holder.dLifespan.setText(d.getLifespan());
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView dImage;
        public TextView dTitle;
//        public TextView dOrigin;
        public TextView dTemp;
//        public MaterialButton dLifespan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            dImage =  itemView.findViewById(R.id.image);
            dTitle = itemView.findViewById(R.id.tv_name);
//            dOrigin = itemView.findViewById(R.id.tv_origin);
            dTemp = itemView.findViewById(R.id.tv_temp);
//            dLifespan = itemView.findViewById(R.id.btn_lifespan);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        DogAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}

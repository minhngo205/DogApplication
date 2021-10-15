package com.inducesmile.dogapplication.adapter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.inducesmile.dogapplication.R;
import com.inducesmile.dogapplication.database.entities.DogEntity;
import com.inducesmile.dogapplication.utils.FetchImage;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = "DogAdapter";

    private final List<DogEntity> dogList;
    private final Handler loadIMGHandler = new Handler();
    private static final int SHOW_MENU = 1;
    private static final int HIDE_MENU = 2;
    private static ClickListener clickListener;

    public DogAdapter(List<DogEntity> data) { this.dogList = data; }

    @Override
    public int getItemViewType(int position) {
        if(dogList.get(position).isShowMenu()){
            return SHOW_MENU;
        }else{
            return HIDE_MENU;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==SHOW_MENU){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item_detail, parent, false);
            return new DetailHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item,parent,false);
            return new ListViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DogEntity d = dogList.get(position);
        if(holder instanceof ListViewHolder){
            new FetchImage(d.getImageURL(),((ListViewHolder)holder).dImage,loadIMGHandler).start();
            ((ListViewHolder)holder).dTitle.setText(d.getName());
            ((ListViewHolder)holder).dTemp.setText(d.getTemperament());
        }
        if(holder instanceof DetailHolder){
            ((DetailHolder) holder).tvName.setText(d.getName());
            ((DetailHolder) holder).tvOrigin.setText(d.getOrigin());
            ((DetailHolder) holder).tvLifespan.setText(d.getLifespan());
            ((DetailHolder) holder).tvTemp.setText(d.getTemperament());
        }
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showMenu(int adapterPosition) {
        for (DogEntity d:dogList) {
            d.setShowMenu(false);
        }
        dogList.get(adapterPosition).setShowMenu(true);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void closeMenu() {
        for (DogEntity d:dogList) {
            d.setShowMenu(false);
        }
        notifyDataSetChanged();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView dImage;
        public TextView dTitle;
        public TextView dTemp;
        public CardView container;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            dImage =  itemView.findViewById(R.id.image);
            dTitle = itemView.findViewById(R.id.tv_name);
            dTemp = itemView.findViewById(R.id.tv_temp);
            container = itemView.findViewById(R.id.card);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public static class DetailHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvOrigin;
        public TextView tvLifespan;
        public TextView tvTemp;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvOrigin = itemView.findViewById(R.id.tvOrigin);
            tvLifespan = itemView.findViewById(R.id.tvLifespan);
            tvTemp = itemView.findViewById(R.id.tvTemp);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        DogAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}

package com.example.shopline.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopline.Model.TitleModel;
import com.example.shopline.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


    private ArrayList<TitleModel> titlemodel;

    public MyAdapter(ArrayList<TitleModel> titlemodel) {
        this.titlemodel = titlemodel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(titlemodel.get(position));
        holder.nameTextView.setText(titlemodel.get(position).getTitel());

    }

    @Override
    public int getItemCount() {
        return titlemodel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }

}

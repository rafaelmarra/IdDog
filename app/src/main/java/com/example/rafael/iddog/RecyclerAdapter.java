package com.example.rafael.iddog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(ListaActivity.listaHusky.get(position)).
                placeholder(R.drawable.iddog).
                error(R.drawable.iddog).
                into(holder.imageDog);

    }

    @Override
    public int getItemCount() {
        return ListaActivity.listaHusky.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageDog;

        public ViewHolder(View itemView) {
            super(itemView);
            imageDog = itemView.findViewById(R.id.imageDog);
        }
    }

}

package com.example.seriesapp.view.adapters;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seriesapp.R;
import com.example.seriesapp.model.entities.Serie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder>{

    ArrayList<Serie> listadoDatos;

    private OnItemClickListener onItemClickListener;

    public AdaptadorPersonalizado(ArrayList<Serie> listadoDatos) {
        this.listadoDatos = listadoDatos;
        this.onItemClickListener = null;
    }

    public void setListadoDatos(ArrayList<Serie> listadoDatos) {
        this.listadoDatos = listadoDatos;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclear_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cagarDatos(listadoDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listadoDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombre, tvAño;
        private ImageView ivPrincipal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tv_item_nombre);
            tvAño = itemView.findViewById(R.id.tv_descripcion);

            ivPrincipal = itemView.findViewById(R.id.iv_item_principal);

        }

        public void cagarDatos(Serie serie) {
            tvNombre.setText(serie.getNombre());
            tvAño.setText(serie.getDescripcion());

            Picasso.get()
                    .load(serie.getURLLink())
                    .resize(400, 400)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(ivPrincipal);

            if(onItemClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(serie, getAdapterPosition());
                    }
                });
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Serie serie, int posicion);
    }
}

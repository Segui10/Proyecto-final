package com.herprogramacion.restaurantericoparico.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comida;
import com.herprogramacion.restaurantericoparico.modelo.Comidas;

import java.util.List;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> implements ItemClickListener {
    private final Context context ;
    private final List<Comida> items = Comidas.uno;

    public AdaptadorInicio(Context context) {
     this.context = context;
    }

    public int getItemCount() {
        return items.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v,this);
    }
    @Override
    public void onItemClick(View view, int position) {
        View sharedImage = view.findViewById(R.id.image);

        DetailActivity.launch((Activity) context, position, sharedImage ,items,0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public TextView voteCount;
        public TextView date;
        public ItemClickListener listener;

        public ViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
            voteCount= (TextView) v.findViewById(R.id.voteCount);
            date = (TextView) v.findViewById(R.id.date);
            v.setOnClickListener(this);
            this.listener = listener;
        }


        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Comida item = Comidas.uno.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.voteCount.setText(item.getVoteCount()+"");
        viewHolder.date.setText(item.getDate()+"");
        viewHolder.precio.setText(item.getRating()+"/10");
    }


}
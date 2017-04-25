package com.herprogramacion.restaurantericoparico.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comida;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorSeries extends RecyclerView.Adapter<AdaptadorSeries.ViewHolder> implements ItemClickListener {
    private final Context context;
    private final List<Comida> items;
    private final int tipo;
    public AdaptadorSeries(Context context, List<Comida> items, int tipo) {
        this.tipo=tipo;
        this.items = items;
        this.context = context;
    }
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new ViewHolder(v,this);
    }

    public void onItemClick(View view, int position) {
        // Imagen a compartir entre transiciones
        View sharedImage = view.findViewById(R.id.image);

            DetailActivity.launch((Activity) context, position, sharedImage, items,tipo);

    }




    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Comida item = items.get(i);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText(item.getRating()+"/10");
        Picasso.with(getContext()).load(Uri.parse("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+item.getIdDrawable())).error(R.drawable.ic_nocover).into(viewHolder.imagen);
        //viewHolder.test.setText(item.getopenLibraryId());
    }

    public Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public ItemClickListener listener;
        //public TextView test;
        public ViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
           // test  =(TextView) v.findViewById(R.id.test);
            v.setOnClickListener(this);
            this.listener = listener;
        }


        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }


}
interface ItemClickListener {
    void onItemClick(View view, int position);
}

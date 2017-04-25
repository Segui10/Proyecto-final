package com.herprogramacion.restaurantericoparico.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comidas;

/**
 * Created by 1DAW on 23/03/17.
 */

public class FragmentoSeriesTabs extends Fragment {

    private static final String INDICE_SECCION
            = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorSeries adaptador;

    public static FragmentoSeriesTabs nuevaInstancia(int indiceSeccion) {
        FragmentoSeriesTabs fragment = new FragmentoSeriesTabs();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                adaptador = new AdaptadorSeries(getContext(), Comidas.getTopRatedS(),2);
                break;
            case 1:
                adaptador = new AdaptadorSeries(getContext(),Comidas.getMorePopSer(),2);
                break;
            case 2:
                adaptador = new AdaptadorSeries(getContext(),Comidas.getOnAir(),2);
                break;
        }

        reciclador.setAdapter(adaptador);

        return view;
    }

}

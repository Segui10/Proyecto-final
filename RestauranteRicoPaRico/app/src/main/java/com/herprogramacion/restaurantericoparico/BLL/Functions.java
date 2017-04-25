package com.herprogramacion.restaurantericoparico.BLL;

import com.herprogramacion.restaurantericoparico.models.Book;
import com.herprogramacion.restaurantericoparico.ui.Singleton;

/**
 * Created by Segui on 25/04/2017.
 */

public class Functions {
    public static int findPeli(Book admin) {
        for (int i = 0; i<=(Singleton.PeliculasFav.size()-1); i++){
            if((Singleton.PeliculasFav.get(i)).equals(admin) )
                return i;
        }
        return -1;
    }
}

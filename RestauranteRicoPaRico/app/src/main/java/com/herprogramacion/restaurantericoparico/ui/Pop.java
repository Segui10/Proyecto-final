package com.herprogramacion.restaurantericoparico.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.herprogramacion.restaurantericoparico.R;

/**
 * Created by Sergio on 05/04/2017.
 */

public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;
        getWindow().setLayout((int)(width*.5),(int)(height*.2));
    }
}

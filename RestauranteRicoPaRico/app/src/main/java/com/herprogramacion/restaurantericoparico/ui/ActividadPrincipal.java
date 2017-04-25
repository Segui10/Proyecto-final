package com.herprogramacion.restaurantericoparico.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comidas;
import com.herprogramacion.restaurantericoparico.ui.activities.BookListActivity;
import com.herprogramacion.restaurantericoparico.ui.init.MorePopMovie;
import com.herprogramacion.restaurantericoparico.ui.init.MorePopSer;
import com.herprogramacion.restaurantericoparico.ui.init.NowPlaying;
import com.herprogramacion.restaurantericoparico.ui.init.OnAir;
import com.herprogramacion.restaurantericoparico.ui.init.TopRatedS;
import com.herprogramacion.restaurantericoparico.ui.init.test;

import java.util.Locale;

public class ActividadPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Comidas.MorePopSer.clear();
        Comidas.Movies.clear();
        Comidas.MorePopular.clear();
        Comidas.NowPlaying.clear();
        Comidas.OnAir.clear();
        Comidas.TopRatedS.clear();
        startActivity(new Intent(this, test.class));
        startActivity(new Intent(this, MorePopMovie.class));
        startActivity(new Intent(this, NowPlaying.class));
        startActivity(new Intent(this, TopRatedS.class));
        startActivity(new Intent(this, MorePopSer.class));
        startActivity(new Intent(this, OnAir.class));
        //startActivity(new Intent(this, MorePopMovie.class));
        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            /*Inicio*/
            case R.id.item_inicio:
                //startActivity(new Intent(this, test.class));
                fragmentoGenerico = new FragmentoInicio();
                break;
            /*Peliculas*/
            case R.id.item_cuenta:
                fragmentoGenerico = new FragmentoPeliculas();
                break;
            /*Series*/
            case R.id.item_categorias:
                fragmentoGenerico = new FragmentoSeries();
                break;
             /*Favoritos*/
            case R.id.favoritos:
                fragmentoGenerico = new FragmentFavorite();
                break;
            /*Config*/
            case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
            /*Search*/
            case R.id.item_search:
                startActivity(new Intent(this, BookListActivity.class));
                break;
            /*Contact*/
            case R.id.item_contact:
                startActivity(new Intent(this, ActividadContact.class));
                break;
            case R.id.item_forum:
                startActivity(new Intent(this, ForumActivity.class));
            break;
            case R.id.en:
                change_lang("en");
                break;
            case R.id.es:
                change_lang("es");
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void change_lang(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        //String lang_default = Locale.getDefault().getLanguage();
        //Toast toast1 = Toast.makeText(getApplicationContext(), lang_default, Toast.LENGTH_SHORT);
        //toast1.show();

        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
        //getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        finish();
        Intent refresh = new Intent(ActividadPrincipal.this, ActividadPrincipal.class);
        startActivity(refresh);
    }

    public void searchpelis(){
        startActivity(new Intent(this, BookListActivity.class));
    }
}

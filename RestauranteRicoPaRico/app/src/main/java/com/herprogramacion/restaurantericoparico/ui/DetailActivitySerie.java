package com.herprogramacion.restaurantericoparico.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comida;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Sergio on 01/04/2017.
 */

public class DetailActivitySerie extends AppCompatActivity {
    private static final String EXTRA_POSITION = "com.herprogramacion.cursospoint.extra.POSITION";
    public static int position1;
    private static List<Comida> itemss;
    private TextView name;
    private TextView date2;
    private TextView budget2;
    private TextView revenue2;
    private TextView production2;
    private TextView detail_star2;
    private TextView detail_descriptio2;
    private ImageView ivBookCover;
    private String idfilm;
    private Comida comida;
    private int tipo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_test);
        ivBookCover = (ImageView) findViewById(R.id.detail_imag2);
        name = (TextView) findViewById(R.id.detail_namedet);
        date2 = (TextView) findViewById(R.id.date2);
        budget2 = (TextView) findViewById(R.id.budget2);
        revenue2 = (TextView) findViewById(R.id.revenue2);
        production2 = (TextView) findViewById(R.id.production3);
        detail_star2 = (TextView) findViewById(R.id.dt);
        detail_descriptio2=(TextView) findViewById(R.id.detail_descriptio2);
        idfilm=itemss.get(position1).toString();

        Toast alert = Toast.makeText(this,"Valor Position"+position1,Toast.LENGTH_LONG);
        alert.show();
        alert = Toast.makeText(this,"Valor Position"+itemss.get(position1).toString(),Toast.LENGTH_LONG);
        alert.show();
        setToolbar();
        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);
        setupViews(itemss,position,idfilm);
    }
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)// Habilitar Up Button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }






    private void setupViews(final List<Comida> items, int position, String idfilm) {
        this.position1 = position;
        comida=new Comida();
        Picasso.with(this).load(Uri.parse(items.get(position1).getImg())).error(R.drawable.ic_nocover).into(ivBookCover);

        //Comida detailCourse = Comidas.getCourseByPosition(items,position);

        comida.getExtraBookDetails(itemss.get(position1).toString(),0, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    name.setText(response.getString("original_name"));
                    date2.setText(response.getString("first_air_date"));
                    budget2.setText(Integer.toString(response.getInt("budget"))+" $");
                    revenue2.setText(Integer.toString(response.getInt("revenue"))+" $");
                    detail_descriptio2.setText(response.getString("overview"));
                    production2.setText(response.getString(""));
                    detail_star2.setText(response.getString("vote_average"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }








    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                // Obtener intent de la actividad padre
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Comprobar si DetailActivity no se creó desde CourseActivity
                if (NavUtils.shouldUpRecreateTask(this, upIntent) || this.isTaskRoot()) {
                    // Construir de nuevo la tarea para ligar ambas actividades
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Terminar con el método correspondiente para Android 5.x
                    this.finishAfterTransition();
                    return true;
                }
                // Dejar que el sistema maneje el comportamiento del up button
                return false;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static void launch(Activity context, int position, View sharedView ,List<Comida> items,int tipo) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        tipo=tipo;
        position1=position;
        itemss=items;
        context.startActivity(intent);
    }
}

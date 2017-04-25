package com.herprogramacion.restaurantericoparico.ui.init;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.herprogramacion.restaurantericoparico.R;

/**
 * Created by Segui on 25/04/2017.
 */

public class Peliculas extends AppCompatActivity {
    private ProgressDialog pDialog;
    private ListView lv;

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        lv = (ListView) findViewById(R.id.list);

        new Peliculas.GetContacts().execute();
        finish();
    }
    public class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Peliculas.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.hide();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.hide();
/*
            ListAdapter adapter = new SimpleAdapter(
                    test.this, contactList,
                    R.layout.test_item_list, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            lv.setAdapter(adapter);*/
        }

    }
}

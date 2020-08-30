package com.cryptbrn.favapp.view;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptbrn.favapp.R;
import com.cryptbrn.favapp.viewModel.FavUsersAdapter;

import static com.cryptbrn.favapp.db.DbUserContract.UserColumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv_fav);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState == null){
            new Favorite().execute();
        }
    }

    private class Favorite extends AsyncTask<Void,Void, Cursor> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Cursor doInBackground(Void... voids) {
            return getApplicationContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            FavUsersAdapter adapterfav = new FavUsersAdapter(getApplicationContext());
            adapterfav.setCursor(cursor);
            adapterfav.notifyDataSetChanged();
            rv.setAdapter(adapterfav);
        }
    }
}

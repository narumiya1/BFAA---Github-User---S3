package com.cryptbrn.gitconnect.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.viewModel.UsersAdapter;
import com.cryptbrn.gitconnect.api.Client;
import com.cryptbrn.gitconnect.model.Responses;
import com.cryptbrn.gitconnect.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<User> gitdata = new ArrayList<>();
    RecyclerView userrv;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }

        progress = findViewById(R.id.progressBar);
        userrv = findViewById(R.id.su_rv);

        userrv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (sm != null) {
            SearchView sv = findViewById(R.id.searchview_main);
            sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
            sv.setQueryHint(getResources().getString(R.string.hintusername));
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    showProgress(true);
                    if (s != null) {
                        getDataOnline(s);
                    } else {
                        Toast.makeText(MainActivity.this, "Insert Username First", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return true;
                }
            });
        }
    }

    private void showProgress(Boolean state) {
        if (state) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }
    }

    private void getDataOnline(final String username) {
        Call<Responses> requests = Client.getService().getSearchUser(username);
        requests.enqueue(new Callback<Responses>() {
            @Override
            public void onResponse(@NotNull Call<Responses> call, @NotNull Response<Responses> response) {
                if (response.isSuccessful()) {
                    //Mengambil data dari internet masuk ke Data Github
                    assert response.body() != null;
                    gitdata = response.body().getItems();
                    userrv.setAdapter(new UsersAdapter(MainActivity.this, gitdata));
                    showProgress(false);

                } else {
                    Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Responses> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optmenu) {
        getMenuInflater().inflate(R.menu.main_menu, optmenu);
        return super.onCreateOptionsMenu(optmenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem optitem) {
        if (optitem.getItemId() == R.id.action_change_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        else if (optitem.getItemId() == R.id.fav) {
            Intent intent2 = new Intent(this, FavoriteActivity.class);
            startActivity(intent2);
        }

        else if (optitem.getItemId() == R.id.settings) {
            Intent intent3 = new Intent(this, SettingsActivity.class);
            startActivity(intent3);
        }
        return super.onOptionsItemSelected(optitem);

    }
}

package com.cryptbrn.gitconnect.view;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.viewModel.FavUsersAdapter;
import com.cryptbrn.gitconnect.db.UserHelper;
import com.cryptbrn.gitconnect.model.User;
import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private UserHelper helper;
    private ArrayList<User> userList =  new ArrayList<>();
    private FavUsersAdapter adapterfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        helper = new UserHelper(getApplicationContext());
        helper.open();
        userList = helper.getDataUser();
        setRecyclerView();
        getSupportActionBar().setTitle(R.string.fav_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapterfav = new FavUsersAdapter(getApplicationContext());
        recyclerView.setAdapter(adapterfav);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userList = helper.getDataUser();
        adapterfav.setUserList(userList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}

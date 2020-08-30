package com.cryptbrn.gitconnect.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.viewModel.UsersAdapter;
import com.cryptbrn.gitconnect.viewModel.ViewPagerAdapter;
import com.cryptbrn.gitconnect.api.Client;
import com.cryptbrn.gitconnect.db.DbUserContract;
import com.cryptbrn.gitconnect.db.DbUserHelper;
import com.cryptbrn.gitconnect.db.UserHelper;
import com.cryptbrn.gitconnect.model.Detail;
import com.cryptbrn.gitconnect.model.User;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.TABLE_NAME;

public class DetailActivity extends AppCompatActivity {

    Detail detaildata;
    User userdata;
    TextView repositorytv ,nametv, usernametv, locationtv, companytv;
    ImageView avatarciv;
    private ArrayList<User> userList = new ArrayList<>();
    private UserHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle getbundle = getIntent().getBundleExtra(UsersAdapter.DATA_EXTRA);
        assert getbundle != null;
        userdata = getIntent().getParcelableExtra(UsersAdapter.DATA_USER);
        pager();
        setDataDetail();
        setOnClickFavoriteButton(userdata);
        helper = UserHelper.getInstance(getApplicationContext());


    }
    private void setOnClickFavoriteButton(final User userdata){
        MaterialFavoriteButton favButton = findViewById(R.id.mfb_favorite);
        if (READY(userdata.getLogin())){
            favButton.setFavorite(true);
            favButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite){
                        userList = helper.getDataUser();
                        helper.userInsert(userdata);
                        Toast.makeText(DetailActivity.this, R.string.add, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        userList = helper.getDataUser();
                        helper.userDelete(String.valueOf(userdata.getId()));
                        Toast.makeText(DetailActivity.this, R.string.delete, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            favButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite){
                        userList = helper.getDataUser();
                        helper.userInsert(userdata);
                        Toast.makeText(DetailActivity.this, R.string.add, Toast.LENGTH_SHORT).show();
                    }else {
                        userList = helper.getDataUser();
                        helper.userDelete(String.valueOf(userdata.getId()));
                        Toast.makeText(DetailActivity.this, R.string.delete, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private boolean READY(String username){
        String replace = DbUserContract.UserColumns.USERNAME + "=?";
        String[] replaceargs = {username};
        String limit = "1";
        helper = new UserHelper(this);
        helper.open();
        userdata = getIntent().getParcelableExtra("DATA_USER");
        DbUserHelper dbHelper = new DbUserHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_NAME,null,replace,replaceargs,null,null,null,limit);
        boolean exist = (cursor.getCount() > 0 );
        cursor.close();
        return exist;
    }

    private void setDataDetail(){
        avatarciv = findViewById(R.id.avatar_detail_civ);
        usernametv = findViewById(R.id.username_detail_tv);
        nametv = findViewById(R.id.name_detail_tv);
        repositorytv = findViewById(R.id.repository_detail_tv);
        locationtv = findViewById(R.id.location_detail_tv);
        companytv = findViewById(R.id.company_detail_tv);

        final ProgressDialog progressbar = new ProgressDialog(DetailActivity.this);
        progressbar.setMessage(getString(R.string.progress));
        progressbar.show();

        Glide.with(DetailActivity.this)
                .load(userdata.getAvatarUrl())
                .into(avatarciv);
        usernametv.setText(userdata.getLogin());

        Call<Detail> requests = Client.getService().getDetailUser(userdata.getLogin());
        requests.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(@NotNull Call<Detail> call, @NotNull Response<Detail> responses) {
                detaildata = responses.body();

                assert detaildata != null;
                nametv.setText(detaildata.getName());
                locationtv.setText(detaildata.getLocation());
                repositorytv.setText(detaildata.getPublicRepos());
                companytv.setText(detaildata.getCompany());
                progressbar.dismiss();
            }

            @Override
            public void onFailure(@NotNull Call<Detail> call, @NotNull Throwable t) {

            }
        });
    }

    private void pager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, getSupportFragmentManager(), userdata);
        ViewPager vp = findViewById(R.id.view_pager);
        vp.setAdapter(adapter);
        TabLayout tl = findViewById(R.id.tab_detail);
        tl.setupWithViewPager(vp);

    }

}

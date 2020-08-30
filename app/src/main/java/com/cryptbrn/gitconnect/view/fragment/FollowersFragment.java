package com.cryptbrn.gitconnect.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cryptbrn.gitconnect.view.DetailActivity;
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.viewModel.FollowersAdapter;
import com.cryptbrn.gitconnect.viewModel.UsersAdapter;
import com.cryptbrn.gitconnect.api.Client;
import com.cryptbrn.gitconnect.model.Followers;
import com.cryptbrn.gitconnect.model.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersFragment extends Fragment {
    RecyclerView followersrv;
    User userdata;

    public FollowersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflate, ViewGroup container, Bundle savedInstanceState) {
        return inflate.inflate(R.layout.fragment_followers, container, false);
    }

    @Override
    public void onViewCreated(@NotNull final View viewitem, @Nullable Bundle savedInstanceState){
        super.onViewCreated(viewitem, savedInstanceState);

        DetailActivity detail = (DetailActivity) getActivity();
        assert detail != null;
        Bundle getbundle = detail.getIntent().getBundleExtra(UsersAdapter.DATA_EXTRA);
        assert getbundle != null;
        //userdata = Parcels.unwrap(getbundle.getParcelable(UsersAdapter.DATA_USER));
        Bundle bundle = getActivity().getIntent().getExtras();
        assert bundle != null;
        userdata = bundle.getParcelable(UsersAdapter.DATA_USER);
        //userdata = getContext().getParcelableExtra(UsersAdapter.DATA_USER);

        followersrv = viewitem.findViewById(R.id.followers_rv);
        followersrv.setLayoutManager(new LinearLayoutManager(viewitem.getContext()));

        Call<List<Followers>> requests = Client.getService().getFollowerUser(userdata.getLogin());
        requests.enqueue(new Callback<List<Followers>>() {
            @Override
            public void onResponse(@NotNull Call<List<Followers>> call, @NotNull Response<List<Followers>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        ArrayList<Followers> followerslist = new ArrayList<>(response.body());
                        Log.d("Result", "Response: " +followerslist.size());
                        followersrv.setAdapter(new FollowersAdapter(getContext(), followerslist));
                    }
                } else {
                    Toast.makeText(getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Followers>> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

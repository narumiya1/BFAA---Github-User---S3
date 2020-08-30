package com.cryptbrn.gitconnect.view.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import com.cryptbrn.gitconnect.view.DetailActivity;
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.viewModel.FollowingAdapter;
import com.cryptbrn.gitconnect.viewModel.UsersAdapter;
import com.cryptbrn.gitconnect.model.Following;
import com.cryptbrn.gitconnect.model.User;
import com.cryptbrn.gitconnect.api.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FollowingFragment extends Fragment {
    RecyclerView followingrv;
    User userdata;

    public FollowingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflate, ViewGroup container, Bundle savedInstanceState) {
        return inflate.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NotNull final View viewitem, @Nullable Bundle savedInstanceState){
        super.onViewCreated(viewitem, savedInstanceState);

        DetailActivity detail = (DetailActivity) getActivity();
        assert detail != null;
//        Bundle getbundle = detail.getIntent().getBundleExtra(UsersAdapter.DATA_EXTRA);
//        assert getbundle != null;
//        userdata = Parcels.unwrap(getbundle.getParcelable(UsersAdapter.DATA_USER));
        Bundle bundle = getActivity().getIntent().getExtras();
        assert bundle != null;
        userdata = bundle.getParcelable(UsersAdapter.DATA_USER);

        followingrv = viewitem.findViewById(R.id.following_rv);
        followingrv.setLayoutManager(new LinearLayoutManager(viewitem.getContext()));

        Call<List<Following>> requests = Client.getService().getFollowingUser(userdata.getLogin());
        requests.enqueue(new Callback<List<Following>>() {
            @Override
            public void onResponse(@NotNull Call<List<Following>> call, @NotNull Response<List<Following>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        ArrayList<Following> followinglist = new ArrayList<>(response.body());
                        Log.d("Result", "Response: " +followinglist.size());
                        followingrv.setAdapter(new FollowingAdapter(getContext(), followinglist));
                    }
                }
                else {
                    Toast.makeText(getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Following>> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

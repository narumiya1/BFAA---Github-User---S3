package com.cryptbrn.gitconnect.viewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.model.Followers;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder> {

    private Context context;
    private ArrayList<Followers> mdata;

    public FollowersAdapter(Context mcontext, ArrayList<Followers> followerslist) {
        this.context = mcontext;
        this.mdata = followerslist;
    }

    @NonNull
    @Override
    public FollowersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_followers, parent, false);
        return new FollowersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersViewHolder holder, int position) {
        holder.UsernameFollowerstv.setText(mdata.get(position).getLogin());
        Glide.with(context)
                .load(mdata.get(position).getAvatarUrl())
                .into(holder.AvatarFollowersciv);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
    public static class FollowersViewHolder extends RecyclerView.ViewHolder {
        TextView UsernameFollowerstv;
        ImageView AvatarFollowersciv;

        public FollowersViewHolder(@NonNull View itemView) {
            super(itemView);
            UsernameFollowerstv = itemView.findViewById(R.id.usrnm_followers_tv);
            AvatarFollowersciv = itemView.findViewById(R.id.avatar_followers_civ);

        }
    }
}

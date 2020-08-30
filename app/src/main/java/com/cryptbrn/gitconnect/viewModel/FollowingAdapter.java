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
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.model.Following;
import java.util.ArrayList;


public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder> {

    private Context context;
    private ArrayList<Following> mdata;

    public FollowingAdapter(Context mcontext, ArrayList<Following> followinglist) {
        this.context = mcontext;
        this.mdata = followinglist;
    }

    @NonNull
    @Override
    public FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_following, parent, false);
        return new FollowingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingViewHolder holder, int position) {
        holder.UsernameFollowingtv.setText(mdata.get(position).getLogin());
        Glide.with(context)
                .load(mdata.get(position).getAvatarUrl())
                .into(holder.AvatarFollowingciv);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class FollowingViewHolder extends RecyclerView.ViewHolder {
        TextView UsernameFollowingtv;
        ImageView AvatarFollowingciv;

        public FollowingViewHolder(@NonNull View itemView) {
            super(itemView);

            UsernameFollowingtv = itemView.findViewById(R.id.usrnm_following_tv);
            AvatarFollowingciv = itemView.findViewById(R.id.avatar_following_civ);
        }
    }
}

package com.cryptbrn.gitconnect.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.cryptbrn.gitconnect.view.DetailActivity;
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.model.User;
import org.parceler.Parcels;
import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    public static final String DATA_USER = "userdata";
    public static final String DATA_EXTRA = "extradata";
    private Context mcontext;
    private List<User> mdata;


    public UsersAdapter(Context mcontext, List<User> mdata) {
        this.mcontext = mcontext;
        this.mdata = mdata;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_users, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.Usernametv.setText(mdata.get(position).getLogin());
        Glide.with(mcontext)
                .load(mdata.get(position).getAvatarUrl())
                .into(holder.Avatarciv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveDetailActivity = new Intent(mcontext, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER, Parcels.wrap(mdata.get(position)));
                moveDetailActivity.putExtra(DATA_USER, mdata.get(position));
                mcontext.startActivity(moveDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Usernametv;
        ImageView Avatarciv;
        public MyViewHolder(@NonNull View view) {
            super(view);
            Usernametv = itemView.findViewById(R.id.usrnm_users_tv);
            Avatarciv = itemView.findViewById(R.id.avatar_users_civ);
        }
    }
}

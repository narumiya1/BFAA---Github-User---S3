package com.cryptbrn.favapp.viewModel;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cryptbrn.favapp.view.DetailActivity;
import com.cryptbrn.favapp.model.User;
import com.cryptbrn.favapp.R;


public class FavUsersAdapter extends RecyclerView.Adapter<FavUsersAdapter.UserViewHolder> {
    private Context context;
    private Cursor c;

    public FavUsersAdapter(Context c) {
        this.context = c;
    }

    public void setCursor(Cursor cursor) {
        this.c = cursor;
    }

    private User getItemData(int position){
        if (!c.moveToPosition(position)){
            throw new IllegalStateException("INVALID");
        }
        return new User(c);
    }
    @NonNull
    @Override
    public FavUsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavUsersAdapter.UserViewHolder holder, int position) {
        User user = getItemData(position);
        holder.tv_username.setText(user.getLogin());
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatarUrl())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        if (c == null) return 0;
        return c.getCount();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        TextView tv_username;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_users_civ);
            tv_username = itemView.findViewById(R.id.usrnm_users_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            User userGithub = getItemData(getAdapterPosition());
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("DATA_USER",userGithub);
            v.getContext().startActivity(i);
        }
    }
}

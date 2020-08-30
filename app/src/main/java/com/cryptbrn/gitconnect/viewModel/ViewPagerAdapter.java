package com.cryptbrn.gitconnect.viewModel;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.view.fragment.FollowersFragment;
import com.cryptbrn.gitconnect.view.fragment.FollowingFragment;
import com.cryptbrn.gitconnect.model.User;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final Context Context;
    private User userData;

    public ViewPagerAdapter(Context mcontext, FragmentManager fm, User user) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        userData = user;
        Context = mcontext;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.title_follower,
            R.string.title_following
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragmenttab = null;
        switch (position) {
            case 0:
                fragmenttab = new FollowersFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(UsersAdapter.DATA_USER, userData);
                fragmenttab.setArguments(bundle);
                break;
            case 1:
                fragmenttab = new FollowingFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable(UsersAdapter.DATA_USER, userData);
                fragmenttab.setArguments(bundle1);
                break;
        }
        assert fragmenttab != null;
        return fragmenttab;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}

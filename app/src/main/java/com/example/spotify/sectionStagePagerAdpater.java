package com.example.spotify;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class sectionStagePagerAdpater extends FragmentStatePagerAdapter {
    List<fragmentHomeDisplay>  fragmentsList = new ArrayList<>();

    public sectionStagePagerAdpater(@NonNull FragmentManager fm, List<fragmentHomeDisplay> fragmentsList) {
        super(fm);
        this.fragmentsList = fragmentsList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    public void AddFragment(fragmentHomeDisplay f1)
    {
         fragmentsList.add(f1);

    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}


/////////////////////////////////////



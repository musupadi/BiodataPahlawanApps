package com.destinyapp.skripsiapps.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentsList = new ArrayList<>();
    private final List<String> FragmentListTittles = new ArrayList<>();
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return FragmentListTittles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTittles.get(position);
    }

    public void AddFragment(Fragment fragment,String title){
        fragmentsList.add(fragment);
        FragmentListTittles.add(title);
    }


}

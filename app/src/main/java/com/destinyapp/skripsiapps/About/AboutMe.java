package com.destinyapp.skripsiapps.About;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.destinyapp.skripsiapps.Adapter.TabPagerAdapter;
import com.destinyapp.skripsiapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutMe extends Fragment {

    private TabLayout Table;
    private AppBarLayout appBar;
    private ViewPager viewPager;
    private FragmentActivity context;
    public AboutMe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aboutme, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Table = (TabLayout)view.findViewById(R.id.tableLayout);
        appBar = (AppBarLayout)view.findViewById(R.id.appBarId);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager());
        adapter.AddFragment(new TabDetail(),"Detail");
        adapter.AddFragment(new TabDeskripsi(),"Deskripsi");
        viewPager.setAdapter(adapter);
        Table.setupWithViewPager(viewPager);
    }

}

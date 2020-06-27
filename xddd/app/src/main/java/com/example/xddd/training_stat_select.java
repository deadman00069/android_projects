package com.example.xddd;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class training_stat_select extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    NavController navController;
    stats_date_select f1;
    stats_graphView f2;
    public training_stat_select() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_stat_select, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);
        f1=new stats_date_select();
        f1.setArguments(getArguments());
        f2=new stats_graphView();
        f2.setArguments(getArguments());
        ViewPagerAdapter viewpageAdapter= new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewpageAdapter.addFragments(f1,"stats");
        viewpageAdapter.addFragments(f2,"Graph");
        viewPager.setAdapter(viewpageAdapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments=new ArrayList<>();
        List<String> titles=new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        public void addFragments(Fragment fragment,String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}

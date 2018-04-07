package com.wazaby.android.wazaby.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wazaby.android.wazaby.R;

public class Accueil extends Fragment {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.accueil, container, false);

        final TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        //tabLayout.addTab(tabLayout.newTab().setText(""));
        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        int[] tabIcons = {
                R.drawable.ic_sms_black_24dp,
                R.drawable.ic_question_answer_black_24dp,
                //R.drawable.ic_notifications_black_24dp
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
       // tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.colorPrimary));
        tabLayout.getTabAt(1).getIcon().setTint(getResources().getColor(R.color.graycolor));
        //tabLayout.getTabAt(2).getIcon().setTint(getResources().getColor(R.color.graycolor));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                {
                    tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.colorPrimary));
                    tabLayout.getTabAt(1).getIcon().setTint(getResources().getColor(R.color.graycolor));
                   // tabLayout.getTabAt(2).getIcon().setTint(getResources().getColor(R.color.graycolor));
                }else if(tab.getPosition()==1)
                {
                    //tabLayout.getTabAt(2).getIcon().setTint(getResources().getColor(R.color.graycolor));
                    tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.graycolor));
                    tabLayout.getTabAt(1).getIcon().setTint(getResources().getColor(R.color.colorPrimary));
                }else
                {
                    tabLayout.getTabAt(1).getIcon().setTint(getResources().getColor(R.color.graycolor));
                    tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.graycolor));
                    //tabLayout.getTabAt(2).getIcon().setTint(getResources().getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return inflatedView;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Conversationsprivee tab1 = new Conversationsprivee();
                    return tab1;
                case 1:
                    Conversationspublic tab2 = new Conversationspublic();
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
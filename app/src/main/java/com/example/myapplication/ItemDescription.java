package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class ItemDescription extends AppCompatActivity {
//
//    private LinearLayout tabLayout;
//    private ViewPager2 viewPager;
//
//    private TabLayout tb;

    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_item_description);
//        ImageView backButton = findViewById(R.id.backButton2);
//
//        tabLayout = findViewById(R.id.topRow2);
//
//        viewPager = findViewById(R.id.viewPagerIDDesc);
//
//        tb = findViewById(R.id.tabsLayout);
//        VPAdapter vpAdapter = null;
//        viewPager.setAdapter(vpAdapter);
//
//
//        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        vpAdapter.addFragment(new ProductFragment(),"CHATS");
//        vpAdapter.addFragment(new PhotoFragment(), "STATUS");
//        vpAdapter.addFragment(new ProductFragment(),"CHATS");
//        vpAdapter.addFragment(new PhotoFragment(), "STATUS");
//
//        TabLayout.Tab tab = tb.getTabAt(0);
//        if (tab != null) {
//            tab.setIcon(R.drawable.information_variant);
//        }
//        TabLayout.Tab tab2 = tb.getTabAt(1);
//        if (tab2 != null) {
//            tab2.setIcon(R.drawable.truck_delivery);
//        }
//        TabLayout.Tab tab3 = tb.getTabAt(2);
//        if (tab3 != null) {
//            tab3.setIcon(R.drawable.google);
//        }
//        TabLayout.Tab tab4 = tb.getTabAt(3);
//        if (tab4 != null) {
//            tab4.setIcon(R.drawable.equal);
//        }
//
//        viewPager.setAdapter(vpAdapter);
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        ImageView backButton = findViewById(R.id.backButton2);
//        LinearLayout tabLayout = findViewById(R.id.topRow2);
        ViewPager viewPager = findViewById(R.id.viewPagerIDDesc);
        TabLayout tabLayout = findViewById(R.id.tabsLayout);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        vpAdapter.addFragment(new ProductFragment(), "CHATS");
//        vpAdapter.addFragment(new PhotoFragment(), "STATUS");
//        vpAdapter.addFragment(new ProductFragment(), "CHATS");
//        vpAdapter.addFragment(new PhotoFragment(), "STATUS");

        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setIcon(R.drawable.information_variant);
        tab1.setText("Product");
        tabLayout.addTab(tab1);

        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setIcon(R.drawable.truck_delivery);
        tab2.setText("Shipping");
        tabLayout.addTab(tab2);

        TabLayout.Tab tab3 = tabLayout.newTab();
        tab3.setIcon(R.drawable.google);
        tab3.setText("Photos");
        tabLayout.addTab(tab3);

        TabLayout.Tab tab4 = tabLayout.newTab();
        tab4.setIcon(R.drawable.equal);
        tab4.setText("Similar");
        tabLayout.addTab(tab4);
        Bundle bundle = getIntent().getExtras();
        vpAdapter.addFragment(new ProductFragment(), tab1, bundle);

        vpAdapter.addFragment(new ShippingFragment(), tab2, bundle);

        vpAdapter.addFragment(new PhotoFragment(), tab3, bundle);

        vpAdapter.addFragment(new PhotoFragment(), tab4, bundle);


        viewPager.setAdapter(vpAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Unused for this purpose
            }

            @Override
            public void onPageSelected(int position) {
                // Highlight the corresponding tab when a page is selected
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Unused for this purpose
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Unused for this purpose
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Unused for this purpose
            }
        });

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                // Define your icon changes when a tab is selected
//                // For instance, change the icon when the tab is selected
//                switch (tab.getPosition()) {
//                    case 0:
//                        tab.setIcon(R.drawable.equal);
//                        break;
//                    case 1:
//                        tab.setIcon(R.drawable.truck_delivery);
//                        break;
//                    // Add cases for other tabs if needed
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                // Clear the icon when the tab is unselected
//                tab.setIcon(null);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                // React to a tab being reselected (if needed)
//            }
//        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
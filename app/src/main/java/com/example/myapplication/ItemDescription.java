package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class ItemDescription extends AppCompatActivity {


    String similarVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        ImageView backButton = findViewById(R.id.backButton2);
//        LinearLayout tabLayout = findViewById(R.id.topRow2);
        ViewPager viewPager = findViewById(R.id.viewPagerIDDesc);
        TabLayout tabLayout = findViewById(R.id.tabsLayout);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

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

        try{
            RequestQueue queue = Volley.newRequestQueue(this);
        // Define the URL for the GET request
            String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/getSimilarItems/374707336304";
            // Create a JsonObjectRequest
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JsonObject gsonJsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
                                bundle.putString("jsonObjectStringSimilar", gsonJsonObject.toString());
                                Log.d( "Gson JsonObject: " , gsonJsonObject.toString());
                            } catch (Exception e) {
                                Log.e( "Error parsing JSON to Gson JsonObject: " , e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Error occurred during the request
                    Log.e("Volley Error: " , error.toString());
                }
            });



            vpAdapter.addFragment(new SimilarItemsFragment(), tab4, bundle);
            // Add the request to the RequestQueue
            queue.add(jsonObjectRequest);
        }
        catch (Exception e){
            Log.d("errorrr", e.toString());
        }
//
//        vpAdapter.addFragment(new SimilarItemsFragment(), tab4, new Bundle());
//        vpAdapter.addFragment(new SimilarItemsFragment(), tab4, bundle);
//        bundle.putString("jsonObjectStringSimilar", gsonJsonObject.toString());
        vpAdapter.addFragment(new SimilarItemsFragment(), tab4, bundle);
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


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
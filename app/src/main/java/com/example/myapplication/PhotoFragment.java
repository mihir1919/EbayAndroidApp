package com.example.myapplication;

import static com.example.myapplication.R.id.buttonLinearLayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.design.ImagePagerAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhotoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_photo, container, false);


        Bundle args = getArguments();
        try{
            if (args != null) {
                String jsonString = args.getString("jsonObject");
                JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(jsonString), JsonObject.class);
                if (receivedJsonObject != null) {
                    try{
                        if(receivedJsonObject.get("PictureURL").getAsJsonArray()!=null){

                            for(int i=0;i<receivedJsonObject.get("PictureURL").getAsJsonArray().size();i++){
                                receivedJsonObject.get("PictureURL").getAsJsonArray().get(i).toString();
                            }
//                            ViewPager viewPager = view.findViewById(R.id.viewPager);

                            String[] imageUrls = new String[receivedJsonObject.get("PictureURL").getAsJsonArray().size()];

                            for (int i = 0; i < imageUrls.length; i++) {
                                imageUrls[i] = receivedJsonObject.get("PictureURL").getAsJsonArray().get(i).getAsString();
                            }
                            LinearLayout linearLayout = view.findViewById(R.id.buttonLinearLayout);
                            for (String imageUrl : imageUrls) {
                                ImageView imageView = new ImageView(view.getContext());
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                imageView.setLayoutParams(layoutParams);

                                Picasso.get().load(imageUrl).into(imageView);
                                linearLayout.addView(imageView);
                            }

//                            ImagePagerAdapter adapter = new ImagePagerAdapter(view.getContext(), imageUrls);
//                            viewPager.setAdapter(adapter);
                        }
                        else{
                        }
                    }
                    catch (Exception e){
                    }
                }

            }
        }
        catch (Exception e){
            Log.d("Exception Fifa", e.toString());
        }



        return view;
    }
}
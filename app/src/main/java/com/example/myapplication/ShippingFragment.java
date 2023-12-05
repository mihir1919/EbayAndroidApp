package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.design.ImagePagerAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShippingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_shipping, container, false);
        Bundle args = getArguments();
        try{
            if (args != null) {



                String jsonString = args.getString("jsonObject");
                JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(jsonString), JsonObject.class);
                if (receivedJsonObject != null) {
                    try{
                        if(receivedJsonObject.get("Storefront").getAsJsonObject().get("StoreName")!=null){
                            TextView title = view.findViewById(R.id.TV1);
                            title.setText(receivedJsonObject.get("Storefront").getAsJsonObject().get("StoreName").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV1);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore")!=null){
                            TextView title = view.findViewById(R.id.TV2);
                            title.setText(receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV2);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("Seller").getAsJsonObject().get("PositiveFeedbackPercent")!=null){
                            TextView title = view.findViewById(R.id.TV3);
                            title.setText(receivedJsonObject.get("Seller").getAsJsonObject().get("PositiveFeedbackPercent").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV3);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore")!=null){
                            TextView title = view.findViewById(R.id.TV4);
                            title.setText(receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV4);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }


                    try{
                        if(receivedJsonObject.get("GlobalShipping").getAsString()=="true"){
                            TextView title = view.findViewById(R.id.TV5);
                            title.setText("Yes");
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV5);
                            title.setText("No");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("HandlingTime").getAsString()!=null){
                            TextView title = view.findViewById(R.id.TV6);
                            title.setText(receivedJsonObject.get("HandlingTime").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV6);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }


                    try{
                        if(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("Refund")!=null){
                            TextView title = view.findViewById(R.id.TV7);
                            title.setText(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("Refund").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV7);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ReturnsWithin")!=null){
                            TextView title = view.findViewById(R.id.TV8);
                            title.setText(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ReturnsWithin").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV8);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ReturnsAccepted")!=null){
                            TextView title = view.findViewById(R.id.TV9);
                            title.setText(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ReturnsAccepted").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV9);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ShippingCostPaidBy")!=null){
                            TextView title = view.findViewById(R.id.TV10);
                            title.setText(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ShippingCostPaidBy").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV10);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }


//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, arrayListSpecificValues);
//                    listView.setAdapter(adapter);

                }

            }
        }
        catch (Exception e){
            Log.d("Exception Fifa", e.toString());
        }



        return view;
    }
}
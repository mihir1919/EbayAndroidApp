package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.design.ImagePagerAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_product, container, false);


        Bundle args = getArguments();
        try{
            if (args != null) {



                String jsonString = args.getString("jsonObject");
                JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(jsonString), JsonObject.class);
                if (receivedJsonObject != null) {
                    try{
                        Log.d("teststring to", receivedJsonObject.get("Title").getAsString());
                        if(receivedJsonObject.get("Title").getAsString()!=null){
                            TextView title = view.findViewById(R.id.textView11);
                            title.setText(receivedJsonObject.get("Title").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.textView11);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("PictureURL").getAsJsonArray()!=null){

                            for(int i=0;i<receivedJsonObject.get("PictureURL").getAsJsonArray().size();i++){
                                receivedJsonObject.get("PictureURL").getAsJsonArray().get(i).toString();
                            }
                            ViewPager viewPager = view.findViewById(R.id.viewPager);

                            String[] imageUrls = new String[receivedJsonObject.get("PictureURL").getAsJsonArray().size()];

                            for (int i = 0; i < imageUrls.length; i++) {
                                imageUrls[i] = receivedJsonObject.get("PictureURL").getAsJsonArray().get(i).getAsString();
                            }

//                            ViewPager viewPager = view.findViewById(R.id.viewPager);
                            ImagePagerAdapter adapter = new ImagePagerAdapter(view.getContext(), imageUrls);
                            viewPager.setAdapter(adapter);


                        }
                        else{
                        }
                    }
                    catch (Exception e){
                    }

                    if(receivedJsonObject.get("CurrentPrice").getAsJsonObject().get("Value").getAsString()!=null){
                        TextView Price = view.findViewById(R.id.textView12);
                        Price.setText(receivedJsonObject.get("CurrentPrice").getAsJsonObject().get("Value").getAsString());
                    }
                    else{

                        TextView Price = view.findViewById(R.id.textView12);
                        Price.setText("N/A");
                    }

                    HashMap<String, String> itemSpecifics = new HashMap<>();
                    if((receivedJsonObject.get("ItemSpecifics").getAsJsonObject().get("NameValueList").getAsJsonArray())!=null){
                        JsonArray specificArray = (receivedJsonObject.get("ItemSpecifics").getAsJsonObject().get("NameValueList").getAsJsonArray());
                        for(int i=0;i<specificArray.size();i++){
                            String temp = specificArray.get(i).getAsJsonObject().get("Value").getAsJsonArray().toString();
                            itemSpecifics.put(specificArray.get(i).getAsJsonObject().get("Name").getAsString(), temp);
                        }
                    }
                    try{
                        if(itemSpecifics.containsKey("Brand") && itemSpecifics.get("Brand")!=null){
                            TextView Price = view.findViewById(R.id.textView17);
                            TextView PriceVal = view.findViewById(R.id.textView162);
                            Price.setText("Brand");
                            PriceVal.setText(itemSpecifics.get("Brand"));
                        }
                        else{
                            TextView Price = view.findViewById(R.id.textView17);
                            TextView PriceVal = view.findViewById(R.id.textView162);
                            Price.setText("Brand");
                            PriceVal.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }

                    ArrayList<String> arrayListSpecificValues = new ArrayList<>();
                    for (Map.Entry<String, String> entry : itemSpecifics.entrySet()) {
                        if(entry.getKey()!="Brand"){
                            String temp=entry.getValue();
                            if(temp.length()>0 && temp.charAt(0)=='['){
                                temp=temp.substring(2, temp.length()-2);
                            }
                            arrayListSpecificValues.add(temp);
                        }
                    }

                    Log.d("specific values",arrayListSpecificValues.toString());

                    LinearLayout verticalLayout = view.findViewById(R.id.verticalLayout);

                    for (String value : arrayListSpecificValues) {
                        TextView textView = new TextView(requireContext());
                        textView.setText(value);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView.setPadding(16, 16, 16, 16); // Example padding
                        verticalLayout.addView(textView);
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
package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
                            TextView title = view.findViewById(R.id.TV1Titless);
                            title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                            title.setSelected(true);
                            title.setText(receivedJsonObject.get("Storefront").getAsJsonObject().get("StoreName").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV1Titless);
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
                        if(receivedJsonObject.get("Seller").getAsJsonObject().get("PositiveFeedbackPercent").toString()!=null) {
                            TextView percentageTitle = view.findViewById(R.id.percentageID);
                            percentageTitle.setText(receivedJsonObject.get("Seller").getAsJsonObject().get("PositiveFeedbackPercent").toString());

                            ProgressBar progressBar = view.findViewById(R.id.circularProgressBar);
                            progressBar.setProgress(Integer.parseInt(receivedJsonObject.get("Seller").getAsJsonObject().get("PositiveFeedbackPercent").toString()));
                        }
                    }
                    catch (Exception e){

                    }


                    try{

//                        if(receivedJsonObject.get("Seller").getAsJsonObject().get("PositiveFeedbackPercent")!=null){
                        if(receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore")!=null){
                            ImageView StarFeedback = view.findViewById(R.id.IVStar);
                            Integer feedbackRating = receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore").getAsInt();
                            Integer percentage = receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore").getAsInt();
                            String iconColor = "#000000"; // Default color (Black) in hexadecimal
                            String symbol = "StarBorderIcon";

                            Log.d("fucking colors", percentage.toString());

                            if (feedbackRating >= 10 && feedbackRating <= 49) {
                                iconColor = "#FFFF00"; // Yellow in hexadecimal
                                symbol = "StarBorderIcon";
                            } else if (feedbackRating >= 50 && feedbackRating <= 99) {
                                iconColor = "#0000FF"; // Blue in hexadecimal
                                symbol = "StarBorderIcon";
                            } else if (feedbackRating >= 100 && feedbackRating <= 499) {
                                iconColor = "#40E0D0"; // Turquoise in hexadecimal
                                symbol = "StarBorderIcon";
                            } else if (feedbackRating >= 500 && feedbackRating <= 999) {
                                iconColor = "#800080"; // Purple in hexadecimal
                                symbol = "StarBorderIcon";
                            } else if (feedbackRating >= 1000 && feedbackRating <= 4999) {
                                iconColor = "#FF0000"; // Red in hexadecimal
                                symbol = "StarBorderIcon";
                            } else if (feedbackRating >= 5000 && feedbackRating <= 9999) {
                                iconColor = "#008000"; // Green in hexadecimal
                                symbol = "StarBorderIcon";
                            } else if (feedbackRating >= 10000 && feedbackRating <= 24999) {
                                iconColor = "#FFFF00"; // Yellow in hexadecimal
                                symbol = "StarsIcon";
                            } else if (feedbackRating >= 25000 && feedbackRating <= 49999) {
                                iconColor = "#40E0D0"; // Turquoise in hexadecimal
                                symbol = "StarsIcon";
                            } else if (feedbackRating >= 50000 && feedbackRating <= 99999) {
                                iconColor = "#800080"; // Purple in hexadecimal
                                symbol = "StarsIcon";
                            } else if (feedbackRating >= 100000 && feedbackRating <= 499000) {
                                iconColor = "#FF0000"; // Red in hexadecimal
                                symbol = "StarsIcon";
                            } else if (feedbackRating >= 500000 && feedbackRating <= 999000) {
                                iconColor = "#008000"; // Green in hexadecimal
                                symbol = "StarsIcon";
                            } else if (feedbackRating >= 1000000) {
                                iconColor = "#C0C0C0"; // Silver in hexadecimal
                                symbol = "StarsIcon";
                            }

                            StarFeedback.setImageResource(symbol=="StarIcon"?R.drawable.star_circle:R.drawable.star_circle_outline);
                            int color = Color.parseColor(iconColor);
                            StarFeedback.setColorFilter(color);

                        }
                        else{
                            TextView title = view.findViewById(R.id.IVStar);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore")!=null){
                            ProgressBar circularProgressBar = view.findViewById(R.id.circularProgressBar);

                            int progressValue = receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore").getAsInt();
                            circularProgressBar.setProgress(progressValue);
                        }
                        else{
                            ProgressBar circularProgressBar = view.findViewById(R.id.circularProgressBar);
//                            int progressValue = receivedJsonObject.get("Seller").getAsJsonObject().get("FeedbackScore").getAsInt();
                            circularProgressBar.setProgress(0);
                        }
                    }
                    catch (Exception e){
                    }


                    try{
                        TextView shippingView = view.findViewById(R.id.TV5);
                        if(args.get("shippingInfo").toString().equals("Free")){
                            shippingView.setText("Free Shipping");
                        }
                        else{
                            shippingView.setText("$"+args.get("shippingInfo").toString());
                        }
                    }
                    catch (Exception e){
                        TextView shippingView = view.findViewById(R.id.TV5);
                        shippingView.setText("Free Shipping");
                    }


                    try{


                        if(receivedJsonObject.get("GlobalShipping").getAsString()=="true"){
                            TextView title = view.findViewById(R.id.TV6);
                            title.setText("Yes");
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV6);
                            title.setText("No");
                        }
                    }
                    catch (Exception e){
                    }
                    try{
                        if(receivedJsonObject.get("HandlingTime").getAsString()!=null){
                            TextView title = view.findViewById(R.id.TV7);
                            title.setText(receivedJsonObject.get("HandlingTime").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV7);
                            title.setText("N/A");
                        }
                    }
                    catch (Exception e){
                    }


                    try{
                        if(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ReturnsAccepted")!=null){
                            TextView title = view.findViewById(R.id.TV997);
                            title.setText(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("ReturnsAccepted").getAsString());
                        }
                        else{
                            TextView title = view.findViewById(R.id.TV997);
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
                        if(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("Refund")!=null){
                            TextView title = view.findViewById(R.id.TV9);
                            title.setText(receivedJsonObject.get("ReturnPolicy").getAsJsonObject().get("Refund").getAsString());
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
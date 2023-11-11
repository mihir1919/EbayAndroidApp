package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.design.GridCustomerAdapter;
import com.example.myapplication.model.GridModelList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemCards extends AppCompatActivity {

//    if (intent != null) {
//        String jsonString = intent.getStringExtra("jsonObject");
//
//        if (jsonString != null) {
//            JsonArray receivedJsonObjectParent = new Gson().fromJson(jsonString, JsonArray.class);
//            for(int i=0;i<receivedJsonObjectParent.size();i++){
//                JsonArray receivedJsonObject = receivedJsonObjectParent.get(i).getAsJsonObject().get("itemJSON").getAsJsonArray();
//                Log.d("received Objedct", receivedJsonObject.toString());
//                HashMap<String, String> hashMap = new HashMap<>();
//                try{
//                    hashMap.put("keyword",receivedJsonObject.get(0).getAsJsonObject().get("title").getAsJsonArray().get(0).getAsString());
//                }
//                catch (Exception e){
//                    hashMap.put("keyword", "N/A");
//                }
//                try{
//                    hashMap.put("zipcode",receivedJsonObject.get(0).getAsJsonObject().get("postalCode").getAsJsonArray().get(0).getAsString());
//                }
//                catch (Exception e){
//                    hashMap.put("zipcode", "N/A");
//                }
//                try{
//                    hashMap.put("shippingInfo",receivedJsonObject.get(0).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
//                }
//                catch (Exception e){
//                    hashMap.put("shippingInfo", "N/A");
//                }
//                try{
//                    hashMap.put("price",receivedJsonObject.get(0).getAsJsonObject().get("sellingStatus").getAsJsonArray().get(0).getAsJsonObject().get("currentPrice").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
//                }
//                catch (Exception e){
//                    hashMap.put("price", "N/A");
//                }
//                try{
//                    hashMap.put("photo",receivedJsonObject.get(0).getAsJsonObject().get("galleryURL").getAsJsonArray().get(0).getAsString());
//                }
//                catch (Exception e){
//                    hashMap.put("photo", "N/A");
//                }
//                try{
//                    hashMap.put("condition",receivedJsonObject.get(0).getAsJsonObject().get("condition").getAsJsonArray().get(0).getAsJsonObject().get("conditionDisplayName").getAsJsonArray().get(0).getAsString());
//                }
//                catch (Exception e){
//                    hashMap.put("condition", "N/A");
//                }
//                listOfArrayLists.add(hashMap);
//            }
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cards);
        Intent intent = getIntent();

        List<HashMap<String, String>> listOfArrayLists = new ArrayList<>();

        if(intent.getStringExtra("isWishlist").equals("true")){
                if (intent != null) {
                    String jsonString = intent.getStringExtra("jsonObject");
                    if (jsonString != null) {
                        JsonArray receivedJsonObjectParent = new Gson().fromJson(jsonString, JsonArray.class);
                        for(int i=0;i<receivedJsonObjectParent.size();i++){
                            JsonArray receivedJsonObject = receivedJsonObjectParent.get(i).getAsJsonObject().get("itemJSON").getAsJsonArray();
                            Log.d("received Objedct", receivedJsonObject.toString());
                            HashMap<String, String> hashMap = new HashMap<>();
                            try{
                                hashMap.put("keyword",receivedJsonObject.get(0).getAsJsonObject().get("title").getAsJsonArray().get(0).getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("keyword", "N/A");
                            }
                            try{
                                hashMap.put("zipcode",receivedJsonObject.get(0).getAsJsonObject().get("postalCode").getAsJsonArray().get(0).getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("zipcode", "N/A");
                            }
                            try{
                                hashMap.put("shippingInfo",receivedJsonObject.get(0).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("shippingInfo", "N/A");
                            }
                            try{
                                hashMap.put("price",receivedJsonObject.get(0).getAsJsonObject().get("sellingStatus").getAsJsonArray().get(0).getAsJsonObject().get("currentPrice").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("price", "N/A");
                            }
                            try{
                                hashMap.put("photo",receivedJsonObject.get(0).getAsJsonObject().get("galleryURL").getAsJsonArray().get(0).getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("photo", "N/A");
                            }
                            try{
                                hashMap.put("condition",receivedJsonObject.get(0).getAsJsonObject().get("condition").getAsJsonArray().get(0).getAsJsonObject().get("conditionDisplayName").getAsJsonArray().get(0).getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("condition", "N/A");
                            }
                            listOfArrayLists.add(hashMap);
                        }

                    }
                }
                Log.d("item wishlist array", listOfArrayLists.toString());
        }
        else{
            if (intent != null) {
                String jsonString = intent.getStringExtra("jsonObject");

                if (jsonString != null) {
                    JsonArray receivedJsonObject = new Gson().fromJson(jsonString, JsonArray.class);
                    for(int i=0;i<receivedJsonObject.size();i++){
                        HashMap<String, String> hashMap = new HashMap<>();

                        try{
                            hashMap.put("keyword",receivedJsonObject.get(i).getAsJsonObject().get("title").getAsJsonArray().get(0).getAsString());
                        }
                        catch (Exception e){
                            hashMap.put("keyword", "N/A");
                        }
                        try{
                            hashMap.put("zipcode",receivedJsonObject.get(i).getAsJsonObject().get("postalCode").getAsJsonArray().get(0).getAsString());
                        }
                        catch (Exception e){
                            hashMap.put("zipcode", "N/A");
                        }
                        try{
                            hashMap.put("shippingInfo",receivedJsonObject.get(i).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
                        }
                        catch (Exception e){
                            hashMap.put("shippingInfo", "N/A");
                        }
                        try{
                            hashMap.put("price",receivedJsonObject.get(i).getAsJsonObject().get("sellingStatus").getAsJsonArray().get(0).getAsJsonObject().get("currentPrice").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
                        }
                        catch (Exception e){
                            hashMap.put("price", "N/A");
                        }
                        try{
                            hashMap.put("photo",receivedJsonObject.get(i).getAsJsonObject().get("galleryURL").getAsJsonArray().get(0).getAsString());
                        }
                        catch (Exception e){
                            hashMap.put("photo", "N/A");
                        }
                        try{
                            hashMap.put("condition",receivedJsonObject.get(i).getAsJsonObject().get("condition").getAsJsonArray().get(0).getAsJsonObject().get("conditionDisplayName").getAsJsonArray().get(0).getAsString());
                        }
                        catch (Exception e){
                            hashMap.put("condition", "N/A");
                        }
                        listOfArrayLists.add(hashMap);
                    }

                }
                Log.d("item search array", listOfArrayLists.toString());
            }
        }
//        Log.d("objectcount",jobj.get("findItemsAdvancedResponse").getAsJsonArray().get(0).getAsJsonObject().get("searchResult").getAsJsonArray().get(0).getAsJsonObject().get("@count").getAsString());



        //title, zip, Shipping, New/Used etc, Price

        RecyclerView recyclerView = findViewById(R.id.recycler_view_list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        GridCustomerAdapter adapter = new GridCustomerAdapter(this, new GridModelList().socialMediaList(listOfArrayLists));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    public void addToWishlist(View V){
//        finish();
    }
}
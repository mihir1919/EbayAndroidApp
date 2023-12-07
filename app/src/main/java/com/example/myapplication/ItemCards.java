package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.design.GridCustomerAdapter;
import com.example.myapplication.model.GridModelList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cards);
        Intent intent = getIntent();

        ImageView backBtn = findViewById(R.id.backButtonToSearch);
        backBtn.setOnClickListener(view -> finish());


        List<HashMap<String, String>> listOfArrayLists = new ArrayList<>();
        double totalVal = 0;
        if(intent.getStringExtra("isWishlist").equals("true")){
                if (intent != null) {
                    String jsonString = intent.getStringExtra("jsonObject");
                    if (jsonString != null) {
                        JsonArray receivedJsonObjectParent = new Gson().fromJson(jsonString, JsonArray.class);

                        for(int i=0;i<receivedJsonObjectParent.size();i++){
                            JsonArray receivedJsonObject = receivedJsonObjectParent.get(i).getAsJsonObject().get("itemJSON").getAsJsonArray();
                            Log.d("rektt", receivedJsonObject.toString());
                            HashMap<String, String> hashMap = new HashMap<>();
                            try{
                                hashMap.put("itemId",receivedJsonObject.get(0).getAsJsonObject().get("itemId").getAsJsonArray().get(0).getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("itemId", "N/A");
                            }

                            try{
                                hashMap.put("keyword",receivedJsonObject.get(0).getAsJsonObject().get("title").getAsJsonArray().get(0).getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("keyword", "N/A");
                            }
                            try{
                                hashMap.put("zipcode","Zip:"+receivedJsonObject.get(0).getAsJsonObject().get("postalCode").getAsJsonArray().get(0).getAsString());
                            }
                            catch (Exception e){
                                hashMap.put("zipcode", "N/A");
                            }
                            try{
//                                Log.d("recObj",receivedJsonObject.get(0).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
                                if(receivedJsonObject.get(0).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsDouble()>0.0)
                                {
                                    hashMap.put("shippingInfo",receivedJsonObject.get(0).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
                                }
                                else{
                                    hashMap.put("shippingInfo", "Free");
                                }
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
                            try{
                                hashMap.put("isInWishlist","true");
                            }
                            catch (Exception e){
                                hashMap.put("isInWishlist","true");
                            }
                            try{
                                hashMap.put("CurrentlyInWishlistSection", "true");
                            }
                            catch (Exception e){

                            }

                            try{
                                if(receivedJsonObject.get(0).getAsJsonObject().get("sellingStatus").getAsJsonArray().get(0).getAsJsonObject().get("currentPrice").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsDouble() > 0){
                                    totalVal+=receivedJsonObject.get(0).getAsJsonObject().get("sellingStatus").getAsJsonArray().get(0).getAsJsonObject().get("currentPrice").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsDouble();
                                }
                            }
                            catch(Exception e){

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
                HashMap<String, Boolean> wishlistMap = (HashMap<String, Boolean>) intent.getSerializableExtra("wishlistMap");
                Log.d("checkingWishlist", wishlistMap.toString());
                if (jsonString != null) {
                    JsonArray receivedJsonObject = new Gson().fromJson(jsonString, JsonArray.class);
                    for(int i=0;i<receivedJsonObject.size();i++){
                        HashMap<String, String> hashMap = new HashMap<>();


                        try{
                            hashMap.put("itemId",receivedJsonObject.get(i).getAsJsonObject().get("itemId").getAsJsonArray().get(0).getAsString());
                        }
                        catch (Exception e){
                            hashMap.put("itemId", "N/A");
                        }

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
                            if(receivedJsonObject.get(i).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsDouble()>0.0)
                            {
                                hashMap.put("shippingInfo",receivedJsonObject.get(i).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
//                                hashMap.put("shippingInfo",receivedJsonObject.get(0).getAsJsonObject().get("shippingInfo").getAsJsonArray().get(0).getAsJsonObject().get("shippingServiceCost").getAsJsonArray().get(0).getAsJsonObject().get("__value__").getAsString());
                            }
                            else{
                                hashMap.put("shippingInfo", "Free");
                            }
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
                        try{
                            Log.d("checking item in wishlist",receivedJsonObject.get(i).getAsJsonObject().get("itemId").getAsJsonArray().get(0).getAsString() + "," + wishlistMap.get(receivedJsonObject.get(i).getAsJsonObject().get("itemId").getAsJsonArray().get(0).getAsString()));
                            hashMap.put("isInWishlist",wishlistMap.get(receivedJsonObject.get(i).getAsJsonObject().get("itemId").getAsJsonArray().get(0).getAsString())==true?"true":"false");
                        }
                        catch (Exception e){
                            hashMap.put("isInWishlist","false");
                        }
                        try{
                            hashMap.put("CurrentlyInWishlistSection", "false");
                        }
                        catch (Exception e){

                        }
                        try{
                            hashMap.put("details", receivedJsonObject.get(i).getAsJsonObject().toString());
                        }
                        catch (Exception e){

                        }

                        listOfArrayLists.add(hashMap);
                    }

                }
                Log.d("item search array", listOfArrayLists.toString());
            }
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_view_list);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        recyclerView.setVisibility(View.GONE);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);


        if(intent.getStringExtra("isWishlist").equals("false")){
            findViewById(R.id.text_total_value).setVisibility(View.GONE);
        }
        else{
            findViewById(R.id.text_total_value).setVisibility(View.VISIBLE);
        }
        GridCustomerAdapter adapter = new GridCustomerAdapter(this, new GridModelList().socialMediaList(listOfArrayLists), findViewById(R.id.text_total_value));
        TextView totalShopping =  findViewById(R.id.text_total_value);
        totalShopping.setText(String.valueOf(totalVal));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        new Handler().postDelayed(() -> {
            // After the delay, hide ProgressBar and show RecyclerView
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }, 2000); // 2000 milliseconds delay (adjust as needed)

        adapter.setOnItemClickListener(new GridCustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loadSingleItem(listOfArrayLists.get(position).get("itemId"));
            }
        });
    }

    public void loadSingleItem(String position) {

        // getting a new volley request queue for making new requests
        RequestQueue volleyQueue = Volley.newRequestQueue(this);
        // url of the api through which we get random dog images
        String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/item/"+position;
        Log.d("myurl",url);
        // since the response we get from the api is in JSON, we
        // need to use `JsonObjectRequest` for parsing the
        // request response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                // we are using GET HTTP request method
                Request.Method.GET,
                // url we want to send the HTTP request to
                url,
                // this parameter is used to send a JSON object to the
                // server, since this is not required in our case,
                // we are keeping it `null`
                null,

                // lambda function for handling the case
                // when the HTTP request succeeds
                (Response.Listener<JSONObject>) response -> {
                    // get the image url from the JSON object
                    String dogImageUrl;
                    try {
//                        dogImageUrl = response.getString("message");
                        Log.d("finalAbax", String.valueOf(response));
                        JsonObject jobj = new Gson().fromJson(String.valueOf(response), JsonObject.class);
                        try{
                            Intent intent2 = new Intent(this, ItemDescription.class);
                            intent2.putExtra("jsonObject", jobj.get("Item").getAsJsonObject().toString());
                            startActivity(intent2);
                        }
                        catch (Exception e) {
                            Log.d("exception in count", e.toString());
                        }
                    } catch (Exception e) {
                        Log.d("fucking error", e.toString());
                    }
                },

                // lambda function for handling the case
                // when the HTTP request fails
                (Response.ErrorListener) error -> {
                    // make a Toast telling the user
                    // that something went wrong
                    Toast.makeText(ItemCards.this, "Some error occurred! Cannot fetch dog image", Toast.LENGTH_LONG).show();
                    // log the error message in the error stream
                    Log.e("MainActivity", "loadDogImage error: ${error.localizedMessage}");
                }
        );

        // add the json request object created above
        // to the Volley request queue
        volleyQueue.add(jsonObjectRequest);
    }
}
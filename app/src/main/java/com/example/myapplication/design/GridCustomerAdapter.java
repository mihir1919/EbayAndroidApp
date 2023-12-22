package com.example.myapplication.design;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.R;
import com.example.myapplication.model.GridArrayList;
import com.example.myapplication.model.GridModelList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GridCustomerAdapter extends RecyclerView.Adapter<GridCustomerAdapter.MyHolder> {

    private TextView totalValueTextView;

    public void removeItem(int position) {
        gridArrayLists.remove(position);
        notifyItemRemoved(position);
    }

    private final Context context;
    private  final ArrayList<GridArrayList> gridArrayLists;
    private final LayoutInflater inflater;

    private OnItemClickListener listener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    public GridCustomerAdapter(Context context, ArrayList<GridArrayList> gridArrayLists, TextView totalValueTextView) {
        this.totalValueTextView = totalValueTextView;
        this.context = context;
        this.gridArrayLists = gridArrayLists;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_layout_item_list, parent, false);
        return new MyHolder(view, listener);
    }



    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        GridArrayList arrayList = gridArrayLists.get(position);
        String imageUri = arrayList.getSocialMediaIcon();
        ImageView ivBasicImage = holder.imageView;
        Picasso.get().load(imageUri).into(ivBasicImage);
//        holder.imageView.setImageResource(arrayList.getSocialMediaIcon());
        holder.textView.setText(arrayList.getSocialMediaName());
        holder.zipView.setText(arrayList.getZipCode());
        holder.conditionView.setText(arrayList.getCondition());
        holder.Price.setText(arrayList.getPrice());
        holder.Shipping.setText(arrayList.getShipping());
        holder.Cart.setImageResource(arrayList.getInWishlist()=="true"?R.drawable.cart_off:R.drawable.cart_plus);
//        holder.setClickMethod(position);
    }

    @Override
    public int getItemCount() {
        return gridArrayLists.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;
        private final TextView zipView;

        private final TextView conditionView;
        private final TextView Shipping;
        private final TextView Price;
        private final ImageView Cart;

        private String CurrentlyInWishlistSection;

//        private final ImageView  NotCart;



        public MyHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            conditionView = itemView.findViewById(R.id.condition_text_id);
            imageView = itemView.findViewById(R.id.grid_image_view);
            textView = itemView.findViewById(R.id.grid_text);
            this.zipView = itemView.findViewById(R.id.zip_text_id);
            Shipping = itemView.findViewById(R.id.shipping_text_id);
            Price = itemView.findViewById(R.id.price_text_id);
            Cart = itemView.findViewById(R.id.cartIdView);
//            NotCart = itemView.findViewById(R.id.cartNotIdView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.cartIdView).setOnClickListener(view -> {
                GridArrayList arrayList = gridArrayLists.get(getAdapterPosition());
                Log.d("cartIdTitan", Integer.toString(R.id.cartIdView));
                if (arrayList.getInWishlist() == "true") {
                    if (arrayList.getCurrentlyInWishlistSection() == "false") {
                        gridArrayLists.get(getAdapterPosition()).setInWishlist("false");
                        try {
                            RequestQueue requestQueue = Volley.newRequestQueue(context);
                            String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/deleteWishlistItem/" + gridArrayLists.get(getAdapterPosition()).getItemId().toString();
                            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(view.getContext(), "Item removed from wishlist", Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle errors that occurred during the request
                                }
                            });

                            // Add the request to the queue
                            requestQueue.add(stringRequest);
                        }
                        catch(Exception e){
                            Log.d("wishlist delete", e.toString());
                        }
                        notifyItemChanged(getAdapterPosition());
                    } else {
                        try {
                            RequestQueue requestQueue = Volley.newRequestQueue(context);
                            Log.d("itemIdVolley",gridArrayLists.get(getAdapterPosition()).getItemId());
                            String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/deleteWishlistItem/" + gridArrayLists.get(getAdapterPosition()).getItemId().toString();
                            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle errors that occurred during the request
                                }
                            });

                            // Add the request to the queue
                            requestQueue.add(stringRequest);
                        }
                        catch(Exception e){

                        }

                        try{
                            Double d = Double.parseDouble(gridArrayLists.get(getAdapterPosition()).getPrice());
                            Log.d("deleted item price", d.toString());
                            TextView totalShopping =  totalValueTextView;
                            Log.d("total Item Text", totalShopping.toString());
                            totalShopping.setText(String.valueOf(Double.parseDouble(totalShopping.getText().toString())-d));
                            gridArrayLists.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                        catch (Exception e){
                            Log.d("string price", e.toString());
                        }

                    }

                }
                else{
                    // Assuming gridArrayLists is a list of items and details is a JSONObject

                    // Create a JSONObject containing the required data
                    JSONObject jsonObjectFromString = null;
                    try {
                        jsonObjectFromString = new JSONObject(gridArrayLists.get(getAdapterPosition()).getDetails());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        // Create your request parameters
                        JSONArray newTemp = new JSONArray();
                        JSONObject postData = new JSONObject();
                        JSONObject temp = new JSONObject();
                        newTemp.put(jsonObjectFromString);
                        newTemp.put(0);
                        temp.put("itemId", gridArrayLists.get(getAdapterPosition()).getItemId()); // Assuming itemId is a variable containing the item ID
                        temp.put("itemJSON", newTemp); // Assuming details is a JSONObject
                        postData.put("dataFromPost", temp);
                        // Create your request
//                        String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/postWishlistItem";

                        String url = "http://192.168.1.114:8080/postWishlistItem";
                        Log.d("postData", postData.toString());



                        // Construct a custom request to set Content-Type header
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                                response -> {

                                    try{
                                        Log.d("posstt", postData.toString());
                                        Double d = Double.parseDouble(gridArrayLists.get(getAdapterPosition()).getPrice());
                                        TextView totalShopping =  totalValueTextView;
                                        totalShopping.setText(String.valueOf(Double.parseDouble(totalShopping.getText().toString())+d));

                                        double totalVal = 0;
                                        JSONArray js = new JSONArray();
                                        js.put(postData);
                                                String jsonString = js.toString();
                                                if (jsonString != null) {
                                                    JsonArray receivedJsonObjectParent = new Gson().fromJson(jsonString, JsonArray.class);
                                                    Log.d("jsonArray", receivedJsonObjectParent.toString());
                                                    for(int i=0;i<receivedJsonObjectParent.size();i++){
                                                        JsonArray receivedJsonObject = receivedJsonObjectParent.get(i).getAsJsonObject().get("dataFromPost").getAsJsonObject().get("itemJSON").getAsJsonArray();
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

                                                        gridArrayLists.add(new GridArrayList(hashMap.get("photo"), hashMap.get("keyword"), hashMap.get("zipcode"), hashMap.get("price"), hashMap.get("condition"), hashMap.get("shippingInfo"), hashMap.get("isInWishlist"), hashMap.get("CurrentlyInWishlistSection"),hashMap.get("itemId"), hashMap.get("details")));
                                                    }

                                                }
                                            Log.d("item wishlist array", gridArrayLists.toString());
//                                         R.id.cartIdView
                                        arrayList.setInWishlist("true");
                                        Toast.makeText(view.getContext(), "Item added to wishlist", Toast.LENGTH_SHORT).show();
                                        notifyItemChanged(getAdapterPosition());
                                    }
                                    catch (Exception e){
                                        Log.d("string price", e.toString());
                                    }

                                    Log.d("response is thiiss", response.toString());
                                },
                                error -> {
                                    Log.e("error is thiiss", error.toString());
                                }
                        ) {
                            @Override
                            public Map<String, String> getHeaders() {
                                Map<String, String> headers = new HashMap<>();
                                headers.put("Content-Type", "application/json"); // Add the Content-Type header
                                return headers;
                            }
                        };

                        // Instantiate the RequestQueue and add the request to it
                        RequestQueue requestQueue = Volley.newRequestQueue(context); // 'context' is your activity or application context
                        requestQueue.add(jsonObjectRequest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });

//            itemView.findViewById(R.id.cartIdView).setOnClickListener(view -> {
//                Log.d("cartIdTitanNot", Integer.toString(R.id.cartIdView));
//                gridArrayLists.remove(getAdapterPosition());
//                notifyItemRemoved(getAdapterPosition());
//            });

        }
    }
}

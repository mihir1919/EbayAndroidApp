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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
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
                    JSONObject postData = new JSONObject();
                    try {

                        JSONObject jsonObjectFromString = new JSONObject(gridArrayLists.get(getAdapterPosition()).getDetails());
                        postData.put("itemId", gridArrayLists.get(getAdapterPosition()).getItemId()); // Assuming itemId is a variable containing the item ID
                        postData.put("itemJSON", jsonObjectFromString); // Assuming details is a JSONObject
                        String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/postWishlistItem";

                        Log.d("postData", postData.toString());
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                                response -> {
                                    Log.d("error in posting", response.toString());
                                },
                                error -> {
                                    Log.d("error in posting", error.toString());
                                }
                        );

                        RequestQueue requestQueue = Volley.newRequestQueue(context); // 'context' is your activity or application context
                        requestQueue.add(jsonObjectRequest);
                    } catch (JSONException e) {
                        Log.d("error in posting",e.toString());
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

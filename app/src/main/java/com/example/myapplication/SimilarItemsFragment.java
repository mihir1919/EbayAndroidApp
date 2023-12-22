package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.YourItemModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimilarItemsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<YourItemModel> itemList; // Replace YourItemModel with your actual item model class

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_similar, container, false);
        Bundle args = getArguments();

        try{

            FrameLayout cart = view.findViewById(R.id.cartInDesc4);

            try {
                HashMap<String, Boolean> wishlistMap = (HashMap<String, Boolean>) args.getSerializable("wishlistMap");
                if (wishlistMap.get(args.get("itemIdSingle")) == true) {
                    ImageView cartView = view.findViewById(R.id.idCartImg4);
                    cartView.setImageResource(R.drawable.cart_off);
                }
                else{
                    ImageView cartView = view.findViewById(R.id.idCartImg4);
                    cartView.setImageResource(R.drawable.cart_plus);
                }
            }
            catch (Exception e){

            }

            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        HashMap<String, Boolean> wishlistMap = (HashMap<String, Boolean>) args.getSerializable("wishlistMap");

                        if (wishlistMap != null && args.get("itemIdSingle") != null) {
                            String itemId = args.get("itemIdSingle").toString();
                            Boolean itemExistsInWishlist = wishlistMap.get(itemId);

                            if (itemExistsInWishlist != null && itemExistsInWishlist) {
                                ImageView cartView = view.findViewById(R.id.idCartImg4);
                                cartView.setImageResource(R.drawable.cart_plus);
                                wishlistMap.put(itemId, false);
                                Toast.makeText(getContext(), "Item removed from wishlist", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("melon", itemId);
                                ImageView cartView = view.findViewById(R.id.idCartImg4);
                                cartView.setImageResource(R.drawable.cart_off);
                                wishlistMap.put(itemId, true);
                                Toast.makeText(getContext(), "Item added to wishlist", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Log.d("lund", "wishlistMap or itemIdSingle is null");
                        }
                    } catch (Exception e) {
                        Log.d("lund", e.toString());
                    }

                }
            });
        }
        catch (Exception e){
        }


        recyclerView = view.findViewById(R.id.recyclerViewSimilarItems);

        // Initialize your item list (Replace this with your data retrieval logic)
        List<YourItemModel> itemList = new ArrayList<>();

        try{
            String stringFromArgs = args.get("jsonObjectStringSimilar").toString();
            JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(stringFromArgs), JsonObject.class);
            JsonArray arr = receivedJsonObject.get("getSimilarItemsResponse").getAsJsonObject().get("itemRecommendations").getAsJsonObject().get("item").getAsJsonArray();
            // Initialize your item list (Replace this with your data retrieval logic)
            for (int i = 0; i < arr.size(); i++) {
                YourItemModel item = new YourItemModel(
                        "Product " + arr.get(i).getAsJsonObject().get("title").getAsString(),
                        "$" + arr.get(i).getAsJsonObject().get("shippingCost").getAsJsonObject().get("__value__").getAsString(),
                        simplifyDuration(arr.get(i).getAsJsonObject().get("timeLeft").getAsString()),
                        "$" + arr.get(i).getAsJsonObject().get("buyItNowPrice").getAsJsonObject().get("__value__").getAsString(),
                        arr.get(i).getAsJsonObject().get("imageURL").getAsString()
                );
                itemList.add(item);
            }
        }
        catch (Exception e){
            for (int i = 1; i <= 20; i++) {
                // Creating dummy data for each item
                YourItemModel item = new YourItemModel(
                        "Product " + i,
                        "$" + (i * 5),
                        i + " days left",
                        "Zip " + i,
                        "https://picsum.photos/200"
                );
                itemList.add(item);
            }
        }


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        // Find your spinners and set up functionality
        Spinner spinnerSortBy = view.findViewById(R.id.spinnerSortBy);
        Spinner spinnerSortOrder = view.findViewById(R.id.spinnerSortOrder);


        spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Enable/disable second spinner based on selection in the first spinner
                if (position == 0) { // "Default" selected
                    spinnerSortOrder.setEnabled(false);
                } else {
                    spinnerSortOrder.setEnabled(true);
                    if (itemList != null && itemList.size() > 0) {

                        if(position == 0){
                            try{
                                String stringFromArgs = args.get("jsonObjectStringSimilar").toString();
                                JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(stringFromArgs), JsonObject.class);
                                JsonArray arr = receivedJsonObject.get("getSimilarItemsResponse").getAsJsonObject().get("itemRecommendations").getAsJsonObject().get("item").getAsJsonArray();
                                // Initialize your item list (Replace this with your data retrieval logic)
                                for (int i = 0; i < arr.size(); i++) {
                                    YourItemModel item = new YourItemModel(
                                            "Product " + arr.get(i).getAsJsonObject().get("title").getAsString(),
                                            "$" + arr.get(i).getAsJsonObject().get("shippingCost").getAsJsonObject().get("__value__").getAsString(),
                                            simplifyDuration(arr.get(i).getAsJsonObject().get("timeLeft").getAsString()),
                                            "$" + arr.get(i).getAsJsonObject().get("buyItNowPrice").getAsJsonObject().get("__value__").getAsString(),
                                            arr.get(i).getAsJsonObject().get("imageURL").getAsString()
                                    );
                                    itemList.add(item);
                                }
                            }
                            catch (Exception e){
                                for (int i = 1; i <= 20; i++) {
                                    // Creating dummy data for each item
                                    YourItemModel item = new YourItemModel(
                                            "Product " + i,
                                            "$" + (i * 5),
                                            i + " days left",
                                            "Zip " + i,
                                            "https://picsum.photos/200"
                                    );
                                    itemList.add(item);
                                }
                            }
                        }
                        else if(position == 1){
                            if ("Ascending".equals(spinnerSortOrder.getSelectedItem())) {
                                Collections.sort(itemList, new Comparator<YourItemModel>() {
                                    @Override
                                    public int compare(YourItemModel o1, YourItemModel o2) {
                                        String title1 = o1.getTitle().replaceAll("\\s+", "").trim();
                                        String title2 = o2.getTitle().replaceAll("\\s+", "").trim();
                                        return title1.compareTo(title2);
                                    }
                                });
                            } else {
                                Collections.sort(itemList, new Comparator<YourItemModel>() {
                                    @Override
                                    public int compare(YourItemModel o1, YourItemModel o2) {
                                        String title1 = o1.getTitle().replaceAll("\\s+", "").trim();
                                        String title2 = o2.getTitle().replaceAll("\\s+", "").trim();
                                        return title2.compareTo(title1);
                                    }
                                });
                            }

                        }
                        else if(position == 2){
                            if(spinnerSortOrder.getSelectedItem().equals("Ascending")) {
                                Collections.sort(itemList, new Comparator<YourItemModel>() {
                                    @Override
                                    public int compare(YourItemModel o1, YourItemModel o2) {
                                        try {
                                            double price1 = Double.parseDouble(o1.getPrice());
                                            double price2 = Double.parseDouble(o2.getPrice());

                                            // Compare the parsed prices
                                            return Double.compare(price1, price2);
                                        } catch (NumberFormatException e) {
                                            // Handle the case where parsing fails for one or both items
                                            e.printStackTrace(); // Log the error for debugging purposes

                                            // Decide how to handle this situation, possibly returning a default value
                                            // For example, if you want items with invalid prices to be considered equal:
                                            return 0;
                                        }
                                    }

                                });
                            }
                            else {
                                Collections.sort(itemList, new Comparator<YourItemModel>() {
                                    @Override
                                    public int compare(YourItemModel o1, YourItemModel o2) {
                                        try {
                                            double price1 = Double.parseDouble(o1.getPrice());
                                            double price2 = Double.parseDouble(o2.getPrice());

                                            // Compare the parsed prices
                                            return Double.compare(price2, price1);
                                        } catch (NumberFormatException e) {
                                            // Handle the case where parsing fails for one or both items
                                            e.printStackTrace(); // Log the error for debugging purposes

                                            // Decide how to handle this situation, possibly returning a default value
                                            // For example, if you want items with invalid prices to be considered equal:
                                            return 0;
                                        }
                                    }

                                });
                            }
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                        else{
                            if(spinnerSortOrder.getSelectedItem().equals("Ascending")) {
                                Collections.sort(itemList, new Comparator<YourItemModel>() {
                                    @Override
                                    public int compare(YourItemModel o1, YourItemModel o2) {
                                        return o1.getTimeLeft().compareTo(o2.getTimeLeft());
                                    }
                                });
                            }
                            else {
                                Collections.sort(itemList, new Comparator<YourItemModel>() {
                                    @Override
                                    public int compare(YourItemModel o1, YourItemModel o2) {
                                        return o2.getTimeLeft().compareTo(o1.getTimeLeft());
                                    }
                                });
                            }

                        }
                    }

                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected if needed
            }
        });


        spinnerSortOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Enable/disable second spinner based on selection in the first spinner
                if (position == 0) { // "Default" selected

                    String getVal = spinnerSortBy.getSelectedItem().toString();

                    if(getVal == "Default"){
                        try{
                            String stringFromArgs = args.get("jsonObjectStringSimilar").toString();
                            JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(stringFromArgs), JsonObject.class);
                            JsonArray arr = receivedJsonObject.get("getSimilarItemsResponse").getAsJsonObject().get("itemRecommendations").getAsJsonObject().get("item").getAsJsonArray();
                            // Initialize your item list (Replace this with your data retrieval logic)
                            for (int i = 0; i < arr.size(); i++) {
                                YourItemModel item = new YourItemModel(
                                        "Product " + arr.get(i).getAsJsonObject().get("title").getAsString(),
                                        "$" + arr.get(i).getAsJsonObject().get("shippingCost").getAsJsonObject().get("__value__").getAsString(),
                                        simplifyDuration(arr.get(i).getAsJsonObject().get("timeLeft").getAsString()),
                                        "$" + arr.get(i).getAsJsonObject().get("buyItNowPrice").getAsJsonObject().get("__value__").getAsString(),
                                        arr.get(i).getAsJsonObject().get("imageURL").getAsString()
                                );
                                itemList.add(item);
                            }
                        }
                        catch (Exception e){
                            for (int i = 1; i <= 20; i++) {
                                // Creating dummy data for each item
                                YourItemModel item = new YourItemModel(
                                        "Product " + i,
                                        "$" + (i * 5),
                                        i + " days left",
                                        "Zip " + i,
                                        "https://picsum.photos/200"
                                );
                                itemList.add(item);
                            }
                        }
                    }
                    else if(getVal == "Name"){
                        Collections.sort(itemList, new Comparator<YourItemModel>() {
                                @Override
                                public int compare(YourItemModel o1, YourItemModel o2) {
                                    String title1 = o1.getTitle().replaceAll("\\s+", "").trim();
                                    String title2 = o2.getTitle().replaceAll("\\s+", "").trim();
                                    return title1.compareTo(title2);
                                }
                            });
                    }
                    else if(getVal == "Price"){
                        Collections.sort(itemList, new Comparator<YourItemModel>() {
                            @Override
                            public int compare(YourItemModel o1, YourItemModel o2) {
                                try {
                                    double price1 = Double.parseDouble(o1.getPrice());
                                    double price2 = Double.parseDouble(o2.getPrice());

                                    // Compare the parsed prices
                                    return Double.compare(price1, price2);
                                } catch (NumberFormatException e) {
                                    // Handle the case where parsing fails for one or both items
                                    e.printStackTrace(); // Log the error for debugging purposes

                                    // Decide how to handle this situation, possibly returning a default value
                                    // For example, if you want items with invalid prices to be considered equal:
                                    return 0;
                                }
                            }

                        });
                    }
                    else{
                        Collections.sort(itemList, new Comparator<YourItemModel>() {
                            @Override
                            public int compare(YourItemModel o1, YourItemModel o2) {
                                return o1.getTimeLeft().compareTo(o2.getTimeLeft());
                            }
                        });
                    }



                }
                else {
                    spinnerSortOrder.setEnabled(true);
                    String getVal = spinnerSortBy.getSelectedItem().toString();

                    if(getVal == "Default"){
                        try{
                            String stringFromArgs = args.get("jsonObjectStringSimilar").toString();
                            JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(stringFromArgs), JsonObject.class);
                            JsonArray arr = receivedJsonObject.get("getSimilarItemsResponse").getAsJsonObject().get("itemRecommendations").getAsJsonObject().get("item").getAsJsonArray();
                            // Initialize your item list (Replace this with your data retrieval logic)
                            for (int i = 0; i < arr.size(); i++) {
                                YourItemModel item = new YourItemModel(
                                        "Product " + arr.get(i).getAsJsonObject().get("title").getAsString(),
                                        "$" + arr.get(i).getAsJsonObject().get("shippingCost").getAsJsonObject().get("__value__").getAsString(),
                                        simplifyDuration(arr.get(i).getAsJsonObject().get("timeLeft").getAsString()),
                                        "$" + arr.get(i).getAsJsonObject().get("buyItNowPrice").getAsJsonObject().get("__value__").getAsString(),
                                        arr.get(i).getAsJsonObject().get("imageURL").getAsString()
                                );
                                itemList.add(item);
                            }
                        }
                        catch (Exception e){
                            for (int i = 1; i <= 20; i++) {
                                // Creating dummy data for each item
                                YourItemModel item = new YourItemModel(
                                        "Product " + i,
                                        "$" + (i * 5),
                                        i + " days left",
                                        "Zip " + i,
                                        "https://picsum.photos/200"
                                );
                                itemList.add(item);
                            }
                        }
                    }
                    else if(getVal == "Name"){
                        Collections.sort(itemList, new Comparator<YourItemModel>() {
                            @Override
                            public int compare(YourItemModel o1, YourItemModel o2) {
                                String title1 = o1.getTitle().replaceAll("\\s+", "").trim();
                                String title2 = o2.getTitle().replaceAll("\\s+", "").trim();
                                return title2.compareTo(title1);
                            }
                        });
                    }
                    else if(getVal == "Price"){
                        Collections.sort(itemList, new Comparator<YourItemModel>() {
                            @Override
                            public int compare(YourItemModel o1, YourItemModel o2) {
                                try {
                                    double price1 = Double.parseDouble(o1.getPrice());
                                    double price2 = Double.parseDouble(o2.getPrice());

                                    // Compare the parsed prices
                                    return Double.compare(price2, price1);
                                } catch (NumberFormatException e) {
                                    // Handle the case where parsing fails for one or both items
                                    e.printStackTrace(); // Log the error for debugging purposes

                                    // Decide how to handle this situation, possibly returning a default value
                                    // For example, if you want items with invalid prices to be considered equal:
                                    return 0;
                                }
                            }

                        });
                    }
                    else{
                        Collections.sort(itemList, new Comparator<YourItemModel>() {
                            @Override
                            public int compare(YourItemModel o1, YourItemModel o2) {
                                return o2.getTimeLeft().compareTo(o1.getTimeLeft());
                            }
                        });
                    }

                }
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected if needed
            }
        });




        return view;
    }

    public  String simplifyDuration(String durationStr) {
        Pattern pattern = Pattern.compile("P(\\d+)D");
        Matcher matcher = pattern.matcher(durationStr);

        if (matcher.find()) {
            int days = Integer.parseInt(matcher.group(1));
            return days + " days left";
        }

        return "Invalid duration format";
    }

    // Create a ViewHolder class for your RecyclerView items (Replace YourItemViewHolder with your actual ViewHolder)
    private class YourItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewPrice;
        TextView textViewTimeLeft;
        TextView textViewZipcode;
        ImageView imageView;

        YourItemViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewTimeLeft = itemView.findViewById(R.id.textViewTimeLeft);
            textViewZipcode = itemView.findViewById(R.id.textViewZipcode);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void bind(YourItemModel item) {
            // Bind data from item to your ViewHolder components
            textViewTitle.setText(item.getTitle());
            textViewPrice.setText(item.getPrice());
            textViewTimeLeft.setText(item.getTimeLeft());
            textViewZipcode.setText(item.getZipcode());

            // Load image using your image loading library (Glide, Picasso, etc.)
            // Example using Picasso (make sure you've added the library to your dependencies)
            Picasso.get().load(item.getImageUrl()).into(imageView);
        }
    }

    // Create an adapter for your RecyclerView (Replace YourItemModel with your actual item model class)
    private class RecyclerViewAdapter extends RecyclerView.Adapter<YourItemViewHolder> {

        private List<YourItemModel> itemList;

        RecyclerViewAdapter(List<YourItemModel> itemList) {
            this.itemList = itemList;
        }

        @NonNull
        @Override
        public YourItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout, parent, false);
            return new YourItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull YourItemViewHolder holder, int position) {
            YourItemModel currentItem = itemList.get(position);
            holder.bind(currentItem);
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }
}


package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.YourItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SimilarItemsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<YourItemModel> itemList; // Replace YourItemModel with your actual item model class

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_similar, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewSimilarItems);

        // Initialize your item list (Replace this with your data retrieval logic)
        List<YourItemModel> itemList = new ArrayList<>();
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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected if needed
            }
        });

        return view;
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

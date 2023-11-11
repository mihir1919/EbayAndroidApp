package com.example.myapplication.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.GridArrayList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridCustomerAdapter extends RecyclerView.Adapter<GridCustomerAdapter.MyHolder> {


    private final Context context;
    private  final ArrayList<GridArrayList> gridArrayLists;
    private final LayoutInflater inflater;

    public GridCustomerAdapter(Context context, ArrayList<GridArrayList> gridArrayLists) {
        this.context = context;
        this.gridArrayLists = gridArrayLists;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_layout_item_list, parent, false);
        return new MyHolder(view);
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
        holder.setClickMethod(position);
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


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            conditionView = itemView.findViewById(R.id.condition_text_id);
            imageView = itemView.findViewById(R.id.grid_image_view);
            textView = itemView.findViewById(R.id.grid_text);
            this.zipView = itemView.findViewById(R.id.zip_text_id);
            Shipping = itemView.findViewById(R.id.shipping_text_id);
            Price = itemView.findViewById(R.id.price_text_id);

            itemView.findViewById(R.id.cartIdView).setOnClickListener(view -> {
                gridArrayLists.remove(getAdapterPosition());

            });

        }

        public void setClickMethod(int position){
            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    GridArrayList list = gridArrayLists.get(position);
                    Toast.makeText(context, "Socail Media Name ... "+list.getSocialMediaName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

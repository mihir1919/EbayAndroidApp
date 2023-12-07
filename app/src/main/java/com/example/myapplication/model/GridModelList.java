package com.example.myapplication.model;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridModelList {
    public ArrayList<GridArrayList> socialMediaList(List<HashMap<String, String>> listOfArrayLists){
        ArrayList<GridArrayList> list = new ArrayList<>();

        for (int i = 0; i < listOfArrayLists.size(); i++) {
//            list.add(new GridArrayList(R.drawable.shopping, hashMap.get("keyword"), hashMap.get("zipcode"), hashMap.get("price"), hashMap.get("condition"), hashMap.get("shippingInfo")));
            list.add(new GridArrayList(listOfArrayLists.get(i).get("photo"), listOfArrayLists.get(i).get("keyword"), listOfArrayLists.get(i).get("zipcode"), listOfArrayLists.get(i).get("price"), listOfArrayLists.get(i).get("condition"), listOfArrayLists.get(i).get("shippingInfo"), listOfArrayLists.get(i).get("isInWishlist"), listOfArrayLists.get(i).get("CurrentlyInWishlistSection"),listOfArrayLists.get(i).get("itemId"), listOfArrayLists.get(i).get("details")));
        }
        return list;
    }


}

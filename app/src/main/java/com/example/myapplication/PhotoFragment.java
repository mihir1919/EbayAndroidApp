package com.example.myapplication;

import static com.example.myapplication.R.id.buttonLinearLayout;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.design.ImagePagerAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class PhotoFragment extends Fragment {

    JsonObject receivedJsonObject = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle args = getArguments();
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/getImages/";

        String jsonString22 = args.getString("jsonObject");
        JsonObject receivedJsonObject22 = new Gson().fromJson(String.valueOf(jsonString22), JsonObject.class);

        try{

            if(receivedJsonObject22.get("Title").getAsString()!=null){
                url+=receivedJsonObject22.get("Title").getAsString();
            }
            else{
                url+="harrypotter";
            }
        }
        catch (Exception e){

        }


//        String url = "https://dummyjson.com/products/1";

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        processJsonObject(response);
                        receivedJsonObject = new Gson().fromJson(String.valueOf(response), JsonObject.class);
                        try{
//                    @Override
//                    public void onResponse(JSONObject response) {
////                        processJsonObject();
////                        receivedJsonObject = new Gson().fromJson(String.valueOf("{\"kind\":\"customsearch#search\",\"url\":{\"type\":\"application/json\",\"template\":\"https://www.googleapis.com/customsearch/v1?q={searchTerms}&num={count?}&start={startIndex?}&lr={language?}&safe={safe?}&cx={cx?}&sort={sort?}&filter={filter?}&gl={gl?}&cr={cr?}&googlehost={googleHost?}&c2coff={disableCnTwTranslation?}&hq={hq?}&hl={hl?}&siteSearch={siteSearch?}&siteSearchFilter={siteSearchFilter?}&exactTerms={exactTerms?}&excludeTerms={excludeTerms?}&linkSite={linkSite?}&orTerms={orTerms?}&relatedSite={relatedSite?}&dateRestrict={dateRestrict?}&lowRange={lowRange?}&highRange={highRange?}&searchType={searchType}&fileType={fileType?}&rights={rights?}&imgSize={imgSize?}&imgType={imgType?}&imgColorType={imgColorType?}&imgDominantColor={imgDominantColor?}&alt=json\"},\"queries\":{\"request\":[{\"title\":\"Google Custom Search - iphone\",\"totalResults\":\"5910000000\",\"searchTerms\":\"iphone\",\"count\":8,\"startIndex\":1,\"inputEncoding\":\"utf8\",\"outputEncoding\":\"utf8\",\"safe\":\"off\",\"cx\":\"d63bff61d60824248\",\"searchType\":\"image\",\"imgSize\":\"huge\"}],\"nextPage\":[{\"title\":\"Google Custom Search - iphone\",\"totalResults\":\"5910000000\",\"searchTerms\":\"iphone\",\"count\":8,\"startIndex\":9,\"inputEncoding\":\"utf8\",\"outputEncoding\":\"utf8\",\"safe\":\"off\",\"cx\":\"d63bff61d60824248\",\"searchType\":\"image\",\"imgSize\":\"huge\"}]},\"context\":{\"title\":\"mihir\"},\"searchInformation\":{\"searchTime\":0.685803,\"formattedSearchTime\":\"0.69\",\"totalResults\":\"5910000000\",\"formattedTotalResults\":\"5,910,000,000\"},\"items\":[{\"kind\":\"customsearch#result\",\"title\":\"Apple introduces iPhone 13 and iPhone 13 mini - Apple\",\"htmlTitle\":\"Apple introduces iPhone 13 and iPhone 13 mini - Apple\",\"link\":\"https://www.apple.com/newsroom/images/product/iphone/standard/Apple_iphone13_hero_09142021_inline.jpg.slideshow-xlarge_2x.jpg\",\"displayLink\":\"www.apple.com\",\"snippet\":\"Apple introduces iPhone 13 and iPhone 13 mini - Apple\",\"htmlSnippet\":\"Apple introduces iPhone 13 and iPhone 13 mini - Apple\",\"mime\":\"image/jpeg\",\"fileFormat\":\"image/jpeg\",\"image\":{\"contextLink\":\"https://www.apple.com/newsroom/2021/09/apple-introduces-iphone-13-and-iphone-13-mini/\",\"height\":5484,\"width\":3918,\"byteSize\":1064458,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgWD21qujPsw8HxWvm_pix6GpDZb3Mwz74yEdQgNBJGtI0cLnoVFFrCQ&s\",\"thumbnailHeight\":150,\"thumbnailWidth\":107}},{\"kind\":\"customsearch#result\",\"title\":\"iPhone 15 Pro review: Coming from iPhone 12 Pro or earlier? This ...\",\"htmlTitle\":\"iPhone 15 Pro review: Coming from iPhone 12 Pro or earlier? This ...\",\"link\":\"https://www.zdnet.com/a/img/2023/10/04/19fd871b-9427-4bd7-919d-323038e6002e/iphone-15-pro-in-front-apple-store.jpg\",\"displayLink\":\"www.zdnet.com\",\"snippet\":\"iPhone 15 Pro review: Coming from iPhone 12 Pro or earlier? This ...\",\"htmlSnippet\":\"iPhone 15 Pro review: Coming from iPhone 12 Pro or earlier? This ...\",\"mime\":\"image/jpeg\",\"fileFormat\":\"image/jpeg\",\"image\":{\"contextLink\":\"https://www.zdnet.com/article/iphone-15-pro-review/\",\"height\":1536,\"width\":2048,\"byteSize\":635764,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShKkjFi_iZIFTBOpTfNIspSLZfhE-R4DGg_vk6bA3V7MeRKtRGX9d3IQY&s\",\"thumbnailHeight\":113,\"thumbnailWidth\":150}},{\"kind\":\"customsearch#result\",\"title\":\"iPhone 14 Plus available in stores Friday - Apple\",\"htmlTitle\":\"iPhone 14 Plus available in stores Friday - Apple\",\"link\":\"https://www.apple.com/newsroom/images/product/iphone/standard/Apple-iPhone-14-Plus-blue-hero_inline.jpg.slideshow-medium_2x.jpg\",\"displayLink\":\"www.apple.com\",\"snippet\":\"iPhone 14 Plus available in stores Friday - Apple\",\"htmlSnippet\":\"iPhone 14 Plus available in stores Friday - Apple\",\"mime\":\"image/jpeg\",\"fileFormat\":\"image/jpeg\",\"image\":{\"contextLink\":\"https://www.apple.com/newsroom/2022/10/iphone-14-plus-available-in-stores-friday/\",\"height\":2688,\"width\":1843,\"byteSize\":518511,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS484RGxCw5pXLo5JFt0VgQX4JexukN9Q_b2z8rV3csvKJdQtHGiDskiUk&s\",\"thumbnailHeight\":150,\"thumbnailWidth\":103}},{\"kind\":\"customsearch#result\",\"title\":\"Apple iPhone 13 5G 128GB (Unlocked) Midnight MMM63LL/A - Best Buy\",\"htmlTitle\":\"Apple iPhone 13 5G 128GB (Unlocked) Midnight MMM63LL/A - Best Buy\",\"link\":\"https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6417/6417788_sd.jpg\",\"displayLink\":\"www.bestbuy.com\",\"snippet\":\"Apple iPhone 13 5G 128GB (Unlocked) Midnight MMM63LL/A - Best Buy\",\"htmlSnippet\":\"Apple iPhone 13 5G 128GB (Unlocked) Midnight MMM63LL/A - Best Buy\",\"mime\":\"image/jpeg\",\"fileFormat\":\"image/jpeg\",\"image\":{\"contextLink\":\"https://www.bestbuy.com/site/apple-iphone-13-5g-128gb-unlocked-midnight/6417788.p?skuId=6417788\",\"height\":2829,\"width\":2082,\"byteSize\":1531579,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuos7K4jbs7ihjW_4Y8GWxKzyw9zXDT4KoBn8DOUk-qFaiGqlG_yz3Bgs&s\",\"thumbnailHeight\":150,\"thumbnailWidth\":110}},{\"kind\":\"customsearch#result\",\"title\":\"Buy iPhone 15 Pro 128GB Blue Titanium T-Mobile - Apple\",\"htmlTitle\":\"Buy iPhone 15 Pro 128GB Blue Titanium T-Mobile - Apple\",\"link\":\"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch-bluetitanium?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692846360609\",\"displayLink\":\"www.apple.com\",\"snippet\":\"Buy iPhone 15 Pro 128GB Blue Titanium T-Mobile - Apple\",\"htmlSnippet\":\"Buy iPhone 15 Pro 128GB Blue Titanium T-Mobile - Apple\",\"mime\":\"image/\",\"fileFormat\":\"image/\",\"image\":{\"contextLink\":\"https://www.apple.com/shop/buy-iphone/iphone-15-pro/6.1-inch-display-128gb-blue-titanium-t-mobile\",\"height\":2880,\"width\":5120,\"byteSize\":463754,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGOvuy2PMgZlWPKUkQpkD5Mozkw8DFrsIkHJ63cvE9cNMrJ3WYT7M5RCE&s\",\"thumbnailHeight\":84,\"thumbnailWidth\":150}},{\"kind\":\"customsearch#result\",\"title\":\"iPhone 14 review: More of a good thing | CNN Underscored\",\"htmlTitle\":\"iPhone 14 review: More of a good thing | CNN Underscored\",\"link\":\"https://media.cnn.com/api/v1/images/stellar/prod/220920215114-iphone-14-lead.jpg?c=original\",\"displayLink\":\"www.cnn.com\",\"snippet\":\"iPhone 14 review: More of a good thing | CNN Underscored\",\"htmlSnippet\":\"iPhone 14 review: More of a good thing | CNN Underscored\",\"mime\":\"image/jpeg\",\"fileFormat\":\"image/jpeg\",\"image\":{\"contextLink\":\"https://www.cnn.com/cnn-underscored/reviews/iphone-14\",\"height\":2268,\"width\":4032,\"byteSize\":463120,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2PhRlbzuoLazveeHmB6GskQYuXhZRIOoEJQ4KsGHfVkHHFOR87LFOV-s&s\",\"thumbnailHeight\":84,\"thumbnailWidth\":150}},{\"kind\":\"customsearch#result\",\"title\":\"Buy iPhone 15 Pro Max 512GB Blue Titanium AT&T - Apple\",\"htmlTitle\":\"Buy iPhone 15 Pro Max 512GB Blue Titanium AT&T - Apple\",\"link\":\"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-7inch-bluetitanium?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692845699311\",\"displayLink\":\"www.apple.com\",\"snippet\":\"Buy iPhone 15 Pro Max 512GB Blue Titanium AT&T - Apple\",\"htmlSnippet\":\"Buy iPhone 15 Pro Max 512GB Blue Titanium AT&T - Apple\",\"mime\":\"image/\",\"fileFormat\":\"image/\",\"image\":{\"contextLink\":\"https://www.apple.com/shop/buy-iphone/iphone-15-pro/6.7-inch-display-512gb-blue-titanium-att\",\"height\":2880,\"width\":5120,\"byteSize\":534732,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6JV5_49pwQlckzcApFvCWBaL2Rv_Mx-iL2ZmyFAVtGBJS3afhvmy0v7Y&s\",\"thumbnailHeight\":84,\"thumbnailWidth\":150}},{\"kind\":\"customsearch#result\",\"title\":\"AT&T Apple iPhone 15 Pro Max 256GB Natural Titanium - Walmart.com\",\"htmlTitle\":\"AT&T Apple iPhone 15 Pro Max 256GB Natural Titanium - Walmart.com\",\"link\":\"https://i5.walmartimages.com/seo/AT-T-Apple-iPhone-15-Pro-Max-256GB-Natural-Titanium_43b58742-b6ad-491a-a795-e6c2eb6bcc6f.d6fa026332e1941b129f607c592844ab.jpeg\",\"displayLink\":\"www.walmart.com\",\"snippet\":\"AT&T Apple iPhone 15 Pro Max 256GB Natural Titanium - Walmart.com\",\"htmlSnippet\":\"AT&T Apple iPhone 15 Pro Max 256GB Natural Titanium - Walmart.com\",\"mime\":\"image/jpeg\",\"fileFormat\":\"image/jpeg\",\"image\":{\"contextLink\":\"https://www.walmart.com/ip/AT-T-Apple-iPhone-15-Pro-Max-256GB-Natural-Titanium-300-eGift-Card-Offer/5054919388\",\"height\":4000,\"width\":4000,\"byteSize\":638212,\"thumbnailLink\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdZcTQIrTQfj9UqAyWKCTiM3AUf5S3xJrSMYZFNttvyZCeCk5qQd-LVdnA&s\",\"thumbnailHeight\":150,\"thumbnailWidth\":150}}]}"), JsonObject.class);
//                        receivedJsonObject = new Gson().fromJson(String.valueOf(response), JsonObject.class);
//
//                        Bundle args = getArguments();
//                        Log.d("rekferkker", receivedJsonObject.toString());
//                        try{
                            if (args != null) {
//                                String jsonString = args.getString("jsonObject");
//                                JsonObject receivedJsonObject = new Gson().fromJson(String.valueOf(jsonString), JsonObject.class);
                            if (receivedJsonObject != null) {
                                try{
                                    if(receivedJsonObject.get("items").getAsJsonArray()!=null){

                                        String[] imageUrls = new String[receivedJsonObject.get("items").getAsJsonArray().size()];

                                        for (int i = 0; i < imageUrls.length; i++) {
                                            imageUrls[i] = receivedJsonObject.get("items").getAsJsonArray().get(i).getAsJsonObject().get("link").getAsString();
                                        }

//                            for(int i=0;i<receivedJsonObject.get("items").getAsJsonArray().size();i++){
//
//                            }
//                            ViewPager viewPager = view.findViewById(R.id.viewPager);
//                                        LinearLayout linearLayout = view.findViewById(R.id.buttonLinearLayout);
//
//                                        for (String imageUrl : imageUrls) {
//                                            ImageView imageView = new ImageView(getContext());
//                                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//                                            imageView.setLayoutParams(layoutParams);
//
//                                            // Set adjustViewBounds to true
//                                            imageView.setAdjustViewBounds(true);
//
//                                            // Load images into ImageView using Picasso or any other image loading library
//                                            Picasso.get().load(imageUrl).into(imageView);
//                                            linearLayout.addView(imageView);
////                                            View grayLine = view.findViewById(R.id.grayLine);
////                                            linearLayout.addView(grayLine);
//
//                                        }

                                        LinearLayout linearLayout = view.findViewById(R.id.buttonLinearLayout);
                                        Resources resources = getResources();
                                        int separatorHeight = (int) resources.getDimension(R.dimen.separator_height); // Define the separator height in dimens.xml

                                        for (String imageUrl : imageUrls) {
                                            ImageView imageView = new ImageView(getContext());
                                            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                            );
                                            imageView.setLayoutParams(imageLayoutParams);
                                            imageView.setAdjustViewBounds(true);

                                            // Load images into ImageView using Picasso or any other image loading library
                                            Picasso.get().load(imageUrl).into(imageView);

                                            // Add ImageView to the LinearLayout
                                            linearLayout.addView(imageView);

                                            // Add a gray separator line
                                            View separatorView = new View(getContext());
                                            LinearLayout.LayoutParams separatorLayoutParams = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                                    separatorHeight // Set the height of the separator
                                            );
                                            separatorView.setLayoutParams(separatorLayoutParams);
                                            separatorView.setBackgroundColor(Color.GRAY); // Set the background color of the separator
                                            linearLayout.addView(separatorView);
                                        }




//                            ImagePagerAdapter adapter = new ImagePagerAdapter(view.getContext(), imageUrls);
//                            viewPager.setAdapter(adapter);
                                    }
                                    else{
                                    }
                                }
                                catch (Exception e){
                                }
                            }

            }
                        }
                        catch (Exception e){
                            Log.d("Exception Fifa", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);



        return view;
    }

    public void processJsonObject(JSONObject jsonObject) {
        receivedJsonObject = new Gson().fromJson(String.valueOf(jsonObject), JsonObject.class);
    }
}
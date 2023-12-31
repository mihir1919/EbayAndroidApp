package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

//import org.json.simple.parser.ParseException;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String jsonFromURL = null;

    private AutoCompleteTextView autoCompleteTextView;
    private TextView resultTextView;

    public HashMap<String, Boolean> wishListMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Autocomplete

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        String[] suggestions = {"90210",
                "90002",
                "90003",
                "90004",
                "90006",
                "90001"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setDropDownHeight(getResources().getDimensionPixelSize(R.dimen.dropdown_height));
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click if needed
        });
        //End

        CheckBox checkboxEnableNearbySearch = findViewById(R.id.checkboxEnableNearbySearch);
        LinearLayout buttonLinearLayout = findViewById(R.id.buttonLinearLayout);
        LinearLayout hiddenLayout = findViewById(R.id.hiddenLayout);



        try{
            Button clearButton = findViewById(R.id.buttonClearIt);

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);
                        CheckBox checkBoxNew = (CheckBox) findViewById(R.id.checkboxNew);
                        CheckBox checkboxUsed = (CheckBox) findViewById(R.id.checkboxUsed);
                        CheckBox checkBoxUnspecified = (CheckBox) findViewById(R.id.checkboxUnspecified);
                        CheckBox checkBoxLocalPickup = (CheckBox) findViewById(R.id.checkboxLocalPickup);
                        CheckBox checkBoxFreeShipping = (CheckBox) findViewById(R.id.checkboxFreeShipping);
                        CheckBox checkBoxNearby = (CheckBox) findViewById(R.id.checkboxEnableNearbySearch);
                        EditText editTextMilesFrom = (EditText) findViewById(R.id.editTextMilesFrom);
                        RadioButton radioButtonCurrent = (RadioButton) findViewById(R.id.radioButtonCurrentLocation);
                        RadioButton radioButtonZip = (RadioButton) findViewById(R.id.radioButtonZipCode);
                        AutoCompleteTextView editTextZipCodeVar = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

                        // Reset values of all UI components
                        Spinner spinnerd = (Spinner) findViewById(R.id.spinnerCategory);
                        spinnerd.setSelection(0); // Reset Spinner to the default selection
                        checkBoxNew.setChecked(false);
                        checkboxUsed.setChecked(false);
                        checkBoxUnspecified.setChecked(false);
                        checkBoxLocalPickup.setChecked(false);
                        checkBoxFreeShipping.setChecked(false);
                        checkBoxNearby.setChecked(false);
                        editTextMilesFrom.setText(""); // Clear the EditText
                        radioButtonCurrent.setChecked(true); // Set RadioButton to default state
                        radioButtonZip.setChecked(false);
                        editTextZipCodeVar.setText(""); // Clear the AutoCompleteTextView
                        EditText textViewKeyword = (EditText) findViewById(R.id.editTextKeyword);
                        textViewKeyword.setText(""); // Clear the TextView
                        Log.d("clieedede","fekldmwkf");
                    }
                    catch (Exception e){
                        Log.d("this is the error", e.toString());
                    }

                }
            });
        }
        catch (Exception e){

        }


        checkboxEnableNearbySearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hiddenLayout.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.addRule(RelativeLayout.BELOW, R.id.hiddenLayout);
                    buttonLinearLayout.setLayoutParams(params);
                } else {
                    hiddenLayout.setVisibility(View.INVISIBLE);

                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.addRule(RelativeLayout.BELOW, R.id.checkboxEnableNearbySearch);
                    buttonLinearLayout.setLayoutParams(params);
                }
            }

        });



    }

    private void displayTextFiveTimes(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            result.append(text).append("\n");
        }
        resultTextView.setText(result.toString());
    }

    public void onZipChanged(View V) {
        RadioButton currentLoc = (RadioButton) findViewById(R.id.radioButtonCurrentLocation);
        RadioButton zipLoc = (RadioButton) findViewById(R.id.radioButtonZipCode);
        Log.d("current location selected", V.getId() == R.id.radioButtonCurrentLocation ? "Yes" : "No");

        if (V.getId() == R.id.radioButtonCurrentLocation) {
            zipLoc.setChecked(false);
            currentLoc.setChecked(true);
        } else {
            zipLoc.setChecked(true);
            currentLoc.setChecked(false);
        }
//        Log.d(currentLoc.isChecked());
    }



    public void searchFormFunction(View V) throws IOException, JSONException {


        HashMap<String, String> categoryMap = new HashMap<>();

        categoryMap.put("Art", "550");
        categoryMap.put("Baby", "2984");
        categoryMap.put("Books", "267");
        categoryMap.put("Clothing, Shoes and Accessories", "11450");
        categoryMap.put("Computer, Tablets and Network", "58058");
        categoryMap.put("Health & Beauty", "26395");
        categoryMap.put("Music", "11233");
        categoryMap.put("Video Games & Consoles", "1249");


        EditText textViewKeyword = (EditText) findViewById(R.id.editTextKeyword);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);
        CheckBox checkBoxNew = (CheckBox) findViewById(R.id.checkboxNew);
        CheckBox checkboxUsed = (CheckBox) findViewById(R.id.checkboxUsed);
        CheckBox checkBoxUnspecified = (CheckBox) findViewById(R.id.checkboxUnspecified);
        CheckBox checkBoxLocalPickup = (CheckBox) findViewById(R.id.checkboxLocalPickup);
        CheckBox checkBoxFreeShipping = (CheckBox) findViewById(R.id.checkboxFreeShipping);
        CheckBox checkBoxNearby = (CheckBox) findViewById(R.id.checkboxEnableNearbySearch);
        EditText editTextMilesFrom = (EditText) findViewById(R.id.editTextMilesFrom);
        RadioButton radioButtonCurrent = (RadioButton) findViewById(R.id.radioButtonCurrentLocation);
        RadioButton radioButtonZip = (RadioButton) findViewById(R.id.radioButtonZipCode);
        AutoCompleteTextView editTextZipCodeVar = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

//        String baseUrl = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=VishalVe-prodsc12-PRD-a0eed1ece-c051b032&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&paginationInput.entriesPerPage=50&";
        String baseUrl = "https://ebayreactmihir-2454971216.wl.r.appspot.com/api/";
        if (textViewKeyword != null && textViewKeyword.getText().length() > 0) {

            String original = String.valueOf(textViewKeyword.getText());
            String encodedString = original.replace(" ", "%20");
            baseUrl += "keywords=" + encodedString + "&";
            TextView errorKeyword = findViewById(R.id.keywordError);
            errorKeyword.setVisibility(View.INVISIBLE);

            TextView categoryView = findViewById(R.id.titleCategory);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) categoryView.getLayoutParams();
            layoutParams.addRule(RelativeLayout.BELOW, (R.id.editTextKeyword));
            categoryView.setLayoutParams(layoutParams);

        }
        else{
            TextView errorKeyword = findViewById(R.id.keywordError);
            errorKeyword.setVisibility(View.VISIBLE);

            TextView categoryView = findViewById(R.id.titleCategory);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) categoryView.getLayoutParams();
            layoutParams.addRule(RelativeLayout.BELOW, R.id.keywordError);
            categoryView.setLayoutParams(layoutParams);

            Toast.makeText(MainActivity.this, "Please fill values properly", Toast.LENGTH_LONG).show();
            return;
        }




        try{

            Log.d("hk",spinner.getSelectedItem().toString());

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try{

                        String selectedItem = parent.getItemAtPosition(position).toString();
                        Log.d("categoory", categoryMap.get(selectedItem));
                    }
                    catch (Exception e){
                        Log.d("error was this",e.toString());
                    }
                    // Do something with the selected item
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Handle case where nothing is selected
                }
            });

        }
        catch (Exception e){
            Log.d("what is this", e.toString());
        }

        try{
            Log.d("cattss", spinner.getSelectedItem().toString());
            Log.d("ssttac", categoryMap.get(spinner.getSelectedItem().toString()));
            if (categoryMap.get(spinner.getSelectedItem().toString())!=null) {
                baseUrl += "categoryId=" + categoryMap.get(spinner.getSelectedItem().toString()) + "&";
            }
        }
        catch (Exception e){
        }


        int k = 0;
        if ((checkBoxNew != null && checkBoxNew.isChecked()) || (checkboxUsed != null && checkboxUsed.isChecked()) || (checkBoxUnspecified != null && (checkBoxUnspecified.isChecked()))) {
            baseUrl += "itemFilter(" + k + ").name=Condition&";

            List<String> data = new ArrayList<String>();
            if ((checkBoxNew != null && checkBoxNew.isChecked())) {
                data.add("New");
            }
            if ((checkboxUsed != null && checkboxUsed.isChecked())) {
                data.add("Used");
            }
            if ((checkBoxUnspecified != null && checkBoxUnspecified.isChecked())) {
                data.add("Unspecified");
            }
            for (int j = 0; j < data.size(); j++) {
                baseUrl += "itemFilter(" + k + ").value(" + j + ")=" + data.get(j);
                baseUrl += "&";
            }
            k++;
        }

        if (checkBoxFreeShipping.isChecked()) {
            baseUrl +=
                    "itemFilter(" +
                            k +
                            ").name=FreeShippingOnly&itemFilter(" +
                            k++ +
                            ").value=true";
            baseUrl += "&";
        } else {
            baseUrl +=
                    "itemFilter(" +
                            k +
                            ").name=FreeShippingOnly&itemFilter(" +
                            k++ +
                            ").value=false";
            baseUrl += "&";
        }

        if (checkBoxLocalPickup.isChecked()) {
            baseUrl +=
                    "itemFilter(" +
                            k +
                            ").name=LocalPickupOnly&itemFilter(" +
                            k++ +
                            ").value=true";
            baseUrl += "&";
        } else {
            baseUrl +=
                    "itemFilter(" +
                            k +
                            ").name=LocalPickupOnly&itemFilter(" +
                            k++ +
                            ").value=false";
            baseUrl += "&";
        }

        try {
            if (editTextMilesFrom != null && editTextMilesFrom.length() > 0 && Double.parseDouble(editTextMilesFrom.getText().toString()) > 0) {
                baseUrl +=
                        "itemFilter(" +
                                k +
                                ").name=MaxDistance&itemFilter(" +
                                k++ +
                                ").value=" + Integer.parseInt(editTextMilesFrom.getText().toString());
                baseUrl += "&";
            }
            else{
                baseUrl +=
                        "itemFilter(" +
                                k +
                                ").name=MaxDistance&itemFilter(" +
                                k++ +
                                ").value=10";
                baseUrl += "&";
            }
        } catch (Exception e) {
            Log.d("error in zip", e.toString());
        }

        if (checkBoxNearby.isChecked()) {
            if (radioButtonCurrent.isChecked() == true) {
                if (editTextZipCodeVar != null && (String.valueOf(editTextZipCodeVar.getText())).length() == 5) {
                    baseUrl += "buyerPostalCode=" + (String.valueOf(editTextZipCodeVar.getText())) + "&";
                }
            } else {
                baseUrl += "buyerPostalCode=" + "90004" + "&";
            }
        }
        else{
            baseUrl += "buyerPostalCode=" + "90037" + "&";
        }


        if (baseUrl != null && baseUrl.charAt(baseUrl.length() - 1) == '&') {
            baseUrl = baseUrl.substring(0, baseUrl.length() -1);
        }
//        baseUrl += "outputSelector(0)=SellerInfo&outputSelector(1)=StoreInfo";
        Log.d("your base url", baseUrl);



        RequestQueue volleyQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/getWishlistItem/";
        Log.d("wishlist URL resp",url);
        String finalBaseUrl = baseUrl;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                (Response.Listener<JSONArray>) response -> {
                    String dogImageUrl;
                    try {
                        Log.d("finalAbax", String.valueOf(response));
                        JsonArray jobj = new Gson().fromJson(String.valueOf(response), JsonArray.class);
                        jsonFromURL = String.valueOf(response);
                        try{
                            for(int i=0;i<jobj.size();i++){
                                try{
                                    wishListMap.put(jobj.get(i).getAsJsonObject().get("itemId").getAsString(), true);
                                    Log.d("wishlistMapItem",jobj.get(i).getAsJsonObject().get("itemId").getAsString());
                                }
                                catch (Exception e){
                                }
                            }
                        }
                        catch (Exception e) {
                            Log.d("exception in count", e.toString());
                        }
                        Log.d("mapppper",wishListMap.toString());
                        loadDogImage(finalBaseUrl.toString(), wishListMap);
                    } catch (Exception e) {
                        Log.d("fucking error", e.toString());
                    }
                },

                (Response.ErrorListener) error -> {
                    Toast.makeText(MainActivity.this, "Some error occurred! Cannot fetch dog image", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", error.toString());
                }
        );

        // add the json request object created above
        // to the Volley request queue
        volleyQueue.add(jsonObjectRequest);



//        JsonObject jobj = new Gson().fromJson(jsonFromURL, JsonObject.class);
//        try{
//            Log.d("objectcount",jobj.get("findItemsAdvancedResponse").getAsJsonArray().toString());
//        }
//        catch (Exception e) {
//            Log.d('exception in count', e.toString());
//        }
    }


    public void getWishlist(View V){
        loadWishlist(wishListMap);
    }


    private void loadWishlist(HashMap<String, Boolean> wishlistMap) {
        // getting a new volley request queue for making new requests
        RequestQueue volleyQueue = Volley.newRequestQueue(MainActivity.this);
        // url of the api through which we get random dog images
        String url = "https://ebayreactmihir-2454971216.wl.r.appspot.com/getWishlistItem/";
        Log.d("wishlist URL resp",url);
        // since the response we get from the api is in JSON, we
        // need to use `JsonObjectRequest` for parsing the
        // request response
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
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
                (Response.Listener<JSONArray>) response -> {
                    // get the image url from the JSON object
                    String dogImageUrl;
                    try {
//                        dogImageUrl = response.getString("message");
                        Log.d("finalAbax", String.valueOf(response));
                        JsonArray jobj = new Gson().fromJson(String.valueOf(response), JsonArray.class);
                        jsonFromURL = String.valueOf(response);
                        try{



                            Intent intent = new Intent(this, ItemCards.class);

//                            intent.putExtra("jsonObject", jobj.get("findItemsAdvancedResponse").getAsJsonArray().get(0).getAsJsonObject().get("searchResult").getAsJsonArray().get(0).getAsJsonObject().get("item").getAsJsonArray().toString());
//                            intent.putExtra("isWishlist", "false");
                            intent.putExtra("wishlistMap",wishlistMap);
                            intent.putExtra("wishlistMapSize", wishlistMap.size());

                            intent.putExtra("jsonObject", jobj.toString());
                            intent.putExtra("isWishlist","true");

                            startActivity(intent);
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
                    Toast.makeText(MainActivity.this, "Some error occurred! Cannot fetch dog image", Toast.LENGTH_LONG).show();
                    // log the error message in the error stream
                    Log.e("MainActivity", error.toString());
                }
        );

        // add the json request object created above
        // to the Volley request queue
        volleyQueue.add(jsonObjectRequest);
    }


    private void loadDogImage(String requestURl, HashMap<String, Boolean> wishlistMap) {
        RequestQueue volleyQueue = Volley.newRequestQueue(MainActivity.this);
        // url of the api through which we get random dog images
        String url = requestURl;
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
                        jsonFromURL = String.valueOf(response);
                        try{
                            Intent intent = new Intent(this, ItemCards.class);
                            intent.putExtra("jsonObject", jobj.get("findItemsAdvancedResponse").getAsJsonArray().get(0).getAsJsonObject().get("searchResult").getAsJsonArray().get(0).getAsJsonObject().get("item").getAsJsonArray().toString());
                            intent.putExtra("isWishlist", "false");
                            intent.putExtra("wishlistMap",wishlistMap);
                            startActivity(intent);
                        }
                        catch (Exception e) {

                            Intent intentError = new Intent(this, NoResults.class);
//                            intent.putExtra("type", "product");
                            startActivity(intentError);
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
                    Toast.makeText(MainActivity.this, "Some error occurred! Cannot fetch dog image", Toast.LENGTH_LONG).show();
                    // log the error message in the error stream
                    Log.e("MainActivity", "loadDogImage error: ${error.localizedMessage}");
                }
        );

        // add the json request object created above
        // to the Volley request queue
        volleyQueue.add(jsonObjectRequest);
    }


}
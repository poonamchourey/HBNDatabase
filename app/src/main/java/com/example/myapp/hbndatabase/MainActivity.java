package com.example.myapp.hbndatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    InnovationAdapter adapter;
    ArrayList<Innovation> innovationList = new ArrayList<Innovation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new InnovationAdapter(this, innovationList);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Innovation innovation = (Innovation) listView.getAdapter().getItem(position);
                 //  String showurl = "http://www.srist.org/cms/";
                 String STitle = innovation.getTitle();
                String SName = innovation.getName();
                String SAddress = innovation.getAddress();
                  String SDetails  = innovation.getDetails();
               Intent intent;
                intent = new Intent(MainActivity.this,Display.class);
              //  intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.sristi.org/hbnew/honeybee_detailed.php?"+innovation.getId()));intent = new Intent(MainActivity.this,Display.class);
               // intent.setAction(Intent.ACTION_VIEW);
               // intent.addCategory(Intent.CATEGORY_BROWSABLE);
               // intent.setData(Uri.parse("www.sristi.org"));
              //  intent.putExtra("url",showurl);
                intent.putExtra("TITLE",STitle);
                intent.putExtra("NAME",SName);
                intent.putExtra("ADDRESS",SAddress);
                intent.putExtra("DETAILS",SDetails);
                startActivity(intent);
                // openWebPage(url);
            }
        });

        final EditText mSearchText = (EditText) findViewById(R.id.searchText);

        Button mSearchButton = (Button) findViewById(R.id.searchBtn);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = mSearchText.getText().toString();
                String encodedQuery = null;
                try {
                    // For handling multi word queries
                    encodedQuery = URLEncoder.encode(query, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                clearData();
                getData("http://www.sristi.org/hbnew/json.php?global_search/honeybee/" + encodedQuery);
                // getData("http://5b06984cff98d70014f38858.mockapi.io/shrishti?search=" + encodedQuery); // Replace with your own API
                // getData("http://5b0fbac798bef800145855d5.mockapi.io/Techpedia?search=" + encodedQuery);
                // getData("http://5b0fbbca98bef800145855d8.mockapi.io/NIF?search=" + encodedQuery);// Add more method calls for different APIs here
                // For example:
                // getData("http://5b06984cff98d70014f38858.mockapi.io/nif?search=" + encodedQuery);
            }
        });

        Button mResetButton = (Button) findViewById(R.id.resetBtn);

      /*  mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchText.setText("");
                clearData();
            }
        });*/
       // true onOptionItemSelected();

    }





    public void openWebPage(String myurl) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(myurl));
    }

    private void clearData() {
        innovationList.clear();
        adapter.notifyDataSetChanged();
    }

    private void getData(String url) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Searching...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               // clearData();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Innovation innovation = new Innovation();
                        innovation.setName(jsonObject.getString("name"));
                        innovation.setAddress(jsonObject.getString("address"));
                        innovation.setDistrict(jsonObject.getString("district"));
                        innovation.setState(jsonObject.getString("state"));
                        innovation.setType(jsonObject.getString("type"));
                        innovation.setCountry(jsonObject.getString("country"));
                        innovation.setTitle(jsonObject.getString("title"));
                        innovation.setCountry(jsonObject.getString("country"));
                        innovation.setDetails(jsonObject.getString("details"));
                        innovation.setVolume_no(jsonObject.getString("volume_no"));
                        innovation.setSout(jsonObject.getString("sout"));
                        innovation.setCall_number(jsonObject.getString("call_number"));
                        innovation.setStatus(jsonObject.getString("status"));






                        innovation.setName(jsonObject.getString("name"));
                        innovation.setType(jsonObject.getString("type"));
                        innovation.setTitle(jsonObject.getString("title"));
                        innovation.setId(jsonObject.getString("ID"));


                        innovationList.add(innovation);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}

package com.example.scannerqrapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mProductAdapter;
    public ArrayList<Product> mProductList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductList = new ArrayList<>();
//        if(getIntent().hasExtra("com.example.scannerqrapp.URL")){
//            TextView textView = (TextView) findViewById(R.id.tmpTextView);
//            String text = getIntent().getExtras().getString("com.example.scannerqrapp.URL");
//            textView.setText(text);
//        }



        new AsyncFetch().execute();
    }

    private class AsyncFetch extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            final ProgressDialog progressDialog = ProgressDialog.show(MenuActivity.this, "Loading posts", "Please wait", false, false);
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {

                URL url = new URL("http://35.228.252.16:3000/products");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                int lengthOfFile = urlConnection.getContentLength();
                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                forecastJsonStr = buffer.toString();
                Log.e("Json1", forecastJsonStr);

                JSONArray jsonArray = new JSONArray(forecastJsonStr);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("_id");
                    String name = jsonObject.getString("name");
                    String productImageUrl = jsonObject.getString("productImageUrl");
                    int price = jsonObject.getInt("price");
                    Product tmpProduct = new Product(id,name,price,productImageUrl);
                    System.out.println("PRODUCT ------------------------------> " + tmpProduct);
                    mProductList.add(tmpProduct);
                }

                return forecastJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            return forecastJsonStr;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProductAdapter = new ProductAdapter(MenuActivity.this, mProductList);
            mRecyclerView.setAdapter(mProductAdapter);
        }
    }

}

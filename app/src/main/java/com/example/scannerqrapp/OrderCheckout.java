package com.example.scannerqrapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class OrderCheckout extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private ArrayList productsArrayList ;
    private Button btnCheckOut;
    private Order newOrder;
    private EditText editText;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_checkout);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //tmpArrayList is the list which is created when they are pressing the button (must be static to be recreated when the button is clicked and be able to reference this arraylist whenever we want)
        productsArrayList = MenuActivity.tmpArrayList;
        mProductAdapter = new ProductAdapter(OrderCheckout.this, MenuActivity.tmpArrayList);
        mRecyclerView.setAdapter(mProductAdapter);

        editText = findViewById(R.id.editText);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOrder = new Order(editText.getText() + "");
                newOrder.setProducts(productsArrayList);

                Intent finalStage = new Intent(OrderCheckout.this, FinalActivity.class);
                finalStage.putExtra("com.example.scannerqrapp.FINAL_MESSAGE", "ORDER " + newOrder.getOrderName() + " PLACED SUCCESSFULY");
                startActivity(finalStage);
            }
        });



    }
}

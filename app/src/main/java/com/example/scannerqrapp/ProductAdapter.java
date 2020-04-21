package com.example.scannerqrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

//import com.squareup.picasso.Picasso;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private ArrayList<Product> productList;

    public ProductAdapter(Context mContext, ArrayList<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.product_layout, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentItem = productList.get(position);
        String name = currentItem.getName();
        int price = currentItem.getPrice();
        String id = currentItem.getId();
        String url = "http://35.228.252.16:3000/uploads/" + currentItem.getUrl();
        holder.mTextViewPrice.setText(Integer.toString(price));
        holder.mTextViewName.setText(name);
        holder.mTextViewUrl.setText(url);
        holder.mTextViewId.setText(id);
        Glide.with(mContext).load(url).into(holder.img_thumb);
//        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

//        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewPrice;
        public TextView mTextViewUrl;
        public TextView mTextViewId;
        public ImageView img_thumb;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
//            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewPrice = itemView.findViewById(R.id.text_view_price);
            mTextViewUrl = itemView.findViewById(R.id.text_view_url);
            mTextViewId = itemView.findViewById(R.id.text_view_id);
            img_thumb = itemView.findViewById(R.id.image_view);
        }

    }

}

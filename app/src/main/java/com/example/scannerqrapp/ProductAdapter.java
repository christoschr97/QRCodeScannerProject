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
import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private ArrayList<Product> productList;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

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
        String clickedCaption = currentItem.getSelectCaption(); //default = "NOT SELECTED"
        holder.mTextViewPrice.setText("$" + price);
        holder.mTextViewName.setText(name);
        holder.mTextViewClicked.setText(clickedCaption);
//        holder.mTextViewId.setText(id);
        Glide.with(mContext).load(url).into(holder.img_thumb);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

//        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewPrice;
        public TextView mTextViewClicked;
        public TextView mTextViewId;
        public ImageView img_thumb;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewPrice = itemView.findViewById(R.id.text_view_price);
            mTextViewClicked = itemView.findViewById(R.id.text_view_clicked);
//            mTextViewId = itemView.findViewById(R.id.text_view_id);
            img_thumb = itemView.findViewById(R.id.productImgView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

}

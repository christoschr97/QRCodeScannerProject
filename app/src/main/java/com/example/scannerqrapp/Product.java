package com.example.scannerqrapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private String id;
    private String name;
    private int price;
    private String url;
    private String selectCaption;
    private boolean checked;
    private int quantity;

    public Product(String id, String name, int price, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
        this.checked = false;
        this.selectCaption = "NOT SELECTED";
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readInt();
        url = in.readString();
        checked = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectCaption() {
        return selectCaption;
    }

    public void setSelectCaption(String selectCaption) {
        this.selectCaption = selectCaption;
    }

    public int getPrice() {
        return price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(url);
        dest.writeByte((byte) (checked ? 1 : 0));
    }
}

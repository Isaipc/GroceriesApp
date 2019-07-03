package com.ubicsat.abarrotesapp.POJO;

import android.content.ContentValues;

import com.ubicsat.abarrotesapp.data.AbarrotesAppContract;

import java.util.UUID;

public class Product
{
    public String id;
    public String name;
    public String description;
    public String categoryId;
    public float price;
    public String image;

    public Product(){ }

    public Product(String id, String name, String description, String categoryId, float price, String image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.image = image;
    }

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues();
        values.put(AbarrotesAppContract.ProductEntry.COLUMN_ID, id);
        values.put(AbarrotesAppContract.ProductEntry.COLUMN_NAME, name);
        values.put(AbarrotesAppContract.ProductEntry.COLUMN_DESCRIPTION, description);
        values.put(AbarrotesAppContract.ProductEntry.COLUMN_CATEGORY_ID, categoryId);
        values.put(AbarrotesAppContract.ProductEntry.COLUMN_PRICE, price);
        values.put(AbarrotesAppContract.ProductEntry.COLUMN_IMAGE, image);

        return values;
    }
}

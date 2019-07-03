package com.ubicsat.abarrotesapp.POJO;

import android.content.ContentValues;

import com.ubicsat.abarrotesapp.data.AbarrotesAppContract;

import java.util.UUID;

public class Category
{
    public String id;
    public String name;
    public String description;
    public String image;

    public Category() { }

    public Category(String id, String name, String description, String image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues();
        values.put(AbarrotesAppContract.CategoryEntry.COLUMN_ID, id);
        values.put(AbarrotesAppContract.CategoryEntry.COLUMN_NAME, name);
        values.put(AbarrotesAppContract.CategoryEntry.COLUMN_DESCRIPTION, description);
        values.put(AbarrotesAppContract.CategoryEntry.COLUMN_IMAGE, image);

        return values;
    }
}

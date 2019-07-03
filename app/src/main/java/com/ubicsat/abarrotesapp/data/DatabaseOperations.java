package com.ubicsat.abarrotesapp.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ubicsat.abarrotesapp.POJO.Category;
import com.ubicsat.abarrotesapp.data.AbarrotesAppContract.ProductEntry;
import com.ubicsat.abarrotesapp.data.AbarrotesAppContract.CategoryEntry;
import com.ubicsat.abarrotesapp.POJO.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations
{

    private static DatabaseHelper database;
    private static DatabaseOperations instance = new DatabaseOperations();


    public static DatabaseOperations getInstance(Context context) {

        if(database == null)
            database = new DatabaseHelper(context);

        return instance;
    }

    public SQLiteDatabase getDatabase() {
        return database.getWritableDatabase();
    }


    /*OPERATIONS*/

    public boolean insertProduct(Product product)
    {
        SQLiteDatabase db = getDatabase();

        return db.insertOrThrow(AbarrotesAppContract.ProductEntry.TABLE_NAME,
                null,
                product.toValues()) > 0;
    }

    public boolean updateProduct(Product product)
    {
        SQLiteDatabase db = getDatabase();

        ContentValues values = product.toValues();
        values.remove(AbarrotesAppContract.ProductEntry.COLUMN_ID);

        String [] whereArgs = { product.id };

        return db.update(AbarrotesAppContract.ProductEntry.TABLE_NAME,
                values,
                AbarrotesAppContract.PRODUCT_WHERE_CLAUSE,
                whereArgs) > 0;
    }

    public boolean deleteProduct(String productId)
    {
        SQLiteDatabase db = getDatabase();

        String [] whereArgs = { productId };

        return db.delete(AbarrotesAppContract.ProductEntry.TABLE_NAME,
                AbarrotesAppContract.PRODUCT_WHERE_CLAUSE,
                whereArgs) > 0;
    }

    public Cursor getProduct(String productId)
    {
        SQLiteDatabase db = getDatabase();

        String [] whereArgs = { productId };

        return db.query(
                AbarrotesAppContract.ProductEntry.TABLE_NAME,
                AbarrotesAppContract.PRODUCT_PROJECTION,
                AbarrotesAppContract.PRODUCT_WHERE_CLAUSE,
                whereArgs,
                null,
                null,
                null
                );
    }

    public Cursor getAllProducts()
    {
        SQLiteDatabase db = getDatabase();

        String sql = "SELECT * FROM " + AbarrotesAppContract.ProductEntry.TABLE_NAME;

        return db.rawQuery( sql, null);
    }

    public List<Product> getProductList()
    {
        List <Product> productList = new ArrayList<>();

        Cursor cursor = getAllProducts();

        while(cursor.moveToNext())
        {
            Product product = new Product();
            product.id = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_ID));
            product.name = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME));
            product.description = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_DESCRIPTION));
            product.categoryId = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_CATEGORY_ID));
            product.price = Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRICE)));
            product.image = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_IMAGE));

            productList.add(product);
        }

        cursor.close();

        return productList;
    }


    public boolean insertCategory(Category category)
    {
        SQLiteDatabase db = getDatabase();

        return db.insertOrThrow(AbarrotesAppContract.CategoryEntry.TABLE_NAME,
                null,
                category.toValues()) > 0;
    }

    public boolean updateCategory(Category category)
    {
        SQLiteDatabase db = getDatabase();

        ContentValues values = category.toValues();
        values.remove(AbarrotesAppContract.CategoryEntry.COLUMN_ID);

        String [] whereArgs = { category.id };

        return db.update(AbarrotesAppContract.CategoryEntry.TABLE_NAME,
                values,
                AbarrotesAppContract.CATEGORY_WHERE_CLAUSE,
                whereArgs) > 0;
    }

    public boolean deleteCategory(String categoryId)
    {
        SQLiteDatabase db = getDatabase();

        String [] whereArgs = { categoryId };

        return db.delete(AbarrotesAppContract.CategoryEntry.TABLE_NAME,
                AbarrotesAppContract.CATEGORY_WHERE_CLAUSE,
                whereArgs) > 0;
    }

    public Cursor getCategory(String categoryId)
    {
        SQLiteDatabase db = getDatabase();

        String [] whereArgs = { categoryId };

        return db.query(
                AbarrotesAppContract.CategoryEntry.TABLE_NAME,
                AbarrotesAppContract.CATEGORY_PROJECTION,
                AbarrotesAppContract.CATEGORY_WHERE_CLAUSE,
                whereArgs,
                null,
                null,
                null
        );
    }

    public Cursor getAllCategories()
    {
        SQLiteDatabase db = getDatabase();

        String sql = "SELECT * FROM " + AbarrotesAppContract.CategoryEntry.TABLE_NAME;

        return db.rawQuery( sql, null);
    }

    public List<Category> getCategoryList()
    {
        List <Category> categoryList = new ArrayList<>();

        Cursor cursor = getAllCategories();

        while(cursor.moveToNext())
        {
            Category category = new Category();
            category.id = cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_ID));
            category.name = cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME));
            category.description = cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_DESCRIPTION));
            category.image = cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_IMAGE));

            categoryList.add(category);
        }

        cursor.close();

        return categoryList;
    }
}

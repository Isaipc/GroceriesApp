package com.ubicsat.abarrotesapp.data;

public class AbarrotesAppContract
{
    public interface Entry
    {
        String COLUMN_ID = "id";
    }

    public static class ProductEntry implements Entry
    {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE = "image";
    }

    public static class CategoryEntry implements Entry
    {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE = "image";
    }

    public interface References
    {
        String COLUMN_CATEGORY_ID = String.format("REFERENCES %s (%s) ON DELETE CASCADE",
                ProductEntry.TABLE_NAME, AbarrotesAppContract.ProductEntry.COLUMN_CATEGORY_ID);
    }

    public static final String SQL_CREATE_PRODUCT_ENTRY =
            "CREATE TABLE " + AbarrotesAppContract.ProductEntry.TABLE_NAME + "(" +
                    AbarrotesAppContract.ProductEntry.COLUMN_ID + " TEXT PRIMARY KEY NOT NULL, " +
                    AbarrotesAppContract.ProductEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    AbarrotesAppContract.ProductEntry.COLUMN_CATEGORY_ID + " TEXT NOT NULL " +
                        References.COLUMN_CATEGORY_ID  + ", " +
                    AbarrotesAppContract.ProductEntry.COLUMN_PRICE + " REAL NOT NULL, " +
                    AbarrotesAppContract.ProductEntry.COLUMN_DESCRIPTION + " TEXT, " +
                    AbarrotesAppContract.ProductEntry.COLUMN_IMAGE + " TEXT)";

    public static final String SQL_CREATE_CATEGORY_ENTRY =
            "CREATE TABLE " + ProductEntry.TABLE_NAME + "(" +
                    ProductEntry.COLUMN_ID + " TEXT PRIMARY KEY NOT NULL, " +
                    ProductEntry.COLUMN_NAME+ " TEXT NOT NULL, " +
                    ProductEntry.COLUMN_DESCRIPTION + " TEXT, " +
                    ProductEntry.COLUMN_IMAGE+ " TEXT)";

    public static final String SQL_DELETE_PRODUCT_ENTRY =
            "DROP TABLE IF EXISTS " + AbarrotesAppContract.ProductEntry.TABLE_NAME;

    public static final String SQL_DELETE_CATEGORY_ENTRY =
            "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;

    public static final String PRODUCT_WHERE_CLAUSE = ProductEntry.COLUMN_ID + " = ?";
    public static final String CATEGORY_WHERE_CLAUSE = ProductEntry.COLUMN_ID + " = ?";


    public static final String [] PRODUCT_PROJECTION =
            {
                    ProductEntry.COLUMN_ID,
                    ProductEntry.COLUMN_NAME,
                    ProductEntry.COLUMN_DESCRIPTION,
                    ProductEntry.COLUMN_CATEGORY_ID,
                    ProductEntry.COLUMN_PRICE,
                    ProductEntry.COLUMN_IMAGE
            };

    public static final String[] CATEGORY_PROJECTION =
            {
                    CategoryEntry.COLUMN_ID,
                    CategoryEntry.COLUMN_NAME,
                    CategoryEntry.COLUMN_DESCRIPTION,
                    CategoryEntry.COLUMN_IMAGE
            };



}

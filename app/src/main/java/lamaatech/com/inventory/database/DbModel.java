package lamaatech.com.inventory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lamaatech.com.inventory.models.Product;

import static lamaatech.com.inventory.database.ProductContract.ProductEntry.TABLE_NAME;

/**
 * Created by MrHacker on 9/7/2017.
 */

public class DbModel implements IModel {

    private ProductDbHelper mDbHelper;
    private Context context;

    public DbModel(Context newContext) {
        context = newContext;
        mDbHelper = new ProductDbHelper(context);
    }


    @Override
    public void addProduct(Product newProduct) {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();

        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, newProduct.getProductName());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, newProduct.getProductPrice());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY_AVAILABLE, newProduct.getProductQuantity());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, newProduct.getProductSupplier());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PICTURE, newProduct.getProductImage());
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, newProduct.getSupplierEmail());

        long newRowId = db.insert(TABLE_NAME, null, values);

        showToast("Product has been added successfully");
    }


    public void deleteAllItemsFromDb() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        try {
            db.delete(TABLE_NAME, null, null);
        } catch (SQLException e) {
            //Toast.makeText(context,"due to delete: "+e,Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        showToast("Products have been deleted successfully");
    }

    @Override
    public void deleteProduct(Product deletedProduct) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_NAME +
                    " WHERE " + ProductContract.ProductEntry._ID +
                    " = " + deletedProduct.getProductId() + " ;");

        } catch (SQLException e) {
            //Toast.makeText(context,"due to delete: "+e,Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        showToast("Product has been deleted successfully");
    }

    @Override
    public void updateProduct(Product currentProduct) {

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();

        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, currentProduct.getProductName());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, currentProduct.getProductPrice());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY_AVAILABLE, currentProduct.getProductQuantity());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, currentProduct.getProductSupplier());
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PICTURE, currentProduct.getProductImage());
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, currentProduct.getSupplierEmail());

        try {
            long newRowId = db.update(TABLE_NAME, values, "_id=" + currentProduct.getProductId(), null);
        } finally {
            db.close();
        }

        showToast("Product has been updated successfully");
    }

    @Override
    public List<Product> getProducts() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        try {

            Product product = null;
            while (cursor.moveToNext()) {

                product = new Product(cursor);

                products.add(product);

            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

        return products;
    }

    public void deleteAll() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {

            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE 1 ; ");

        } finally {
            db.close();
        }
    }

    @Override
    public Product getProduct(String id) {
        return null;
    }

    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}

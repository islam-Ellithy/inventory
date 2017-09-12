package lamaatech.com.inventory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MrHacker on 8/30/2017.
 */

public class ProductDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "product.db";
    public static final String LOG_TAG = ProductDbHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " ("
                + ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT , "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE + " TEXT NOT NULL  ,"
                + ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY_AVAILABLE + " INTEGER NOT NULL, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT, "
                + ProductContract.ProductEntry.COLUMN_PRODUCT_PICTURE + " BLOB );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        // create new table
        onCreate(db);

    }
}

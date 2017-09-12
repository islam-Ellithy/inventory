package lamaatech.com.inventory.database;

import android.provider.BaseColumns;

/**
 * Created by MrHacker on 8/30/2017.
 */

public class ProductContract {


    public static final class ProductEntry implements BaseColumns{
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY_AVAILABLE = "quantity";
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier";
        public static final String COLUMN_PRODUCT_PICTURE = "product_picture";
    }
}

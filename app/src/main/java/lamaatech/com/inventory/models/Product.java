package lamaatech.com.inventory.models;

import android.database.Cursor;

import java.io.Serializable;

import static lamaatech.com.inventory.database.ProductContract.ProductEntry.COLUMN_PRODUCT_NAME;
import static lamaatech.com.inventory.database.ProductContract.ProductEntry.COLUMN_PRODUCT_PICTURE;
import static lamaatech.com.inventory.database.ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE;
import static lamaatech.com.inventory.database.ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY_AVAILABLE;
import static lamaatech.com.inventory.database.ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME;
import static lamaatech.com.inventory.database.ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL;
import static lamaatech.com.inventory.database.ProductContract.ProductEntry._ID;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class Product implements Serializable {
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private String productPrice;
    private String productSupplier;
    private String supplierEmail;

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    private byte[] productImage;

    public Product(String productName, Integer productQuantity, String productPrice, String productSupplier) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSupplier = productSupplier;
    }


    public Product(String productName, Integer productQuantity, String productPrice, String productSupplier, byte[] productImage, String supplierEmail) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSupplier = productSupplier;
        this.productImage = productImage;
        this.supplierEmail = supplierEmail;
    }

    public Product(Cursor cursor) {
        productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
        productSupplier = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_SUPPLIER_NAME));
        productPrice = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));
        supplierEmail = cursor.getString(cursor.getColumnIndex(COLUMN_SUPPLIER_EMAIL));
        productQuantity = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY_AVAILABLE));
        productImage = cursor.getBlob(cursor.getColumnIndex(COLUMN_PRODUCT_PICTURE));
        productId = cursor.getInt(cursor.getColumnIndex(_ID));
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}

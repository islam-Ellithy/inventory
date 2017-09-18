package lamaatech.com.inventory.core.MainActivity.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lamaatech.com.inventory.database.DbModel;
import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class MainModel implements MainContract.IModel {

    public List<Product> productList;
    private DbModel db;

    public MainModel(Context context) {
        db = new DbModel(context);
    }

    public MainModel() {
        productList = new ArrayList<>();
        productList.add(new Product("BMW", 10, "1,000,000", "Ahmed Aly"));
        productList.add(new Product("Mercedis", 10, "1,000,000", "Ahmed Amr"));
    }

    @Override
    public List<Product> getProducts() {
        return db.getProducts();
    }

    @Override
    public Product getProduct(String id) {
        return db.getProduct(id);
    }

    @Override
    public void addProduct(Product newProduct) {
        db.addProduct(newProduct);
    }

    @Override
    public void deleteAllItems() {
        db.deleteAllItemsFromDb();
    }
}

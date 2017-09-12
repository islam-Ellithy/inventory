package lamaatech.com.inventory.core.EditorActivity.model;

import android.content.Context;

import java.util.List;

import lamaatech.com.inventory.database.DbModel;
import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class EditorModel implements EditorContract.IModel {

    private DbModel db;

    public EditorModel(Context context) {
        db = new DbModel(context);
    }

    @Override
    public void addProduct(Product newProduct) {
        db.addProduct(newProduct);
    }

    @Override
    public void deleteProduct(Product deletedProduct) {
        db.deleteProduct(deletedProduct);
    }

    @Override
    public void updateProduct(Product currentProduct) {
        db.updateProduct(currentProduct);
    }

    @Override
    public List<Product> getProducts() {
        return db.getProducts();
    }

    @Override
    public Product getProduct(String id) {
        return db.getProduct(id);
    }
}

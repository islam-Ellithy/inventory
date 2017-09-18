package lamaatech.com.inventory.core.DetailsActivity.model;

import android.content.Context;

import java.util.List;

import lamaatech.com.inventory.database.DbModel;
import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class DetailsModel implements DetailsContract.IModel {

    private DbModel db;

    public DetailsModel(Context context) {
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

    @Override
    public String getMailMessageModel(Product product, String newOrderQuantity) {
        return "Dear " + product.getProductName() + "\n" +
                "I want to order " + newOrderQuantity + " item\n" +
                "Thanks";
    }
}

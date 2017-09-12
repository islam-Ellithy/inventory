package lamaatech.com.inventory.database;

import java.util.List;

import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/9/2017.
 */

public interface IModel {

    List<Product> getProducts();

    void deleteProduct(Product deletedProduct);

    void updateProduct(Product currentProduct);

    void addProduct(Product newProduct);
    Product getProduct(String id);
}


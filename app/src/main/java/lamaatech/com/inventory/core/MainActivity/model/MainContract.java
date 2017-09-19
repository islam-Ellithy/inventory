package lamaatech.com.inventory.core.MainActivity.model;

import java.util.List;

import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public interface MainContract {

    interface IController {
        void addProduct(Product newProduct);

        void deleteAllItems();

        void updateProducts();

        Product getProduct(String id);
    }

    interface IModel {

        List<Product> getProducts();

        void addProduct(Product newProduct);

        void deleteAllItems();

        void updateProduct(Product currentProduct);

        Product getProduct(String id);
    }

    interface IView {
        void displayUpdateProducts(List<Product> products);
    }

}

package lamaatech.com.inventory.core.DetailActivity.model;

import java.util.List;

import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public interface DetailContract {
    interface IController {
        void addProduct(Product newProduct);

        void deleteProduct(Product deletedProduct);

        void updateProduct(Product currentProduct);

        List<Product> getProducts();

        Product getProduct(String id);

    }

    interface IModel {
        void addProduct(Product newProduct);

        void deleteProduct(Product deletedProduct);

        void updateProduct(Product currentProduct);

        List<Product> getProducts();

        Product getProduct(String id);
    }

    interface IView {
    }
}

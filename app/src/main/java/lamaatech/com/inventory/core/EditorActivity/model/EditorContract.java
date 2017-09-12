package lamaatech.com.inventory.core.EditorActivity.model;

import android.view.View;

import java.util.List;

import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public interface EditorContract {

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

        void addProductBtn(View view);

        void deleteProductBtn(View view);

        void updateProductBtn(View view);

        void selectImageBtn(View view);

        void decreaseQuantityBtn(View view);

        void increaseQuantityBtn(View view);
    }
}

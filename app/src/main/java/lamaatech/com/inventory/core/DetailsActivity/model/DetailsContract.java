package lamaatech.com.inventory.core.DetailsActivity.model;

import android.view.View;

import java.util.List;

import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public interface DetailsContract {

    interface IController {
        void addProduct(Product newProduct);

        void deleteProduct(Product deletedProduct);

        void updateProduct(Product currentProduct);

        List<Product> getProducts();

        Product getProduct(String id);

        String getMailMessage(Product product,String newOrderQuantity);
    }

    interface IModel {
        void addProduct(Product newProduct);

        void deleteProduct(Product deletedProduct);

        void updateProduct(Product currentProduct);

        List<Product> getProducts();

        Product getProduct(String id);

        String getMailMessageModel(Product product,String newOrderQuantity);
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

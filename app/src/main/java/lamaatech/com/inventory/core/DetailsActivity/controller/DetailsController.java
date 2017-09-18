package lamaatech.com.inventory.core.DetailsActivity.controller;

import android.content.Context;

import java.util.List;

import lamaatech.com.inventory.core.DetailsActivity.model.DetailsContract;
import lamaatech.com.inventory.core.DetailsActivity.model.DetailsModel;
import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class DetailsController implements DetailsContract.IController {
    private DetailsContract.IView view;
    private DetailsContract.IModel model;

    public DetailsController(DetailsContract.IView newView, Context context) {
        view = newView;
        model = new DetailsModel(context);
    }

    @Override
    public void addProduct(Product newProduct) {
        if (newProduct.getProductId() != -1)
            model.updateProduct(newProduct);
        else
            model.addProduct(newProduct);
    }

    @Override
    public void deleteProduct(Product deletedProduct) {
        model.deleteProduct(deletedProduct);
    }

    @Override
    public void updateProduct(Product currentProduct) {
        model.updateProduct(currentProduct);
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(String id) {
        return null;
    }

    @Override
    public String getMailMessage(Product product, String newOrderQuantity) {
        return model.getMailMessageModel(product, newOrderQuantity);
    }
}

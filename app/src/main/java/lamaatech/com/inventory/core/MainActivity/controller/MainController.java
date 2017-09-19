package lamaatech.com.inventory.core.MainActivity.controller;

import android.content.Context;

import lamaatech.com.inventory.core.MainActivity.model.MainContract;
import lamaatech.com.inventory.core.MainActivity.model.MainModel;
import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class MainController implements MainContract.IController {
    private MainContract.IView view;
    private MainContract.IModel model;

    public MainController(MainContract.IView newView, Context context) {
        view = newView;
        model = new MainModel(context);
    }

    @Override
    public void updateProducts() {
        view.displayUpdateProducts(model.getProducts());
    }

    @Override
    public void addProduct(Product newProduct) {
        if (newProduct.getProductId() != -1)
            model.updateProduct(newProduct);
        else
            model.addProduct(newProduct);

        view.displayUpdateProducts(model.getProducts());
    }

    @Override
    public void deleteAllItems() {
        model.deleteAllItems();
        view.displayUpdateProducts(model.getProducts());
    }


    @Override
    public Product getProduct(String id) {
        return model.getProduct(id);
    }
}

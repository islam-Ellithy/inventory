package lamaatech.com.inventory.core.EditorActivity.controller;

import android.content.Context;

import java.util.List;

import lamaatech.com.inventory.core.EditorActivity.model.EditorContract;
import lamaatech.com.inventory.core.EditorActivity.model.EditorModel;
import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class EditorController implements EditorContract.IController {
    private EditorContract.IView view;
    private EditorContract.IModel model;

    public EditorController(EditorContract.IView newView, Context context) {
        view = newView;
        model = new EditorModel(context);
    }

    @Override
    public void addProduct(Product newProduct) {
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
}

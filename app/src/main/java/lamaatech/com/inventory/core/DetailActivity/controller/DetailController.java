package lamaatech.com.inventory.core.DetailActivity.controller;

import java.util.List;

import lamaatech.com.inventory.core.DetailActivity.model.DetailContract;
import lamaatech.com.inventory.core.DetailActivity.model.DetailModel;
import lamaatech.com.inventory.models.Product;

/**
 * Created by MrHacker on 9/6/2017.
 */

public class DetailController implements DetailContract.IController {
    private DetailContract.IView view ;
    private DetailContract.IModel model ;

    public DetailController(DetailContract.IView newView)
    {
        view = newView ;
        model = new DetailModel();
    }

    @Override
    public void addProduct(Product newProduct) {

    }

    @Override
    public void deleteProduct(Product deletedProduct) {

    }

    @Override
    public void updateProduct(Product currentProduct) {

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

package lamaatech.com.inventory.core.MainActivity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lamaatech.com.inventory.R;
import lamaatech.com.inventory.core.DetailsActivity.view.DetailsActivity;
import lamaatech.com.inventory.core.MainActivity.controller.MainController;
import lamaatech.com.inventory.core.MainActivity.model.MainContract;
import lamaatech.com.inventory.models.Product;

public class MainActivity extends AppCompatActivity implements MainContract.IView, ProductFragment.OnListFragmentInteractionListener {

    private MainController controller;
    private List<Product> products;
    @BindView(R.id.empty_view)
    protected LinearLayout emptyView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        controller = new MainController(this, getBaseContext());

        controller.updateProducts();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addProduct);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetailsActivity.class));
            }
        });
    }


    private void goToProductFragment() {

        ProductFragment fragment = ProductFragment.newInstance(1);

        fragment.setProducts(products);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, fragment, null)
                .commit();

    }

    private void goToEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }


    protected void onStart() {
        super.onStart();
        controller.updateProducts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_all_items) {
            controller.deleteAllItems();
            return true;
        } else if (id == R.id.insert_dummy_data) {
            Product dummyProduct = new Product("DUMMY Product",
                    10,
                    "10",
                    "Dummy supplier",
                    null,
                    "i@gmail.com");
            controller.addProduct(dummyProduct);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Product item, boolean isQuantity) {
        if (isQuantity) {
            controller.addProduct(item);
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("product", item);
            startActivity(intent);
        }
    }


    @Override
    public void displayUpdateProducts(List<Product> products) {
        if (0 == products.size()) {
            goToEmptyView();
        } else {
            emptyView.setVisibility(View.GONE);
        }
        this.products = products;
        goToProductFragment();
    }

}

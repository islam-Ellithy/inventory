package lamaatech.com.inventory.core.MainActivity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import lamaatech.com.inventory.R;
import lamaatech.com.inventory.core.DetailActivity.view.DetailActivity;
import lamaatech.com.inventory.core.EditorActivity.view.EditorActivity;
import lamaatech.com.inventory.core.MainActivity.controller.MainController;
import lamaatech.com.inventory.core.MainActivity.model.MainContract;
import lamaatech.com.inventory.models.Product;

public class MainActivity extends AppCompatActivity implements MainContract.IView, ProductFragment.OnListFragmentInteractionListener {

    private MainController controller;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new MainController(this, getBaseContext());

        controller.updateProducts();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
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

    private void goToEmptyProductsFragment() {

       // RelativeLayout layout = (RelativeLayout) findViewById(R.id.empty_view);

        //layout.setVisibility(View.VISIBLE);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Product item) {
        startActivity(new Intent(this, DetailActivity.class));
    }


    @Override
    public void displayUpdateProducts(List<Product> products) {
//        if (products.size() == 0) {

        //      }


        //goToEmptyProductsFragment();
        this.products = products;
        goToProductFragment();
    }

}

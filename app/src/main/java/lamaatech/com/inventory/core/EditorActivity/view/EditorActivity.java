package lamaatech.com.inventory.core.EditorActivity.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import lamaatech.com.inventory.R;
import lamaatech.com.inventory.core.EditorActivity.controller.EditorController;
import lamaatech.com.inventory.core.EditorActivity.model.EditorContract;
import lamaatech.com.inventory.database.DbBitmapUtility;
import lamaatech.com.inventory.models.Product;


//this Activity for add new product to DB & update
public class EditorActivity extends AppCompatActivity implements EditorContract.IView {

    private static final int PICK_IMAGE = 101;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    private Button addBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private Button selectImageBtn;

    private Product product;
    private EditText productNameEdt;
    private EditText productQuantityEdt;
    private EditText productPriceEdt;
    private EditText productSupplierEdt;
    private EditorController controller;
    private ImageView selectImageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        controller = new EditorController(this, getBaseContext());
        setUi();
    }

    private void setUi() {
        addBtn = (Button) findViewById(R.id.add_btn);
        updateBtn = (Button) findViewById(R.id.update_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);
        selectImageBtn = (Button) findViewById(R.id.select_image);
        productNameEdt = (EditText) findViewById(R.id.product_name);
        productQuantityEdt = (EditText) findViewById(R.id.product_qunatity);
        productPriceEdt = (EditText) findViewById(R.id.product_Price);
        productSupplierEdt = (EditText) findViewById(R.id.product_supplier);
        selectImageIV = (ImageView) findViewById(R.id.product_image);
    }


    //get product attributes from ui into product object
    private void getProductFromUi() {
        String quantity = productQuantityEdt.getText().toString();
        Bitmap bitmap = null;

        if (selectImageIV.getDrawable() != null)
            bitmap = ((BitmapDrawable) selectImageIV.getDrawable()).getBitmap();

        if (productNameEdt.getText().toString().length() == 0) {
            showToast("Please enter product name");
        } else if (bitmap != null) {
            showToast("Please select product image");
        } else {
            product = new Product(productNameEdt.getText().toString(),
                    Integer.parseInt(quantity),
                    productPriceEdt.getText().toString(),
                    productSupplierEdt.getText().toString(),
                    DbBitmapUtility.getBytes(bitmap));
        }

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addProductBtn(View view) {
        getProductFromUi();

        if (product.getProductName() == null)
            showToast("Please enter product name");
        else if (product.getProductImage() == null)
            showToast("Please select product image");
        else
            controller.addProduct(product);
    }

    @Override
    public void deleteProductBtn(View view) {
        getProductFromUi();
        controller.deleteProduct(product);
    }

    @Override
    public void updateProductBtn(View view) {
        getProductFromUi();
        controller.updateProduct(product);
    }

    @Override
    public void selectImageBtn(View view) {
        CheckPermissionToOpenImageSelector();
    }

    @Override
    public void decreaseQuantityBtn(View view) {

        String quantityStr = productQuantityEdt.getText().toString();
        if (quantityStr.length() == 0) {
            quantityStr = "0";
        }

        Integer cnt = Integer.parseInt(quantityStr);

        if (cnt > 0)
            --cnt;

        productQuantityEdt.setText(String.valueOf(cnt));
    }

    @Override
    public void increaseQuantityBtn(View view) {

        String quantityStr = productQuantityEdt.getText().toString();
        if (quantityStr.length() == 0) {
            quantityStr = "0";
        }

        Integer cnt = Integer.parseInt(quantityStr);

        ++cnt;

        productQuantityEdt.setText(String.valueOf(cnt));

    }


    public void CheckPermissionToOpenImageSelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }
        openImageSelector();
    }

    private void openImageSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageSelector();
                    // permission was granted
                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            Uri imageUri = data.getData();

            if (imageUri != null) {

                selectImageIV.setImageURI(imageUri);
            }
        }
    }

}

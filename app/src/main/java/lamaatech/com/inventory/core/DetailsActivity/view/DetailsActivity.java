package lamaatech.com.inventory.core.DetailsActivity.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lamaatech.com.inventory.R;
import lamaatech.com.inventory.core.DetailsActivity.controller.DetailsController;
import lamaatech.com.inventory.core.DetailsActivity.model.DetailsContract;
import lamaatech.com.inventory.database.DbBitmapUtility;
import lamaatech.com.inventory.models.Product;


//this Activity for add new product to DB & update
public class DetailsActivity extends AppCompatActivity implements DetailsContract.IView {

    private static final int PICK_IMAGE = 101;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    private Product product;
    @BindView(R.id.productNameEditText)
    protected EditText productNameEditText;
    @BindView(R.id.productQuantityEditText)
    protected EditText productQuantityEditText;
    @BindView(R.id.orderQuantityEditText)
    protected EditText orderQuantityEditText;
    @BindView(R.id.productPriceEditText)
    protected EditText productPriceEditText;
    @BindView(R.id.productSupplierEditText)
    protected EditText productSupplierEditText;
    @BindView(R.id.supplierEmailEditText)
    protected EditText supplierEmailEditText;
    private DetailsController controller;
    @BindView(R.id.productImageView)
    protected ImageView productImageView;
    @BindView(R.id.newOrderLayout)
    protected LinearLayout newOrder;
    @BindView(R.id.deleteProductButton)
    protected Button deleteProductButton;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        controller = new DetailsController(this, getBaseContext());

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Product item = (Product) extras.getSerializable("product");

            product = item;
            if (item != null) {
                setValuesToView(item);
                newOrder.setVisibility(View.VISIBLE);
                deleteProductButton.setVisibility(View.VISIBLE);
            }
        }
    }


    @OnClick(R.id.sendEmailButton)
    protected void onClickSendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", product.getSupplierEmail(), null));

        intent.putExtra(Intent.EXTRA_SUBJECT, "New Order");
        intent.putExtra(Intent.EXTRA_TEXT, controller.getMailMessage(product, orderQuantityEditText.getText().toString()));
        startActivity(intent);
    }

    private void setValuesToView(Product item) {
        productNameEditText.setText(item.getProductName());
        productQuantityEditText.setText(String.valueOf(item.getProductQuantity()));
        supplierEmailEditText.setText(item.getSupplierEmail());
        productPriceEditText.setText(item.getProductPrice());
        productSupplierEditText.setText(item.getProductSupplier());
        if (item.getProductImage() != null)
            productImageView.setImageBitmap(DbBitmapUtility.getImage(item.getProductImage()));
    }

    //get product attributes from ui into product object
    private void getProductFromUi() {

        Bitmap bitmap = null;

        String productName = productNameEditText.getText().toString();
        if (productName.length() > 0) {
            String price = productPriceEditText.getText().toString();
            if (price.length() > 0) {
                String supplierName = productSupplierEditText.getText().toString();
                if (supplierName.length() > 0) {
                    String supplierEmail = supplierEmailEditText.getText().toString();
                    if (supplierEmail.length() > 0 && supplierEmail.contains("@")) {
                        if (productImageView.getDrawable() != null) {
                            bitmap = ((BitmapDrawable) productImageView.getDrawable()).getBitmap();

                            Integer quantity = Integer.parseInt(productQuantityEditText.getText().toString());
                            Integer productId = -1;
                            if (product != null)
                                productId = product.getProductId();

                            if (quantity > 0)
                                product = new Product(productName,
                                        quantity,
                                        price,
                                        supplierName,
                                        DbBitmapUtility.getBytes(bitmap),
                                        supplierEmail);
                            else
                                showToast("Please enter quantity");

                            product.setProductId(productId);

                        } else {
                            showToast("Please enter product photo");
                        }
                    } else {
                        showToast("Please enter supplier email");
                    }
                } else {
                    showToast("Please enter supplier name");
                }
            } else {
                showToast("Please enter price");
            }
        } else {
            showToast("Please enter product name");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.addProductButton)
    @Override
    public void addProductBtn(View view) {
        getProductFromUi();
        if (product != null)
            controller.addProduct(product);
    }

    @OnClick(R.id.deleteProductButton)
    @Override
    public void deleteProductBtn(View view) {
        showPrompet();
    }

    private void showPrompet() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        controller.deleteProduct(product);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void updateProductBtn(View view) {
        getProductFromUi();
        controller.updateProduct(product);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick(R.id.selectImageButton)
    @Override
    public void selectImageBtn(View view) {
        CheckPermissionToOpenImageSelector();
    }

    @OnClick(R.id.decreaseQuantityButton)
    @Override
    public void decreaseQuantityBtn(View view) {

        String quantityStr = productQuantityEditText.getText().toString();
        if (quantityStr.length() == 0) {
            quantityStr = "0";
        }

        Integer cnt = Integer.parseInt(quantityStr);

        if (cnt > 0)
            --cnt;

        productQuantityEditText.setText(String.valueOf(cnt));
    }

    @OnClick(R.id.increaseQuantityButton)
    @Override
    public void increaseQuantityBtn(View view) {

        String quantityStr = productQuantityEditText.getText().toString();
        if (quantityStr.length() == 0) {
            quantityStr = "0";
        }

        Integer cnt = Integer.parseInt(quantityStr);

        ++cnt;

        productQuantityEditText.setText(String.valueOf(cnt));

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

                productImageView.setImageURI(imageUri);
            }
        }
    }
}

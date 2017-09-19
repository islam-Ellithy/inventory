package lamaatech.com.inventory.core.MainActivity.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lamaatech.com.inventory.R;
import lamaatech.com.inventory.database.DbBitmapUtility;
import lamaatech.com.inventory.models.Product;


public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final ProductFragment.OnListFragmentInteractionListener mListener;

    public ProductRecyclerViewAdapter(List<Product> items, ProductFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(holder.mItem.getProductName());
        holder.mContentView.setText(holder.mItem.getProductPrice() + "$");
        holder.mQuantityView.setText(String.valueOf(holder.mItem.getProductQuantity()));
        if (holder.mItem.getProductImage() != null)
            holder.mProductImage.setImageBitmap(DbBitmapUtility.getImage(holder.mItem.getProductImage()));
        else
            holder.mProductImage.setImageResource(R.drawable.ic_announcement);

        holder.sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = holder.mItem.getProductQuantity();
                if (quantity > 0) {
                    quantity--;
                    holder.mItem.setProductQuantity(quantity);
                    holder.mQuantityView.setText(String.valueOf(quantity));
                    mListener.onListFragmentInteraction(holder.mItem, true);
                }

            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mQuantityView;
        public final ImageView mProductImage;
        public Product mItem;
        public final Button sellButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.productName);
            mContentView = (TextView) view.findViewById(R.id.content);
            mQuantityView = (TextView) view.findViewById(R.id.quantity);
            mProductImage = (ImageView) view.findViewById(R.id.productImageView);
            sellButton = (Button) view.findViewById(R.id.sellButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

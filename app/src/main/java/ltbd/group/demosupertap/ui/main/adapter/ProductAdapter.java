package ltbd.group.demosupertap.ui.main.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ltbd.group.demosupertap.databinding.ItemProductBinding;
import ltbd.group.demosupertap.models.Product;

/**
 * Created by ltbd on 9/26/20.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.LocalViewHolder> {
    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity) parent.getContext()).getLayoutInflater();
        return new LocalViewHolder(ItemProductBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.imvAvatar.setImageResource(product.getImage());
        holder.binding.tvName.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        if (products != null) return products.size();
        return 0;
    }

    static class LocalViewHolder extends RecyclerView.ViewHolder {
        private ItemProductBinding binding;

        public LocalViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

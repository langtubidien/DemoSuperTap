package ltbd.group.demosupertap.ui.main.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ltbd.group.demosupertap.databinding.ItemNewfunctionBinding;
import ltbd.group.demosupertap.models.NewFunction;

/**
 * Created by ltbd on 9/26/20.
 */
public class NewFunctionAdapter extends RecyclerView.Adapter<NewFunctionAdapter.LocalViewHolder> {
    private List<NewFunction> newFunctions;

    public NewFunctionAdapter(List<NewFunction> newFunctions) {
        this.newFunctions = newFunctions;
    }

    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = ((Activity) parent.getContext()).getLayoutInflater();
        return new NewFunctionAdapter.LocalViewHolder(ItemNewfunctionBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, int position) {
        NewFunction newFunction = newFunctions.get(position);
        holder.binding.imvAvatar.setImageResource(newFunction.getImage());
        holder.binding.tvName.setText(newFunction.getName());
        holder.binding.tvDes.setText(newFunction.getDescription());
    }

    @Override
    public int getItemCount() {
        if (newFunctions != null) return newFunctions.size();
        return 0;
    }

    static class LocalViewHolder extends RecyclerView.ViewHolder {
        private ItemNewfunctionBinding binding;

        LocalViewHolder(@NonNull ItemNewfunctionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

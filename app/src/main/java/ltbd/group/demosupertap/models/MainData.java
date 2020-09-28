package ltbd.group.demosupertap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ltbd.group.demosupertap.models.NewFunction;
import ltbd.group.demosupertap.models.Product;

/**
 * Created by ltbd on 9/26/20.
 */
public class MainData {
    @SerializedName("products")
    @Expose
    private List<Product> products;

    @SerializedName("newFunctions")
    @Expose
    private List<NewFunction> newFunctions;

    public MainData(List<Product> products, List<NewFunction> newFunctions) {
        this.products = products;
        this.newFunctions = newFunctions;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<NewFunction> getNewFunctions() {
        return newFunctions;
    }

    public void setNewFunctions(List<NewFunction> newFunctions) {
        this.newFunctions = newFunctions;
    }
}

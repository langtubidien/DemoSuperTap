package ltbd.group.demosupertap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ltbd on 9/26/20.
 */
public class Product {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("image")
    @Expose
    private int image;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("isFeature")
    @Expose
    private boolean isFeature;

    public Product(int id, int image, String name, boolean isFeature) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.isFeature = isFeature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFeature() {
        return isFeature;
    }

    public void setFeature(boolean feature) {
        isFeature = feature;
    }
}

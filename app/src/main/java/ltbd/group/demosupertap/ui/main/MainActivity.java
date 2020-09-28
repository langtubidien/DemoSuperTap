package ltbd.group.demosupertap.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ltbd.group.demosupertap.Base.BaseActivity;
import ltbd.group.demosupertap.databinding.ActivityMainBinding;
import ltbd.group.demosupertap.models.MainData;
import ltbd.group.demosupertap.models.NewFunction;
import ltbd.group.demosupertap.models.Product;
import ltbd.group.demosupertap.ui.main.adapter.NewFunctionAdapter;
import ltbd.group.demosupertap.ui.main.adapter.ProductAdapter;
import ltbd.group.demosupertap.ui.map.MapContainerActivity;
import ltbd.group.demosupertap.utils.Constant;
import ltbd.group.demosupertap.utils.Utils;

/**
 * Created by ltbd on 9/26/20.
 */
public class MainActivity extends BaseActivity implements MainContract.View {
    private MainContract.Presenter presenter;

    private ActivityMainBinding binding;

    private ProductAdapter productAdapter;
    private ProductAdapter featureAdapter;
    private NewFunctionAdapter newFunctionAdapter;

    private List<NewFunction> newFunctions = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Product> featuredProducts = new ArrayList<>();
    //
    private final String dumpName = "Minh";
    private final LatLng haNoiLocation = new LatLng(20.86, 106.68);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        addAction();
    }

    @Override
    protected void beforeSetContentView() {
        presenter = new MainPresenter();
        presenter.attachView(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected View getContentView() {
        return binding.getRoot();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onFetchDataSuccess(MainData data) {
        hideProgressbar();
        setProductList(data);
        setFeatureList(data);
        setNewFunctionList(data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.MAP_LOCATION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null)
                setUpInformationFormLongLat(data.getDoubleExtra(Constant.Key.KEY_LONG, 0.0), data.getDoubleExtra(Constant.Key.KEY_LAT, 0.0));
        }
    }

    @Override
    public void onFetchDataError() {
        hideProgressbar();
    }

    private void initView() {

        binding.rcyFeatured.setLayoutManager(new GridLayoutManager(this, 3));
        featureAdapter = new ProductAdapter(featuredProducts);
        binding.rcyFeatured.setAdapter(featureAdapter);

        binding.rcyProduct.setLayoutManager(new GridLayoutManager(this, 3));
        productAdapter = new ProductAdapter(products);
        binding.rcyProduct.setAdapter(productAdapter);

        binding.rcyNewFuntion.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        newFunctionAdapter = new NewFunctionAdapter(newFunctions);
        binding.rcyNewFuntion.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                final int space = 20;
                outRect.left = space;
                outRect.right = space;
                outRect.top = space;
                outRect.bottom = space;
            }
        });
        binding.rcyNewFuntion.setAdapter(newFunctionAdapter);
        String hello = Utils.getHelloString();
        Spannable wordtoSpan = new SpannableString(hello + "," + dumpName);
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLACK), (wordtoSpan.length() - dumpName.length()) - 1, wordtoSpan.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.tvHello.setText(wordtoSpan);
        binding.tvPosition.setText(getAddressFormLatLong(haNoiLocation.longitude, haNoiLocation.latitude));
    }

    private void initData() {
        showProgressbar();
        presenter.getFetchData();
    }

    private void addAction() {
        binding.tvPosition.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapContainerActivity.class);
            startActivityForResult(intent, Constant.RequestCode.MAP_LOCATION_REQUEST_CODE);
        });
    }

    private void setProductList(MainData data) {
        products.addAll(data.getProducts());
        productAdapter.notifyDataSetChanged();
    }

    private void setFeatureList(MainData data) {
        featuredProducts.addAll(getDataFeatured(data));
        featureAdapter.notifyDataSetChanged();
    }

    private void setNewFunctionList(MainData data) {
        newFunctions.addAll(data.getNewFunctions());
        newFunctionAdapter.notifyDataSetChanged();
    }

    private List<Product> getDataFeatured(MainData data) {
        List<Product> productList = data.getProducts();
        List<Product> tempFeaturedProduct = new ArrayList<>();
        if (productList != null) {
            for (Product product : productList) {
                if (product.isFeature()) {
                    tempFeaturedProduct.add(product);
                }
            }
        }
        return tempFeaturedProduct;
    }

    private void setUpInformationFormLongLat(double longT, double latT) {
        runOnUiThread(() -> binding.tvPosition.setText(getAddressFormLatLong(longT, latT)));
    }

    private String getAddressFormLatLong(double longT, double latT) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latT, longT, 1);
            return addresses.get(0).getAddressLine(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void showProgressbar() {

    }

    private void hideProgressbar() {

    }
}

package ltbd.group.demosupertap.ui.map;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import ltbd.group.demosupertap.Base.BaseActivity;
import ltbd.group.demosupertap.R;
import ltbd.group.demosupertap.databinding.AcitivtyMapBinding;
import ltbd.group.demosupertap.utils.Constant;
import ltbd.group.demosupertap.utils.PermissionUtils;

import static ltbd.group.demosupertap.utils.Constant.RequestCode.LOCATION_PERMISSION_REQUEST_CODE;

/**
 * Created by ltbd on 9/28/20.
 */
public class MapContainerActivity extends BaseActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnCameraMoveListener {
    private AcitivtyMapBinding binding;
    private final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private GoogleMap map;
    private Marker perth;
    private boolean permissionDenied = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMap();
        addAction();
    }

    @Override
    protected void beforeSetContentView() {
        binding = AcitivtyMapBinding.inflate(getLayoutInflater());
    }

    @Override
    protected View getContentView() {
        return binding.getRoot();
    }

    private void initMap() {
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);
    }

    private void addAction() {
        binding.btnConfirm.setOnClickListener(v -> {
            confirmPosition();
        });
    }

    private void confirmPosition() {
        if (map == null) return;
        LatLng latLng = map.getCameraPosition().target;
        Intent intent = new Intent();
        intent.putExtra(Constant.Key.KEY_LONG, latLng.longitude);
        intent.putExtra(Constant.Key.KEY_LAT, latLng.latitude);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        final LatLng perthLocation = new LatLng(20.86, 106.68);
        perth = map.addMarker(
                new MarkerOptions()
                        .position(perthLocation)
                        .flat(true)
                        .draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLng(perthLocation));
        perth.setTag(0);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        map.setOnCameraMoveListener(this);
        enableMyLocation();
        // Set a listener for marker click.
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    @Override
    public void onCameraMove() {
        if (perth != null) {
            perth.remove();
        }
        //Assign mCenterMarker reference:
        perth = map.addMarker(new MarkerOptions().position(map.getCameraPosition().target).anchor(0.5f, .05f));
    }
}

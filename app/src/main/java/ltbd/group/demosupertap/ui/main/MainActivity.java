package ltbd.group.demosupertap.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ltbd.group.demosupertap.Base.BaseActivity;
import ltbd.group.demosupertap.R;
import ltbd.group.demosupertap.databinding.ActivityMainBinding;
import ltbd.group.demosupertap.ui.products.HomeFragment;

/**
 * Created by ltbd on 9/26/20.
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding.navigation.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_my_jobs:
                    return true;
                case R.id.navigation_support:
                    return true;
                case R.id.navigation_news:
                    return true;
                case R.id.navigation_account:
                    return true;
            }
            return false;
        });
        binding.navigation.setSelectedItemId(R.id.navigation_home);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void beforeSetContentView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected View getContentView() {
        return binding.getRoot();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

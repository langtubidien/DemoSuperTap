package ltbd.group.demosupertap.Base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by ltbd on 9/26/20.
 */
public  abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(getContentView());
    }

    protected abstract void beforeSetContentView();

    protected abstract View getContentView();
}

package innovativepocket.com.systemalertwindow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Activity mActivity;
    private EditText edt1;
    private TextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;
        edt1 = (EditText) findViewById(R.id.edt1);
        tvValue = (TextView) findViewById(R.id.tvValue);
        Button btnStartService = (Button) findViewById(R.id.btnStartService);
        Button btnClose = (Button) findViewById(R.id.btnClose);

        edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvValue.setText(edt1.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.finish();
            }
        });

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDrawOverlayPermission();
            }
        });

    }

    public final static int Overlay_REQUEST_CODE = 251;
    public void checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(mActivity)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, Overlay_REQUEST_CODE);
            } else {
                openFloatingWindow();
            }
        } else {
            openFloatingWindow();
        }
    }

    private void openFloatingWindow() {
            Intent intent = new Intent(mActivity, FloatingWindow.class);
            mActivity.stopService(intent);
            mActivity.startService(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Overlay_REQUEST_CODE: {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (Settings.canDrawOverlays(mActivity)) {
                        openFloatingWindow();
                    }
                } else {
                    openFloatingWindow();
                }
                break;
            }
        }
    }

}

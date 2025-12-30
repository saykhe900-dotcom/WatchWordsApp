package com.watchwords.app;

import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText etWords, etAlertText;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWords = findViewById(R.id.etWords);
        etAlertText = findViewById(R.id.etAlert);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(v -> {
            if (!isAccessibilityOn()) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            }
        });

        btnStop.setOnClickListener(v -> {
            // to stop, user must disable service from Accessibility settings
        });
    }

    private boolean isAccessibilityOn() {
        String enabledServices = Settings.Secure.getString(
                getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);

        if (enabledServices != null && enabledServices.toLowerCase()
                .contains(getPackageName().toLowerCase())) {
            return true;
        }
        return false;
    }
}

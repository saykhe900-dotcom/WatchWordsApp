package com.watchwords.app;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.text.TextUtils;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;

public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        CharSequence text = event.getText().toString();
        if (TextUtils.isEmpty(text)) return;

        String savedWords = Prefs.getInstance(this).getWords();
        String alertText = Prefs.getInstance(this).getAlertText();

        if (!TextUtils.isEmpty(savedWords)){
            String[] words = savedWords.split(",");
            for (String w : words){
                if (text.toString().toLowerCase().contains(w.toLowerCase().trim())){
                    showNotification(alertText);
                    break;
                }
            }
        }
    }

    @Override
    public void onInterrupt() {}

    void showNotification(String msg){
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        String channel = "watch_words";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            nm.createNotificationChannel(new NotificationChannel(channel,"Watch Words", NotificationManager.IMPORTANCE_HIGH));
        }
        NotificationCompat.Builder b = new NotificationCompat.Builder(this, channel)
                .setContentTitle("تنبيه كلمة")
                .setContentText(msg)
                .setSmallIcon(android.R.drawable.ic_dialog_info);

        nm.notify((int)System.currentTimeMillis(), b.build());
    }
}

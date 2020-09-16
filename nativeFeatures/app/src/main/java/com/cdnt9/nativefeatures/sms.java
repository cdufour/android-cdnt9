package com.cdnt9.nativefeatures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;

import java.net.URI;

public class sms extends AppCompatActivity {
    private int READ_SMS_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        testPermissions();
    }

    private void testPermissions() {
        if (ContextCompat.checkSelfPermission(
                sms.this,
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("Can read sms");
            loadSMS();
        } else {
            System.out.println("Cannot read sms");
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CONTACTS}, READ_SMS_CODE);
        }
    }

    private void loadSMS() {
        System.out.println("loadSMS");
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver
                .query(Uri.parse("content://sms"), null, null, null, null);

        System.out.println(cursor.getCount() + " sms en base de données");
        cursor.moveToFirst();
        String smsDate = cursor.getString(cursor.getColumnIndex("date"));
        String smsBody = cursor.getString(cursor.getColumnIndex("body"));
        System.out.println(smsDate + " => " + smsBody);
    }
}
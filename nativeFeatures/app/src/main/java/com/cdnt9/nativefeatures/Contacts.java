package com.cdnt9.nativefeatures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.view.View;

public class Contacts extends AppCompatActivity {
    private int READ_CONTACT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        testPermissions();
    }

    private void testPermissions() {
        if (ContextCompat.checkSelfPermission(
                Contacts.this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("Can read contacts");
            loadContacts();
        } else {
            System.out.println("Cannot read contacts");
            // Demande d'activation de la permission de lire les contacts
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CONTACTS}, READ_CONTACT_CODE);
        }
    }

    private void loadContacts() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        System.out.println(uri); // content://com.android.contacts/contacts

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        //cursor.moveToFirst();
        //String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        //System.out.println(name);

        while (cursor.moveToNext()) { // tant qu'il y a des contacts
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            System.out.println(name);
        }
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // autorisé
            startActivity(intent);
        }
    }

    public void triggerAlarm(View view) {
        createAlarm("Coucou", 15, 30);
    }
}
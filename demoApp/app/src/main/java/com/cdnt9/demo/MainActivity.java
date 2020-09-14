package com.cdnt9.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int clickCount = 0;

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int newValue) {
        clickCount = newValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // définit le layout (mise en page de cette vue)
        setContentView(R.layout.activity_main);

        // Ciblage de l'UI
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Bonjour à vous");
        textView.setAllCaps(true);

        Button button = (Button) findViewById(R.id.btnTest);

        // Ecoute evénementielle
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //boolean isAllCaps = textView.isAllCaps() # méthode ajoutée dans API 28, inutisable en 16
                textView.setText("Modified");
                //clickCount += 1; // incrémente la propriété
                int nbClick = getClickCount();
                setClickCount(nbClick + 1);

                if (getClickCount() > 5) {
                    textView.setText("Stop");

                    // changement d'activité
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                }


            }
        });

    }
}
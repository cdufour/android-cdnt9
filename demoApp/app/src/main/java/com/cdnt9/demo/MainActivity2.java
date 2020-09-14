package com.cdnt9.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView; // priopriété publique accessible par toutes les méthodes
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        textView = (TextView) findViewById(R.id.textView2);
        Button button = (Button) findViewById(R.id.btnAddFruit);

        //final String[] fruits = {"Poire", "Cerise", "Orange"};

        Fruit poire = new Fruit("Poire", false, 20);
        Fruit grenade = new Fruit("Grenade", true, 28);
        Fruit cerise = new Fruit("Cerise", false, 50);

        final Fruit[] fruits = {poire, grenade, cerise};


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText() + fruits[counter].getName());
                counter++;

                if (counter == fruits.length) counter = 0;
            }
        });



    }
}
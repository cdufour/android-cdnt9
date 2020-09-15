package com.cdnt9.simplemathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvOp1;
    TextView tvOp2;
    TextView tvCorrect;
    TextView tvResult;
    TextView tvPoints;
    EditText inpRes;
    Button btnCheck;
    Button btnLevel;

    int points = 0;
    boolean next = false;
    int levelIndex = 0;
    String[] levels = {"Facile", "Moyen", "Difficile"};

    private void initViews() {
        tvOp1       = (TextView) findViewById(R.id.tvOp1);
        tvOp2       = (TextView) findViewById(R.id.tvOp2);
        tvCorrect   = (TextView) findViewById(R.id.tvCorrect);
        btnCheck    = (Button) findViewById(R.id.btnCheck);
        tvResult    = (TextView) findViewById(R.id.tvCorrect);
        inpRes      = (EditText) findViewById(R.id.inpRes);
        tvPoints    = (TextView) findViewById(R.id.tvPoints);
        btnLevel    = (Button) findViewById(R.id.btnLevel);

        inpRes.setText("");
        tvCorrect.setText("");
        tvPoints.setText("0 point");
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private void setRandomValues() {
        int coef = (int) Math.pow(10, levelIndex);
        int r = randInt(0, 10 * coef);
        tvOp1.setText(Integer.toString(r));
        r = randInt(0, 10 * coef);
        tvOp2.setText(Integer.toString(r));
    }

    private int computeTotal(TextView tv1, TextView tv2) {
        int intOp1 = Integer.parseInt(tv1.getText().toString());
        int intOp2 = Integer.parseInt(tv2.getText().toString());
        return intOp1 + intOp2;
    }

    private void showSolution() {
        tvCorrect.setText(Integer.toString(computeTotal(tvOp1, tvOp2)));
        tvCorrect.setAlpha(1);
    }

    private void clear() {
        inpRes.setText("");
        inpRes.clearFocus();
        tvCorrect.setText("");
        tvCorrect.setAlpha(0);
    }

    private void computeGrade() {
        int solution = computeTotal(tvOp1, tvOp2);
        int playerAns = Integer.parseInt(inpRes.getText().toString());

        if (playerAns == solution) {
            Log.i("Result", "Good");
            points++;
            tvPoints.setText(Integer.toString(points) + " points");
        } else {
            showSolution();
        }
    }

    private void changeBtn() {
        String newText = (next == true) ? "Vérifier" : "Suivant";
        btnCheck.setText(newText);
        next = !next;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setRandomValues();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inpRes.getText().length() == 0) return;
                if (!TextUtils.isDigitsOnly(inpRes.getText())) return;

                if (next) {
                    clear();
                    setRandomValues();
                } else {
                    computeGrade();
                }
                changeBtn();
            }
        });

        btnLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (levelIndex == 2) {
                    levelIndex = 0;
                } else {
                    levelIndex++;
                }
                btnLevel.setText(levels[levelIndex]);
            }
        });


    }
}
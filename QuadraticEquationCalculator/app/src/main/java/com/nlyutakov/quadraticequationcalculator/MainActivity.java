package com.nlyutakov.quadraticequationcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText aNumber = findViewById(R.id.aBox);
        EditText bNumber = findViewById(R.id.bBox);
        EditText cNumber = findViewById(R.id.cBox);

        TextView x1Result = findViewById(R.id.x1ResultBox);
        TextView x2Result = findViewById(R.id.x2ResultBox);
        TextView dResult = findViewById(R.id.dResultBox);




        Button btnCalc=findViewById(R.id.button);

        btnCalc.setOnClickListener(view -> {
            int a = 1;
            int b = 1;
            int c = 1;

            if(aNumber.getText().length() != 0) {
                a = Integer.parseInt(aNumber.getText().toString());
            }
            if(bNumber.getText().length() != 0) {
                b = Integer.parseInt(bNumber.getText().toString());
            }
            if(cNumber.getText().length() != 0){
                c = Integer.parseInt(cNumber.getText().toString());
            }

            double x1 = 0;
            double x2 = 0;
            boolean flag = false;

            double d = (b * b) - (4 * a * c);

            if (d >= 0) {
                d = Math.sqrt(d);
                x1 = (-b + d) / (2 * a);
                x2 = (-b - d) / (2 * a);
            }
            else {
                flag = true;
            }

            Formatter x1Formatter = new Formatter().format("%.2f", x1);
            Formatter x2Formatter = new Formatter().format("%.2f", x2);
            Formatter dFormatter = new Formatter().format("%.2f", d);


            if (!flag) {
                x1Result.setText(x1Formatter.toString());
                if (x1 != x2) {
                    x2Result.setText(x2Formatter.toString());
                }
                else {
                    x2Result.setText(getString(R.string.oneRootOnly));
                }
                dResult.setText(dFormatter.toString());
            }
            else {
                x1Result.setText(getString(R.string.noRealRoots));
                x2Result.setText(getString(R.string.noRealRoots));
                dResult.setText(dFormatter.toString());
            }
        });
    }
}
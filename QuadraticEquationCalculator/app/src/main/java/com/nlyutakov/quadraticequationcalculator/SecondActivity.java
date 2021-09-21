package com.nlyutakov.quadraticequationcalculator;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;

import java.util.ArrayList;

public class SecondActivity extends DBActivity {
    Button btnBack;
    protected ListView simpleList;
    protected void FillListView() throws Exception{
        final ArrayList<String> listResults=new ArrayList<>();
        SelectSQL("SELECT * FROM EQUATION ORDER BY Date DESC", null,
                new OnSelectSuccess() {
                    @Override
                    public void OnElementSelected(String ID, String Equation, String Date) {
                        listResults.add(ID+"\t"+Equation);
                    }
                }
        );
        simpleList.clearChoices();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.activity_listview,
                R.id.textView,
                listResults
        );
        simpleList.setAdapter(arrayAdapter);
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try{
            FillListView();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        simpleList=findViewById(R.id.simpleList);

        btnBack=findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected="";
                TextView clickedText=view.findViewById(R.id.textView);
                selected=clickedText.getText().toString();
                String[] elements = selected.split("\t");
                String ID=elements[0];
                String Equation=elements[1];

                Intent intent=new Intent(SecondActivity.this,
                        Delete.class
                );

                Bundle b=new Bundle();
                b.putString("ID", ID);
                b.putString("Equation", Equation);
                intent.putExtras(b);
                startActivityForResult(intent, 200, b);

            }
        });

        try {

            FillListView();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "exception: "+e.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}

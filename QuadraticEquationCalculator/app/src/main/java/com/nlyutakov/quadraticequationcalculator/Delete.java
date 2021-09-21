package com.nlyutakov.quadraticequationcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends DBActivity{

    protected TextView result;
    protected Button btnDelete;
    protected String ID="";
    protected void BackToSecond(){
        finishActivity(200);
        Intent i=new Intent(Delete.this,
                SecondActivity.class
        );
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        result=findViewById(R.id.result);
        btnDelete=findViewById(R.id.btnDelete);
        Bundle b=getIntent().getExtras();
        if(b!=null){
            ID=b.getString("ID");
            result.setText(b.getString("Equation"));
        }

        btnDelete.setOnClickListener(view -> {
            try{
                ExecSQL(
                        "DELETE FROM EQUATION  " +
                                "WHERE ID = ?",
                        new Object[]{
                                ID
                        },
                        ()-> Toast.makeText(getApplicationContext(),
                                "Delete Successful", Toast.LENGTH_LONG
                        ).show()

                );
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),
                        "DELETE ERROR: "+e.getLocalizedMessage(),
                        Toast.LENGTH_LONG).show();
            }
            BackToSecond();
        });
    }
}

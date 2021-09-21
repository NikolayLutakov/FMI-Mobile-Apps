package com.nlyutakov.quadraticequationcalculator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class DBActivity extends AppCompatActivity {
    protected void SelectSQL(String SelectQ, String[] args,
                             OnSelectSuccess success
    )
            throws Exception {
        SQLiteDatabase db =
                SQLiteDatabase.openOrCreateDatabase(
                        getFilesDir().getPath() + "/equation.db", null
                );
        Cursor cursor = db.rawQuery(SelectQ, args);
        while (cursor.moveToNext()) {
            String ID = cursor.getString(cursor.getColumnIndex("ID"));
            String Equation = cursor.getString(cursor.getColumnIndex("Equation"));
            String Date = cursor.getString(cursor.getColumnIndex("Date"));
            success.OnElementSelected(ID, Equation, Date);
        }
        db.close();

    }

    protected void ExecSQL(String SQL, Object[] args, OnQuerySuccess success)
            throws Exception {
        SQLiteDatabase db =
                SQLiteDatabase.openOrCreateDatabase(
                        getFilesDir().getPath() + "/equation.db", null
                );
        if (args != null)
            db.execSQL(SQL, args);
        else
            db.execSQL(SQL);
        db.close();
        success.OnSuccess();
    }

    protected void initDB() throws Exception {
        ExecSQL(

                "CREATE TABLE if not exists EQUATION( " +
                        "ID integer PRIMARY KEY AUTOINCREMENT, " +
                        "Equation text not null, " +
                        "Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");",
                null,
                () -> Toast.makeText(getApplicationContext(), "DB init successful",
                        Toast.LENGTH_SHORT).show()

        );
    }

    protected interface OnQuerySuccess {
        public void OnSuccess();
    }

    protected interface OnSelectSuccess {
        public void OnElementSelected(String ID, String Equation, String Date);
    }
}

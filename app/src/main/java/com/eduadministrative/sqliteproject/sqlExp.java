package com.eduadministrative.sqliteproject;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.database.databaseHelper;
import com.database.new_databaseHelper;

public class sqlExp extends AppCompatActivity {

    new_databaseHelper myDb;

    EditText txtNm, txtSurNm;
    Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_exp);

        myDb = new new_databaseHelper(this);

        txtNm = (EditText) findViewById(R.id.txtNm);
        txtSurNm = (EditText) findViewById(R.id.txtSurNm);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(txtNm.getText().toString(), txtSurNm.getText().toString());
                if (isInserted == true)
                    Toast.makeText(sqlExp.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(sqlExp.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Surname :" + res.getString(2) + "\n \n");
                }

                // Show all data
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}
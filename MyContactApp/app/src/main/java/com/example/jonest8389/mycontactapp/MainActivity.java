package com.example.jonest8389.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editAddress = findViewById(R.id.editText_address);
        editPhone = findViewById(R.id.editText_phone);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp","MainActivity: instantiated myDb");

    }

    public void addData(View v){
        Log.d("MyContactApp","MainActivity: Add contact button pressed");

        String insert = editName.getText().toString() + " " + editAddress.getText().toString() + " " + editPhone.getText().toString();
        boolean isInserted = myDb.insertData(insert);

        if (isInserted) {
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "FAILED - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData (View v) {
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp","MainActivity: viewData: recieved cursor");

        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0;i < res.getCount();i++){
            buffer.append(res.getColumnName(i) + "\n");
            //Append res column 0,1,2,3 to the buffer - see StringBuffer and Cursor api's
            //Delimit each of the "appends" with line feed "\n"
        }

        showMessage("Data", buffer.toString());

    }

    private void showMessage(String title, String message) {
        Log.d("MyContactApp","MainActivity: showMessage: assembling AlertDialog [title " + title + " message " + message + "]");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static final String EXTRA_MESSAGE = "com.example.jonest8389.mycontactapp.MESSAGE";
    public void searchRecord(View v){
        Log.d("MyContactApp","MainActivity: launching SearchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, editName.getText().toString());
        startActivity(intent);

    }

}

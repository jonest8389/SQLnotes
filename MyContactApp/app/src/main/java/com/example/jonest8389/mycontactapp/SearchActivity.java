package com.example.jonest8389.mycontactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get the intent that started the activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Set the string in TextView
        TextView textView = findViewById(R.id.textView_search);
        textView.setText(message);

    }

    public void goBack(View v){
        Log.d("MyContactApp","SearchActivity: launching MainActivity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

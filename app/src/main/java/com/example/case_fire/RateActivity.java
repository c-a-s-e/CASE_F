package com.example.case_fire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        getSupportActionBar().hide();
    }

    public void onButtonClicked(View v){
        Intent intent = new Intent(this, RewardActivity.class);
        startActivity(intent);
    }
}

package com.example.case_fire;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ArrivalActivity extends AppCompatActivity {

    public static String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);
        getSupportActionBar().hide();
    }

    @SuppressLint("MissingPermission")
    public void callButtonClicked(View v){
        try {
            if (phoneNumber==null){
                Toast.makeText(getApplicationContext(), "번호가 없어서 전화를 걸 수 없습니다", Toast.LENGTH_SHORT);
                return;
            }
            Log.d("phoneNumber", phoneNumber);
            Intent tt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(tt);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }

    public void arrivedButtonClicked(View v){
        Intent intent = new Intent(this, RateActivity.class);
        startActivity(intent);
    }
}

package com.example.case_fire;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class CallUtil {
    private static String phoneNumber;

    public static void setPhoneNumber(String phoneNumber) {
        CallUtil.phoneNumber = phoneNumber;
    }

    @SuppressLint("MissingPermission")
    public static void phoneCall(Activity activity, Context context) {
        try {
            if (phoneNumber==null){
                Toast.makeText(context, "번호가 없어서 전화를 걸 수 없습니다", Toast.LENGTH_SHORT);
                return;
            }
            Log.d("phoneNumber", phoneNumber);
            Intent tt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            activity.startActivity(tt);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

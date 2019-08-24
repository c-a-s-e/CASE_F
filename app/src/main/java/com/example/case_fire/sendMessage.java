package com.example.case_fire;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sendMessage {
    private static RequestQueue queue;

    public static void sendAccept(Context context, String user_token) {
        JSONObject acceptData = new JSONObject();
        try {
            acceptData.put("priority", "high");
            acceptData.put("to", user_token);

            JSONObject dataObj = new JSONObject();
            dataObj.put("type", "fire-accept");
            acceptData.put("data", dataObj);

            sendMessage(acceptData, new SendResponseListener() {
                @Override
                public void onRequestCompleted() {
                    Log.d("sendAccept", "onRequestCompleted() 호출됨.");
                    //addToDB(); 요청 완료되면 DB에 추가
                }

                @Override
                public void onRequestStarted() {
                    //Log.d("sendSOS","onRequestStarted() 호출됨.");
                }

                @Override
                public void onRequestWithError(VolleyError error) {
                    Log.d("sendAccept", "onRequestWithError() 호출됨.");
                    // Toast.makeText(context,"요청 실패되었습니다",Toast.LENGTH_LONG);
                }
            }, context);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("accept", "accept 메시지 보내는 과정에서 문제 발생");
        }
    }

    public interface SendResponseListener {
        void onRequestStarted();

        void onRequestCompleted();

        void onRequestWithError(VolleyError error);
    }

    public static void sendMessage(JSONObject requestData, final SendResponseListener listener, Context context) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestWithError(error);
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization",
                        "key=AAAAJpBDuCc:APA91bGkulRX5N7mahz_o6E6PGKXPrSAsfpr6QmnMEmX8SbiVS8XX7ChCT1737SvKzuShTvirLtDoGAMlt3bNAKWJ8xW4m0ntdaCYzxJ7ohxl3AavuIP2t2HsmTaQRCCIVHJaouASrPB");

                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        request.setShouldCache(false);
        listener.onRequestStarted();
        if (queue == null) queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}

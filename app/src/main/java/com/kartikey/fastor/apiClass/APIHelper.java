package com.kartikey.fastor.apiClass;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIHelper {
    Context mContext;

    public APIHelper(Context mContext){
        this.mContext = mContext;
    }

    public void call(final String url , JSONObject body ,Boolean includeAuth, final onHelperResponse callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        final okhttp3.Request.Builder request = new okhttp3.Request.Builder().url(url);


        //TODO: Fetch Token
        //if(includeAuth)
        //request.addHeader("auth", getToken());

        if(body != null)
            request.post(RequestBody.create(MediaType.parse(body.toString()), body.toString()));


        client.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("debugg", "API Error : " + e.toString());
                callback.onError(e.toString(), 0);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code() == 200){
                    try{
                        JSONObject responseObject = new JSONObject(response.body().string());
                        callback.onResponse(responseObject);
                    }catch (Exception e){
                        Log.e("debugg", "error decoding response : " + e.toString());
                        callback.onError("error decoding response : " + e.toString(), 200);
                    }
                }else{
                    Log.e("debugg", "NON 200 RESPONSE (" + url + ") :" + response.code() + ", " + String.valueOf(response.body()));
                    callback.onError("NON 200 RESPONSE :" + response.code() + ", " + String.valueOf(response.body()), response.code());
                }

            }
        });

    }




    interface onHelperResponse{
        void onResponse(JSONObject object);
        void onError(String errorMessage, Integer errorCode);
    }


}

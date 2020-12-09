package com.kartikey.fastor.apiClass;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class API {
    static String sendOtpUrl = "https://staging.fastor.in:8090/v1/pwa/user/register";
    static String loginOtpUrl = "https://staging.fastor.in:8090/v1/pwa/user/login";
    static String fetchRestaurantUrl = "https://staging.fastor.in:8090/v1/m/restaurant?city_id=118&&";

    public static void userLogin(Context context,String phone,String otp , final APIResponse response){
        APIHelper helper = new APIHelper(context);
        JSONObject body =new JSONObject();
        try{
            body.put("phone",phone);
            body.put("otp",otp);
            body.put("dial_code",+91);
        }catch (JSONException e){
            Log.e("debugg", "APIs Error UserLogin : ",e);
        }

        helper.call(loginOtpUrl, body, false, new APIHelper.onHelperResponse() {
            @Override
            public void onResponse(JSONObject object) {
              response.onSuccess(object);
            }

            @Override
            public void onError(String errorMessage, Integer errorCode) {
                response.onError(errorMessage);
            }
        });
    }
    public static void registerUser(Context context,String phone,final APIResponse response){
        APIHelper helper = new APIHelper(context);
        JSONObject body = new JSONObject();
        try{
            body.put("phone",phone);
            body.put("dial_code",+91);
        }catch (JSONException e){
            Log.e("debugg","APIs Error Register :",e);
        }
        helper.call(sendOtpUrl, body, false, new APIHelper.onHelperResponse() {
            @Override
            public void onResponse(JSONObject object) {
                response.onSuccess(object);
            }

            @Override
            public void onError(String errorMessage, Integer errorCode) {
                response.onError(errorMessage);
            }
        });
    }

    public interface APIResponse{
        void onSuccess(JSONObject response);
        void onError(String error);
    }

}

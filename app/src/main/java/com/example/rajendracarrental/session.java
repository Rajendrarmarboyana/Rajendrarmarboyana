package com.example.rajendracarrental;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class session {
    private static final String DATA_LOGIN = "user_login";
    private static final String DATA_NAME = "name";
    private static final String DATA_USERNAME = "email";
    private static final String DATA_PHONE = "phone";
    private static final String DATA_ADDRESS = "fullname";
    private static final String DATA_USERID = "userid";

    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setDataName(Context context, String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_NAME,data);
        editor.apply();
    }

    public static String getDataName(Context context){
        return getSharedPreferences(context).getString(DATA_NAME,"");
    }

    public static String getDataUsername(Context context) {
        return getSharedPreferences(context).getString(DATA_USERNAME,"");
    }

    public static void setDataUsername(Context context, String email){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_USERNAME,email);
        editor.apply();
    }

    public static String getDataPhone(Context context) {
        return getSharedPreferences(context).getString(DATA_PHONE,"");
    }

    public static void setDataPhone(Context context, String phone){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_PHONE,phone);
        editor.apply();
    }

    public static String getDataAddress(Context context) {
        return getSharedPreferences(context).getString(DATA_ADDRESS,"");
    }

    public static void setDataAddress(Context context, String fullname){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_ADDRESS,fullname);
        editor.apply();
    }

    public static String getDataUserid(Context context) {
        return getSharedPreferences(context).getString(DATA_USERID,"");
    }

    public static void setDataUserid(Context context, String userid){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_USERID,userid);
        editor.apply();
    }


    public static void setDataLogin(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(DATA_LOGIN,status);
        editor.apply();
    }

    public static boolean getDataLogin(Context context){
        return getSharedPreferences(context).getBoolean(DATA_LOGIN,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(DATA_NAME);
        editor.remove(DATA_USERNAME);
        editor.remove(DATA_PHONE);
        editor.remove(DATA_ADDRESS);
        editor.remove(DATA_LOGIN);
        editor.apply();
    }
}

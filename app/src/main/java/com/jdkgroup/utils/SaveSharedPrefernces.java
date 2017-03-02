package com.jdkgroup.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveSharedPrefernces {

    SharedPreferences sharedPreferences;

    public static final String PREFS_NAME = "MyPref";
    public static final String KEY_PREFS = "key_pref";

    public static final String KEY_USERID = "key_userid";
    public static final String KEY_EMAILID = "key_emailid";
    public static final String KEY_USERNAME = "key_username";
    public static final String KEY_LOGIN = "key_login";

    public void set_Userid(Context context, String userid) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, userid);
        editor.commit();
    }

    public String get_Userid(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userid = sharedPreferences.getString(KEY_USERID, "");

        return userid;
    }

    public void set_Emailid(Context context, String emailid) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAILID, emailid);
        editor.commit();
    }

    public String get_Emailid(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String emailid = sharedPreferences.getString(KEY_EMAILID, "");

        return emailid;
    }

    public void set_Username(Context context, String username) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public String get_Username(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USERNAME, "");

        return username;
    }

    public void set_Login(Context context, String username) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LOGIN, username);
        editor.commit();
    }

    public String get_Login(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String login = sharedPreferences.getString(KEY_LOGIN, "");

        return login;
    }
}
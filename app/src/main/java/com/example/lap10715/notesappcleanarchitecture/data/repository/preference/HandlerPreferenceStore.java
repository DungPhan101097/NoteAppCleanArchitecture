package com.example.lap10715.notesappcleanarchitecture.data.repository.preference;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class HandlerPreferenceStore {

    public static String getStringPreference(Context context, int key, String defaultString){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(key), defaultString);
    }

   public static void saveStringPreference(Context context, int key, String newValue){
        String prefKey = context.getString(key);
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(prefKey, newValue);
        editor.apply();
   }
}

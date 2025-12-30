package com.watchwords.app;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    static Prefs inst;
    SharedPreferences prefs;

    private Prefs(Context c){
        prefs = c.getSharedPreferences("watchwords", MODE_PRIVATE);
    }

    public static Prefs getInstance(Context c){
        if(inst==null) inst = new Prefs(c);
        return inst;
    }

    public void saveWords(String w){
        prefs.edit().putString("words", w).apply();
    }

    public String getWords(){
        return prefs.getString("words", "");
    }

    public void saveAlertText(String t){
        prefs.edit().putString("alert", t).apply();
    }

    public String getAlertText(){
        return prefs.getString("alert", "");
    }
}

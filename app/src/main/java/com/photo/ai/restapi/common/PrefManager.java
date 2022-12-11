package com.photo.ai.restapi.common;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences mPref;
    SharedPreferences.Editor editor;

    public PrefManager(Context mContext) {
        mPref = mContext.getSharedPreferences("Preset", Context.MODE_PRIVATE);
        editor = mPref.edit();
    }

    public void setIsFirstTime(Boolean d1) {
        if (mPref != null) {
            editor = mPref.edit();
            if (!d1.equals(""))
                editor.putBoolean("isFirst", d1);
            editor.apply();
        }
    }

    public Boolean getIsFirstTime() {
        Boolean var = false;
        if (mPref != null) {
            var = mPref.getBoolean("isFirst", false);
        }
        return var;
    }

    public void setIsPremium(Boolean d1) {
        if (mPref != null) {
            editor = mPref.edit();
            if (!d1.equals(""))
                editor.putBoolean("isPremium", d1);
            editor.apply();
        }
    }

    public Boolean getIsPremium() {
        Boolean var = false;
        if (mPref != null) {
            var = mPref.getBoolean("isPremium", false);
        }
        return var;
    }

}

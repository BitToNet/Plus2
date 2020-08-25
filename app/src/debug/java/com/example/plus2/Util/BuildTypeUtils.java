package com.example.plus2.Util;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-07-14   10:46
 * desc   :
 */
public class BuildTypeUtils {

    public static void drawBadge(Activity activity) {
        ViewGroup decorView = (ViewGroup)activity.getWindow().getDecorView();
        View badge = new View(activity);
        badge.setBackgroundColor(Color.YELLOW);
        decorView.addView(badge,200,200);
    }
}

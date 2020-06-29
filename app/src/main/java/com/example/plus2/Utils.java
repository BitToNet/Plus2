package com.example.plus2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-17   17:48
 * desc   :
 */
public class Utils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());

    }

    public static Bitmap getAvatar(Resources resources, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //这个方法比较耗时，如果先设置上面的参数为true,就只会取到图片的宽高
        BitmapFactory.decodeResource(resources, R.drawable.avatar_dog, options);
        options.inJustDecodeBounds = false;
        //获取宽高比以后再重新取就可以提高性能
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_dog, options);
    }

    public static float getZFromCamera() {
        return -6 * Resources.getSystem().getDisplayMetrics().density;
    }
}

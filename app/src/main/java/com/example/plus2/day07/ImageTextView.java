package com.example.plus2.day07;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.R;
import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-18   16:42
 * desc   :
 */
public class ImageTextView extends View {
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");
    private static final int RING_WIDTH = (int) Utils.dp2px(20);
    private static final int RADIUS = (int) Utils.dp2px(150);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    TextPaint textPaint = new TextPaint();
    Bitmap bitmap;
    float[] cutWidth = new float[1];

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int) Utils.dp2px(100));
        paint.setTextSize(Utils.dp2px(12));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, getWidth() - Utils.dp2px(100), 100, paint);
        String text = "文字自动换行： 使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转";
        //第一行结束的位置
        int index = paint.breakText(text, true, getWidth(), cutWidth);
        canvas.drawText(text, 0, index, 0, 50, paint);
        int oldIndex = index;
        index = paint.breakText(text, index, text.length(), true, getWidth(), cutWidth);
        canvas.drawText(text, oldIndex, oldIndex + index, 0, 50 + paint.getFontSpacing(), paint);
        oldIndex = index;
        index = paint.breakText(text, index, text.length(), true, getWidth() - Utils.dp2px(100), cutWidth);
        canvas.drawText(text, oldIndex, oldIndex + index, 0, 50 + paint.getFontSpacing() * 2, paint);


        //spacingmult:空隙缩放，1不变，1.2扩大，0.8缩小
//        StaticLayout staticLayout = new StaticLayout(
//                "使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转使用Camera做三维旋转",
//                textPaint,getWidth(), Layout.Alignment.ALIGN_NORMAL,1,0,false);
//        staticLayout.draw(canvas);
    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //这个方法比较耗时，如果先设置上面的参数为true,就只会取到图片的宽高
        BitmapFactory.decodeResource(getResources(), R.drawable.avatar_dog, options);
        options.inJustDecodeBounds = false;
        //获取宽高比以后再重新取就可以提高性能
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avatar_dog, options);
    }
}

package com.example.plus2.day06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.R;
import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-18   14:20
 * desc   : 圆形头像
 */
public class AvatarView extends View {
    private static final int WIDTH = (int) Utils.dp2px(300);
    //黑边的宽度
    private static final int EDGE_WIDTH = (int) Utils.dp2px(10);
    private static final int PADDING = (int) Utils.dp2px(50);

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    RectF savedArea = new RectF();

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int) WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        savedArea.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //把这块区域扣下来，这样我拿到的离屏缓存尽量的小
        canvas.drawOval(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH, paint);
        int saved = canvas.saveLayer(savedArea, paint);
        canvas.drawOval(PADDING + EDGE_WIDTH, PADDING + EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        //恢复
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
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



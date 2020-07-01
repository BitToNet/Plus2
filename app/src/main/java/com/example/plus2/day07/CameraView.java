package com.example.plus2.day07;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-18   18:00
 * desc   : camera介绍
 */
public class CameraView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        //画布沿x轴旋转
        camera.rotateX(45);
        //调整camera位置，调整相机的高度
        //-6的单位是英寸，1英寸=72像素，所以手机像素不一样产生的效果不一样，所以要做兼容性配置 *getResources().getDisplayMetrics().density
//        camera.setLocation(0,0,-6*getResources().getDisplayMetrics().density);
        camera.setLocation(0, 0, Utils.getZFromCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //斜着绘制对折效果:多两步，1旋转，2扩大切割范围
        //绘制上半部分
        canvas.save();
        canvas.rotate(-20);
        canvas.clipRect(-200,0,getWidth(),100+500/2+200);
        canvas.rotate(20);
        canvas.drawBitmap(Utils.getAvatar(getResources(), 500), 100, 100, paint);
        canvas.restore();
        //绘制下半部分
        canvas.save();
        canvas.translate(100 + 500 / 2, 100 + 500 / 2);
        canvas.rotate(-20);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-500/2-200, 0, 500/2+200, 500/2+200);
        canvas.rotate(20);
        canvas.translate(-(100 + 500 / 2), -(100 + 500 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 500), 100, 100, paint);
        canvas.restore();

        //绘制对折效果，上面是自己写的，没有先移动到原点，所以要多做计算，所以最好还是统一移动到原点
        //绘制上半部分
        canvas.save();
        //裁剪
        canvas.translate(100 + 500 / 2, 700 + 500 / 2);
        canvas.rotate(-20);
        canvas.clipRect(-500/2-200, -500/2-200, 500/2+200, 0);
        canvas.rotate(20);
        canvas.translate(-(100 + 500 / 2), -(700 + 500 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 500), 100, 700, paint);
        canvas.restore();
        //绘制下半部分
        canvas.translate(100 + 500 / 2, 700 + 500 / 2);
        canvas.rotate(-20);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-500/2-200, 0, 500/2+200, 500/2+200);
        //旋转
        canvas.rotate(20);
        //平移
        canvas.translate(-(100 + 500 / 2), -(700 + 500 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 500), 100, 700, paint);

    }
}

package com.example.plus2.day08;

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

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 20;
    
    float top = Utils.dp2px(100);
    float left = Utils.dp2px(100);


    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {

        //调整camera位置，调整相机的高度
        //-6的单位是英寸，1英寸=72像素，所以手机像素不一样产生的效果不一样，所以要做兼容性配置 *getResources().getDisplayMetrics().density
//        camera.setLocation(0,0,-6*getResources().getDisplayMetrics().density);
        camera.setLocation(0, 0, Utils.getZFromCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制对折效果，上面是自己写的，没有先移动到原点，所以要多做计算，所以最好还是统一移动到原点
        //绘制上半部分
        canvas.save();
        //裁剪
        canvas.translate(left + 600 / 2, top + 600 / 2);
        canvas.rotate(-flipRotation);
        //画布沿x轴旋转
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-600/2-200, -600/2-200, 600/2+200, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(left + 600 / 2), -(top + 600 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 600), left, top, paint);
        canvas.restore();
        //绘制下半部分
        canvas.translate(left + 600 / 2, top + 600 / 2);
        canvas.rotate(-flipRotation);
        //画布沿x轴旋转
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-600/2-200, 0, 600/2+200, 600/2+200);
        //旋转
        canvas.rotate(flipRotation);
        //平移
        canvas.translate(-(left + 600 / 2), -(top + 600 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 600), left, top, paint);

    }
    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }
}

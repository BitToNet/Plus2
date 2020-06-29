package com.example.plus2.day09;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.plus2.R;
import com.example.plus2.TextUtils;
import com.example.plus2.Utils;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-28   16:26
 * desc   :
 */
//不直接继承EditText是为了更好的兼容，使用上没有什么区别
public class MaterialEditText extends AppCompatEditText {
    private static final float TEXT_SIZE = Utils.dp2px(12);
    private static final float TEXT_MARGIN = Utils.dp2px(8);
    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dp2px(22);
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dp2px(5);
    private static final int TEXT_ANIMATION_OFFSET = (int) Utils.dp2px(16);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    boolean floatingLableShown;

    float floatingLableFraction;
    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    boolean useFloatingLabel = true;
    Rect backgroundPadding = new Rect();

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs);

    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        //用完回收
        typedArray.recycle();

        paint.setTextSize(TEXT_SIZE);
        onUseFloatingLableChange();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(useFloatingLabel){
                    if (floatingLableShown && TextUtils.isEmpty(s.toString())) {
//                    getAniamtorReverse().start();
                        getAniamtor().reverse();
                        floatingLableShown = !floatingLableShown;
                    } else if (!floatingLableShown && !TextUtils.isEmpty(s.toString())) {
                        getAniamtor().start();
                        floatingLableShown = !floatingLableShown;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onUseFloatingLableChange() {
        getBackground().getPadding(backgroundPadding);
        if (useFloatingLabel) {
            setPadding(getPaddingLeft(), (int) (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());
            if(!TextUtils.isEmpty(getText().toString())){
                getAniamtor().start();
            }
        } else {
            if(!TextUtils.isEmpty(getText().toString())){
                getAniamtor().reverse();
            }
            setPadding(getPaddingLeft(), backgroundPadding.top, getPaddingRight(), getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAlpha((int) (0xff * floatingLableFraction));
        float extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLableFraction);
        canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET + extraOffset, paint);
    }

    //代码中设置属性
    public void setUseFloatingLabel(boolean useFloatingLabel) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel;
            //因为这条属性会影响布局高度，所以要请求重新绘制
//        requestLayout();
//            if(useFloatingLabel){
//                setPadding(getPaddingLeft(), (int) (getPaddingTop() + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());
//            }  else {
//                //因为之前设置过，所以padding已经变大了，所以要减回去
//                setPadding(getPaddingLeft(), (int) (getPaddingTop() - TEXT_SIZE - TEXT_MARGIN), getPaddingRight(), getPaddingBottom());
//            }
            //上面的计算方式会考虑到历史累加，代码越多计算会越复杂，所以换一种方式
            //padding是哪来的？其实是根据我们的背景来的
//            getBackground().getPadding(backgroundPadding);
//            if (useFloatingLabel) {
//                setPadding(getPaddingLeft(), (int) (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());
//            } else {
//                setPadding(getPaddingLeft(), backgroundPadding.top, getPaddingRight(), getPaddingBottom());
//            }
            onUseFloatingLableChange();
        }

    }

    public boolean getUseFloatingLabel(){
        return useFloatingLabel;
    }

    private ObjectAnimator getAniamtorReverse() {
        if (animator1 == null) {
            animator1 = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLableFraction", 0);
        }
        return animator1;

    }

    private ObjectAnimator getAniamtor() {
        if (animator2 == null) {
            animator2 = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLableFraction", 1);
        }
        return animator2;

    }

    public float getFloatingLableFraction() {
        return floatingLableFraction;
    }

    public void setFloatingLableFraction(float floatingLableFraction) {
        this.floatingLableFraction = floatingLableFraction;
        invalidate();
    }
}

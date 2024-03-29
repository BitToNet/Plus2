package com.example.plus2;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plus2.day08.CameraView;
import com.example.plus2.day08.CircleView;
import com.example.plus2.day08.CountryView;
import com.example.plus2.day08.PointView;
import com.example.plus2.day08.ProvinceUtils;
import com.example.plus2.day09.MaterialEditText;
import com.example.plus2.day11.TouchView;
import com.example.plus2.day12.ScalableImageViewActivity;
import com.example.plus2.day13.MultiTouchView1Activity;
import com.example.plus2.day13.MultiTouchView2Activity;
import com.example.plus2.day13.MultiTouchView3Activity;
import com.example.plus2.httpdemo.BaseBean;
import com.example.plus2.httpdemo.GitHubService;
import com.example.plus2.httpdemo.RetrofitManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.view10)
    TouchView view10;
    @BindView(R.id.view9)
    MaterialEditText view9;
    @BindView(R.id.view8)
    CountryView view8;
    @BindView(R.id.view7)
    PointView view7;
    @BindView(R.id.view6_2)
    ImageView view6_2;
    @BindView(R.id.view6)
    ImageView view6;
    @BindView(R.id.view5)
    ImageView view5;
    @BindView(R.id.view4)
    CameraView view4;
    @BindView(R.id.view3)
    CameraView view3;
    @BindView(R.id.view2)
    CircleView view2;
    @BindView(R.id.view1)
    ImageView view1;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        view9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //识别dubug和其他版本的技巧  第20课
//        drawBadge(this);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("","");
        map.putIfAbsent("","");

        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("","");
    }



    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8
            , R.id.bt9
            , R.id.view10
            , R.id.bt10
            , R.id.bt11
            , R.id.bt12
            , R.id.bt13
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                view1.setTranslationX(0);
                view1.setTranslationY(0);
                view1.setRotation(0);
                view1.setAlpha(1f);
                view1.animate()
                        .translationX(Utils.dp2px(200))
                        .translationY(100)
                        .rotation(180)
                        .alpha(0.5f)
                        .setStartDelay(1000)
                        .start();
                break;
            case R.id.bt2:
                //自定义一个圆心不断变大的属性动画
                //这个propertyName会调用反射去找自定义view里面的get、set方法
                //并且会逐渐把这个值改变为150,所以要在自定义view的set方法里面加invalidate();
                //自定义view里面的radiu名字可以改，但是get、set方法必须要与propertyName对应
                ValueAnimator animator = ObjectAnimator.ofFloat(view2, "radius", 0,Utils.dp2px(150));
                animator.setStartDelay(1000);
                animator.start();
                break;
            case R.id.bt3:
                //组合动画AnimatorSet，先后执行
                ObjectAnimator bottomFlip = ObjectAnimator.ofFloat(view3, "bottomFlip", 0,45);
                bottomFlip.setDuration(1500);
//                bottomFlip.start();
                ObjectAnimator flipRotation = ObjectAnimator.ofFloat(view3, "flipRotation", 0,360);
                flipRotation.setDuration(1500);
                ObjectAnimator topFlip = ObjectAnimator.ofFloat(view3, "topFlip", 0,-45);
                flipRotation.setDuration(1500);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(bottomFlip, flipRotation,topFlip);
                animatorSet.start();
                break;
            case R.id.bt4:
//             组合动画PropertyValuesHolder，同时执行
                PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 0,45);
                PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 0,360);
                PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", 0,-45);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view4, bottomFlipHolder, flipRotationHolder, topFlipHolder);
                objectAnimator.setDuration(1500);
                objectAnimator.start();
                break;
            case R.id.bt5:
                //用Keyframe做中间有阻力的平移
                float v = Utils.dp2px(200);
                Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
                Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 0.4f * v);
                Keyframe keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * v);
                Keyframe keyframe4 = Keyframe.ofFloat(1, 1 * v);
                PropertyValuesHolder translationXHolde = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4);
//                ObjectAnimator translationX = ObjectAnimator.ofFloat(view5, "translationX", 0,Utils.dp2px(200));
                ObjectAnimator translationX = ObjectAnimator.ofPropertyValuesHolder(view5, translationXHolde);
                translationX.setDuration(2000);
                translationX.start();
                break;
            case R.id.bt6:
                view6.setTranslationX(-Utils.dp2px(100));
                //用插值器做入场动画和加速动画
                view6.animate()
                        .translationX(Utils.dp2px(250))
                        .setDuration(2000)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
                view6_2.setTranslationX(0);
                view6_2.animate()
                        .translationX(Utils.dp2px(250))
                        .setDuration(2000)
                        .setInterpolator(new AccelerateInterpolator())
                        .start();
                break;
            case R.id.bt7:
                //用Evaluator对点对象做自定义轨迹的动画
                Point pointStart = new Point(0, 0);
                Point point = new Point((int)Utils.dp2px(300), (int)Utils.dp2px(400));
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofObject(view7, "point", new PointValueEvaluator(), pointStart,point);
                objectAnimator1.setDuration(1000);
                objectAnimator1.start();
                break;
            case R.id.bt8:
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofObject(view8, "city", new CityEvaluator(), "河北省", "青海省");
                objectAnimator2.setDuration(2000);
                objectAnimator2.start();
                break;
            case R.id.bt9:
                view9.setUseFloatingLabel(!view9.getUseFloatingLabel());
                break;
            case R.id.view10:
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_LONG).show();
                break;
            case R.id.bt10:
                Intent intent = new Intent(this, ScalableImageViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt11:
                intent = new Intent(this, MultiTouchView1Activity.class);
                startActivity(intent);
                break;
            case R.id.bt12:
                intent = new Intent(this, MultiTouchView2Activity.class);
                startActivity(intent);
                break;
            case R.id.bt13:
                intent = new Intent(this, MultiTouchView3Activity.class);
                startActivity(intent);
                break;
        }
    }

    class PointValueEvaluator implements TypeEvaluator<Point>{
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            float x = startValue.x+(endValue.x-startValue.x)*fraction;
//            float y = startValue.y+(endValue.y-startValue.y)*fraction;
            double sqrt =  Math.sqrt((endValue.y - startValue.y));
            double y = startValue.y+ Math.pow(sqrt*fraction,2);
//            float y = (20*fraction)*(20*fraction);
            return new Point((int)x,(int)y);
        }
    }

    class CityEvaluator implements TypeEvaluator<String>{
        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
            int start = ProvinceUtils.provinces.indexOf(startValue);
            int end = ProvinceUtils.provinces.indexOf(endValue);
            return ProvinceUtils.provinces.get((int) ((end-start)*fraction));
        }
    }


    public void okhttpDemo(){
//        String hostname = "publicobject.com";
//        CertificatePinner certificatePinner = new CertificatePinner.Builder()
//                .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
//                .build();
//        OkHttpClient client =new OkHttpClient.Builder()
//                .certificatePinner(certificatePinner)
//                .addInterceptor(new MyInterceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        //前置工作
//                        Response response = chain.proceed(chain.request());
//                        //后置工作
//                        return response;
//                    }
//                })
//                .build();
//        Request request = new Request.Builder()
//                .url("https://" + hostname)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });

        OkHttpClient client = new OkHttpClient();
        client.newCall(new Request.Builder().url("http://api.github.com").build())
                .enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                    }
                });

        try {
            Request request = new Request.Builder().url("http://api.github.com").build();
            client.newCall(request).execute();
            request.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void retrofitDemo(){
    //研究retrofit源码
        Resources.getSystem();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService api = retrofit.create(GitHubService.class);

//        api.getRepos().execute();//同步
        //异步
        api.getRepos().enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });

        OkHttpClient client = new OkHttpClient();
        client.newCall(
                new Request.Builder().url("").get().build())
                .enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                    }
                });

        RetrofitManager.getInstance()
                .getApiService()
                .getRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BaseBean bean) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        RetrofitManager.getInstance()
                .getApiService()
                .getDemo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(BaseBean value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}

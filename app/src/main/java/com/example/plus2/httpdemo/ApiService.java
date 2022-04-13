package com.example.plus2.httpdemo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2019/6/18  14:41
 * desc   : 基类
 */
public interface ApiService {

    /**
     * post请求
     */
    @FormUrlEncoded
    @POST(ApiConstant.EG)
    Observable<BaseBean> creatAddress(@FieldMap Map<String, Object> params);

    /**
     * get请求
     */
    @GET(ApiConstant.EG)
    Single<BaseBean> getAddressList(@Query("member_id") int member_id);

    /**
     * 动态拼接地址
     */
    @FormUrlEncoded
    @POST("v1/history/{category}")
    Observable<BaseBean> createHistory(@Path("category") String category,
                                       @FieldMap Map<String, Object> params);

    /**
     * 使用指定url
     */
    @FormUrlEncoded
    @POST()
    Observable<BaseBean> uploadFitSleepData(@Url String url, @FieldMap Map<String, Object> params);

    @GET()
    Observable<BaseBean> cleanFitSleepData(@Url String url, @Query("mac") String mac);


    /**
     * post上传文件
     */
    @Multipart
    @POST(ApiConstant.EG)
    Observable<BaseBean> updateMemberAvatar(@PartMap Map<String, RequestBody> params);

    /**
     * 上传文件时 RequestBody 示例
     */
    static Map<String, RequestBody> getRequestBodyDemo() {
        Map<String, RequestBody> params = new HashMap<>();
        File file = new File("path");
        params.put("images[]\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        params.put("title", RequestBody.create(MediaType.parse("text/plain"), "title"));
        return params;
    }

    @GET("user/{user}/repos")
    Observable<BaseBean> getRepos();

    @GET("user/{user}/repos")
    Single<BaseBean> getDemo();

    /**
     * 图片上传有三种,具体看后台怎么写的
     * 1.通过base64转成文字上传（传统纯文字表单，base64转化后数据量变大，效率低）
     * 2.分段传输，下面的@Multipart的updateAvatar()的方式，上传指定文件（传统含有文件的表单，需要拼接）
     * 3.直接上传，这种最高效，但是后台一般不会写（在Body中直接提交）
     */

    @FormUrlEncoded
    @POST(ApiConstant.EG)
    Observable<BaseBean> updateAvatar1(@Field("image") String image);

    @Multipart
    @POST(ApiConstant.EG)
    Observable<BaseBean> updateAvatar2(@PartMap Map<String, RequestBody> params);

    @POST(ApiConstant.EG)
    Observable<BaseBean> updateAvatar3(@Body RequestBody image);

}

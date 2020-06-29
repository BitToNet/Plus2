package com.example.plus2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-06-05   10:50
 * desc   :
 */
public interface GitHubService {
    @GET("user/{user}/repos")
    Call<ResponseBody> getRepos();
}

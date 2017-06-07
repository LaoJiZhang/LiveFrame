package com.laojizhang.liveframe.network;

import com.laojizhang.liveframe.movie.bean.MovieEntity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 文件名称： APIService
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-11:51
 * 文件描述：
 * <p>
 */
public interface APIService {

    @GET("movie/index")
    Call<LiveResponse<List<MovieResponse>>> loadingMovies(@QueryMap Map<String, String> map);

    @GET("http://api.douban.com/v2/movie/top250")
    Observable<DouBanResponse<List<MovieEntity>>> loadingDouBanTop250(@QueryMap Map<String, String> map);
}

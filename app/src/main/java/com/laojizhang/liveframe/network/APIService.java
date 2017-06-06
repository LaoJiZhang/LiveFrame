package com.laojizhang.liveframe.network;

import com.laojizhang.liveframe.movie.bean.MovieEntity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by guomaojian on 16/10/12.
 */

public interface APIService {

    @GET("movie/index")
    Call<LiveResponse<List<MovieResponse>>> loadingMovies(@QueryMap Map<String, String> map);

    @GET("http://api.douban.com/v2/movie/top250")
    Observable<DouBanResponse<List<MovieEntity>>> loadingDouBanTop250(@QueryMap Map<String, String> map);
}

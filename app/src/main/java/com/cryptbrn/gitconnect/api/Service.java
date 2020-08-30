package com.cryptbrn.gitconnect.api;

import java.util.List;
import com.cryptbrn.gitconnect.model.Detail;
import com.cryptbrn.gitconnect.model.Followers;
import com.cryptbrn.gitconnect.model.Following;
import com.cryptbrn.gitconnect.model.Responses;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("search/users")
    @Headers("Authorization: token c463a651043f3016aa7c6a52b9170db26dadd23a")
    Call<Responses> getSearchUser(
            @Query("q") String username
    );

    @GET("users/{username}")
    @Headers("Authorization: token c463a651043f3016aa7c6a52b9170db26dadd23a")
    Call<Detail> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token c463a651043f3016aa7c6a52b9170db26dadd23a")
        //<list> soalnya modelnya dibungkus array karena data ne banyak
    Call<List<Followers>> getFollowerUser(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token c463a651043f3016aa7c6a52b9170db26dadd23a")
    Call<List<Following>> getFollowingUser(
            @Path("username") String username
    );
}

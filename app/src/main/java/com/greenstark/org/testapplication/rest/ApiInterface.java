package com.greenstark.org.testapplication.rest;


import com.greenstark.org.testapplication.modal.GetallpopularalertsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    /*Customer API-------------------*/
    @Headers("Authorization: Bearer 044f910c-f202-482c-9119-72509e6f838a")
    @GET("getallpopularalerts")
    Call<GetallpopularalertsResponse> getalldetails(@Query("userId") int userId, @Query("latitude") double latitude, @Query("longitude") double longitude );




}

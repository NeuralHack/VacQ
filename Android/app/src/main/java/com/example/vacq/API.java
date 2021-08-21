package com.example.vacq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("predict")
    Call<List<Priority>> predict(@Query("Age") int Age, @Query("Gender") String Gender,@Query("Disease") String Disease,@Query("Zone") String Zone, @Query("Profession") String Profession);
}

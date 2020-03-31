package com.co.MiApp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("registro.php")
    Call<User> performRegistration(@Query("name") String Name,@Query("user_name") String UserName,@Query("password") String password);

    @GET("login.php")
    Call<User> performUserLogin(@Query("user_name") String UserName,@Query("password") String password);

}

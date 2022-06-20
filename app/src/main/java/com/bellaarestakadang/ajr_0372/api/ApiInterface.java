package com.bellaarestakadang.ajr_0372.api;

import com.bellaarestakadang.ajr_0372.models.CustomerResponse;
import com.bellaarestakadang.ajr_0372.models.PromoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({"Accept: application/json"})
    @GET("customer")
    Call<CustomerResponse> getAllCustomer();

    @Headers({"Accept: application/json"})
    @GET("customer/{id}")
    Call<CustomerResponse> getCustomerById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @GET("promo")
    Call<PromoResponse> getAllPromo();
}

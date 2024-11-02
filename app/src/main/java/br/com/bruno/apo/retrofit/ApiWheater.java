package br.com.bruno.apo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiWheater {
    //https://api.hgbrasil.com/weather?woeid=457197
    @GET("weather?woeid=457197")
    Call<ApiPojo> getInfTempo();
}

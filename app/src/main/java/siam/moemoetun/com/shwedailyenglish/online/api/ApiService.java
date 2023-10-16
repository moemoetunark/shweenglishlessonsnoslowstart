package siam.moemoetun.com.shwedailyenglish.online.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import siam.moemoetun.com.shwedailyenglish.BuildConfig;
import siam.moemoetun.com.shwedailyenglish.online.ItemModel;

public interface ApiService {
    @GET("f4664be1-4ce9-4bee-a1d3-79c1cc1ff0cc")
    @Headers({
            "X-SILO-KEY: " + BuildConfig.API_KEY, // Use BuildConfig.API_KEY to access the API key
            "Content-Type: application/json"
    })
    Call<List<ItemModel>> getData();
}

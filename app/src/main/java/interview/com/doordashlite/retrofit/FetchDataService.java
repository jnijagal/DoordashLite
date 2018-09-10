package interview.com.doordashlite.retrofit;

import java.util.List;
import java.util.Map;

import interview.com.doordashlite.models.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FetchDataService {
    @GET("/v2/restaurant")
    Call<List<Restaurant>> getRestaurantList(@QueryMap Map<String, String> params);
}

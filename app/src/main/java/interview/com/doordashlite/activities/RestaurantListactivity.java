package interview.com.doordashlite.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interview.com.doordashlite.DoorDashLiteConstants;
import interview.com.doordashlite.R;
import interview.com.doordashlite.RestaurantListAdapter;
import interview.com.doordashlite.RestaurantListItemDecoration;
import interview.com.doordashlite.fragments.SplashFragment;
import interview.com.doordashlite.models.Restaurant;
import interview.com.doordashlite.retrofit.FetchDataService;
import interview.com.doordashlite.retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListactivity extends Activity {
    private static final String TAG_SPLASH_SCREEN = "splashScreen";
    private RestaurantListAdapter adapter;
    private RecyclerView recyclerView;
    private SplashFragment mSplashScreenFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentManager fm = getFragmentManager();
        if (getActionBar() != null) {
            getActionBar().hide();
        }

        // Show loading fragment
        mSplashScreenFragment = (SplashFragment) fm.findFragmentByTag(TAG_SPLASH_SCREEN);
        if (mSplashScreenFragment == null) {
            mSplashScreenFragment = new SplashFragment();
            fm.beginTransaction().add(android.R.id.content, mSplashScreenFragment, TAG_SPLASH_SCREEN).commit();
        }

        loadRestaurantData();
    }

    private void loadRestaurantData() {
        final FragmentManager fm = getFragmentManager();

        /*
          Create handle for the RetrofitInstance interface
          */
        FetchDataService service = RetrofitClientInstance.getRetrofitInstance().create(FetchDataService.class);

        Map<String, String> params = new HashMap<>();
        params.put(DoorDashLiteConstants.DOORDASH_API_LAT_KEY, "37.422740");
        params.put(DoorDashLiteConstants.DOORDASH_API_LNG_KEY, "-122.139956");
        params.put(DoorDashLiteConstants.DOORDASH_API_LIMIT_KEY, "150");

        Call<List<Restaurant>> call = service.getRestaurantList(params);
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                mSplashScreenFragment = (SplashFragment) fm.findFragmentByTag(TAG_SPLASH_SCREEN);
                if (mSplashScreenFragment != null) {
                    fm.beginTransaction().remove(mSplashScreenFragment).commit();
                }
                if (getActionBar() != null) {
                    getActionBar().show();
                }
                setContentView(R.layout.restaurant_list_activity);
                setTitle(getString(R.string.title_discover));
                if (response.body() != null) {

                    Collections.sort(response.body(), new Comparator<Restaurant>() {
                        @Override
                        public int compare(Restaurant r1, Restaurant r2) {
                            if (r1.getStatus().contains(DoorDashLiteConstants.STATUS_MINS) && r2.getStatus().contains(DoorDashLiteConstants.STATUS_MINS)) {
                                int time1 = Integer.parseInt(r1.getStatus().split(" ")[0]);
                                int time2 = Integer.parseInt(r2.getStatus().split(" ")[0]);
                                return time1 < time2 ? -1 : time1 == time2 ? 0 : 1;
                            } else {
                                if (r2.getStatus().contains(DoorDashLiteConstants.STATUS_MINS)) {
                                    return 1;
                                }
                            }
                            return 0;
                        }
                    });
                }
                populateRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e("Test", "error " + t.getMessage());
                Toast.makeText(RestaurantListactivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Method to generate List of data using RecyclerView with custom adapter
     *
     * @param restaurantList
     */
    private void populateRecyclerView(List<Restaurant> restaurantList) {
        final CheckBox favCheckBox = findViewById(R.id.fav_checked);
        Log.d("Test", "init checkbox" + favCheckBox);
        recyclerView = findViewById(R.id.restaurant_list_recycler_view);
        recyclerView.addItemDecoration(new RestaurantListItemDecoration(this));
        adapter = new RestaurantListAdapter(this, restaurantList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestaurantListactivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

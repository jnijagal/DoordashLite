package interview.com.doordashlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import interview.com.doordashlite.models.Restaurant;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder> {

    private List<Restaurant> mRestaurantList;
    private Context mContext;
    private OkHttp3Downloader okHttp3Downloader;
    private Picasso mPicasso;
    private SharedPreferences mSharedPreferences;

    public RestaurantListAdapter(Context context, List<Restaurant> restaurantList) {
        this.mContext = context;
        this.mRestaurantList = restaurantList;
        mSharedPreferences = mContext.getSharedPreferences(
                DoorDashLiteConstants.DOORDASH_APP, Context.MODE_PRIVATE);

    }

    @Override
    public RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restaurant_list_item, parent, false);

        return new RestaurantListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RestaurantListViewHolder holder, int position) {
        SpannableString name = new SpannableString(mRestaurantList.get(position).getName());
        name.setSpan(new StyleSpan(Typeface.BOLD), 0, name.length(), 0);
        final CheckBox favStatus = holder.favStatus;
        if (favStatus != null) {
            favStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setFavStatus(favStatus.isChecked(), DoorDashLiteConstants.FAV_STATUS_KEY + holder.getAdapterPosition());
                }
            });
            favStatus.setChecked(getFavStatus(DoorDashLiteConstants.FAV_STATUS_KEY + holder.getAdapterPosition()));
        }
        holder.restaurantName.setText(name);
        holder.restaurantDescription.setText(mRestaurantList.get(position).getDescription());
        String status = mRestaurantList.get(position).getStatus();
        holder.restaurantStatus.setText(status.contains(DoorDashLiteConstants.STATUS_MINS) ? status : mContext.getString(R.string.status_pre_order));

        if (okHttp3Downloader == null) {
            okHttp3Downloader = new OkHttp3Downloader(mContext);
        }

        if (mPicasso == null) {
            Picasso.Builder picassoBuilder = new Picasso.Builder(mContext);
            picassoBuilder.downloader(okHttp3Downloader);
            mPicasso = picassoBuilder.build();
        }

        mPicasso.load(mRestaurantList.get(position).getCoverImageUrl())
                .placeholder((R.drawable.cover_img_loading))
                .error(R.drawable.cover_img_error)
                .into(holder.coverImage);

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (okHttp3Downloader != null) {
            okHttp3Downloader.shutdown();
            okHttp3Downloader = null;
        }

        if (mPicasso != null) {
            mPicasso = null;
        }
    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }

    class RestaurantListViewHolder extends RecyclerView.ViewHolder {

        final View mView;

        private ImageView coverImage;
        private TextView restaurantName;
        private CheckBox favStatus;
        private TextView restaurantDescription;
        private TextView restaurantStatus;

        RestaurantListViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            coverImage = mView.findViewById(R.id.restaurant_cover_image);
            restaurantName = mView.findViewById(R.id.restaurant_name);
            favStatus = mView.findViewById(R.id.fav_checked);
            restaurantDescription = mView.findViewById(R.id.restaurant_description);
            restaurantStatus = mView.findViewById(R.id.restaurant_status);
        }
    }

    private void setFavStatus(final boolean isFav, final String key) {
        if (mSharedPreferences != null) {
            mSharedPreferences.edit().putBoolean(key, isFav).apply();
        }
    }

    private boolean getFavStatus(final String key) {
        return mSharedPreferences != null && mSharedPreferences.getBoolean(key, false);
    }
}

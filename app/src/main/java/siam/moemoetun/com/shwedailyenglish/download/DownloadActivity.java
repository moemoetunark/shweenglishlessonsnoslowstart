package siam.moemoetun.com.shwedailyenglish.download;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.download.adapter.ItemAdapter;
import siam.moemoetun.com.shwedailyenglish.download.adapter.SecondAdapter;
import siam.moemoetun.com.shwedailyenglish.download.api.ApiService;
import siam.moemoetun.com.shwedailyenglish.download.api.RetrofitClient;
import siam.moemoetun.com.shwedailyenglish.utility.Slide;

public class DownloadActivity extends AppCompatActivity implements SecondAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerView secondRecyclerView;
    private ItemAdapter adapter;
    private SecondAdapter secondAdapter;
    private List<ItemModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        secondRecyclerView = findViewById(R.id.secondRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        secondRecyclerView.setLayoutManager(layoutManager);


        // Set up RecyclerViews with LinearLayoutManager and auto-scroll
        setupRecyclerView(recyclerView);


        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ItemModel>> call = apiService.getData();

        call.enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                if (response.isSuccessful()) {
                    List<ItemModel> allItems = response.body();

                    // Filter items with the desired category "A"
                    itemList = filterItemsByCategory(allItems, "A");
                    adapter = new ItemAdapter(itemList);
                    recyclerView.setAdapter(adapter);

                    // Filter items with the desired category "B" for the second RecyclerView
                    List<ItemModel> secondItemList = filterItemsByCategory(allItems, "A");
                    secondAdapter = new SecondAdapter(secondItemList, DownloadActivity.this);
                    secondRecyclerView.setAdapter(secondAdapter);

                } else {
                    // Handle API error
                }
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                // Handle network or API request failure
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Slide.startAutoScroll(recyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private List<ItemModel> filterItemsByCategory(List<ItemModel> items, String desiredCategory) {
        List<ItemModel> filteredItems = new ArrayList<>();

        for (ItemModel item : items) {
            if (item.getCategory().equals(desiredCategory)) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    @Override
    public void onItemClick(String url) {
        CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
        intent.launchUrl(this, Uri.parse(url));
    }
}

package siam.moemoetun.com.shwedailyenglish.online.tabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.online.ItemModel;
import siam.moemoetun.com.shwedailyenglish.online.adapter.ItemAdapter;
import siam.moemoetun.com.shwedailyenglish.online.adapter.SecondAdapter;
import siam.moemoetun.com.shwedailyenglish.online.api.ApiService;
import siam.moemoetun.com.shwedailyenglish.online.api.RetrofitClient;
import siam.moemoetun.com.shwedailyenglish.online.network.NetworkChangeReceiver;
import siam.moemoetun.com.shwedailyenglish.utility.Slide;

public class Tab1Fragment extends Fragment implements SecondAdapter.OnItemClickListener {
    private RecyclerView secondRecyclerView;
    private SecondAdapter secondAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    private List<ItemModel> itemList;
    private NetworkChangeReceiver networkChangeReceiver;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        secondRecyclerView = view.findViewById(R.id.secondRecyclerView);
        progressBar = view.findViewById(R.id.progressBar); // Initialize the progress bar

        // Register the network change receiver
        networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireContext().registerReceiver(networkChangeReceiver, filter);

        if (isNetworkAvailable()) {
            GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
            secondRecyclerView.setLayoutManager(layoutManager);
            // Set up RecyclerView with LinearLayoutManager and auto-scroll

            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<List<ItemModel>> call = apiService.getData();
            progressBar.setVisibility(View.VISIBLE);

            call.enqueue(new Callback<List<ItemModel>>() {
                @Override
                public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                    if (response.isSuccessful()) {
                        List<ItemModel> allItems = response.body();
                        // Filter items with the desired category "A"
                        itemList = filterItemsSlide(allItems);
                        adapter = new ItemAdapter(itemList);
                        recyclerView.setAdapter(adapter);
                        // Filter items with the desired category "B" for the second RecyclerView
                        List<ItemModel> secondItemList = filterItemsByCategory(allItems);
                        secondAdapter = new SecondAdapter(secondItemList, Tab1Fragment.this);
                        secondRecyclerView.setAdapter(secondAdapter);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        // Handle API error
                        Toast.makeText(requireContext(), "Failed to load data.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                    // Handle network or API request failure
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Network request failed.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            showNoInternetDialog();
        }
        setupRecyclerView(recyclerView);


    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Slide.startAutoScroll(recyclerView);
    }

    private List<ItemModel> filterItemsByCategory(List<ItemModel> items) {
        List<ItemModel> filteredItems = new ArrayList<>();
        for (ItemModel item : items) {
            if ("Noun".equals(item.getCategory())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }


    private List<ItemModel> filterItemsSlide(List<ItemModel> items) {
        List<ItemModel> filteredItems = new ArrayList<>();
        for (ItemModel item : items) {
            if ("Slide".equals(item.getCategory())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        }
        return false;
    }

    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please enable your internet connection to load data.");
        builder.setPositiveButton("Enable Internet", (dialog, which) -> {
            // Open internet settings
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Handle the user's choice (e.g., close the app)
        });
        builder.create().show();
    }

    @Override
    public void onItemClick(String url) {
        // Handle item click
        CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
        intent.launchUrl(requireContext(), Uri.parse(url));
    }
    @Override
    public void onResume() {
        super.onResume();
        networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireContext().registerReceiver(networkChangeReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (networkChangeReceiver != null) {
            requireContext().unregisterReceiver(networkChangeReceiver);
            networkChangeReceiver = null; // Set it to null to prevent double unregister
        }
    }

}

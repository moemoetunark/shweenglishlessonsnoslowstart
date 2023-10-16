package siam.moemoetun.com.shwedailyenglish.online.tabs;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import siam.moemoetun.com.shwedailyenglish.MainActivity;
import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.online.ItemModel;
import siam.moemoetun.com.shwedailyenglish.online.adapter.ItemAdapter;
import siam.moemoetun.com.shwedailyenglish.online.adapter.SecondAdapter;
import siam.moemoetun.com.shwedailyenglish.online.api.ApiService;
import siam.moemoetun.com.shwedailyenglish.online.api.RetrofitClient;
import siam.moemoetun.com.shwedailyenglish.utility.Slide;

public class Tab1Fragment extends Fragment implements SecondAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerView secondRecyclerView;
    private ItemAdapter adapter;
    private SecondAdapter secondAdapter;
    private List<ItemModel> itemList;
    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
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
                    itemList = filterItemsByCategory(allItems);
                    adapter = new ItemAdapter(itemList);
                    recyclerView.setAdapter(adapter);

                    // Filter items with the desired category "B" for the second RecyclerView
                    List<ItemModel> secondItemList = filterItemsByCategory(allItems);
                    secondAdapter = new SecondAdapter(secondItemList, Tab1Fragment.this);
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Slide.startAutoScroll(recyclerView);
    }

    private List<ItemModel> filterItemsByCategory(List<ItemModel> items) {
        List<ItemModel> filteredItems = new ArrayList<>();

        for (ItemModel item : items) {
            if (item.getCategory().equals("Noun")) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    @Override
    public void onItemClick(String url) {
        // Handle item click
        CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
        intent.launchUrl(requireContext(), Uri.parse(url));
    }
}

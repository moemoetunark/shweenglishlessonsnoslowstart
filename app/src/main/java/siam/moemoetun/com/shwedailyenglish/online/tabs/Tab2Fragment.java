package siam.moemoetun.com.shwedailyenglish.online.tabs;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.online.ItemModel;
import siam.moemoetun.com.shwedailyenglish.online.adapter.SecondAdapter;
import siam.moemoetun.com.shwedailyenglish.online.api.ApiService;
import siam.moemoetun.com.shwedailyenglish.online.api.RetrofitClient;
public class Tab2Fragment extends Fragment implements SecondAdapter.OnItemClickListener {
    private RecyclerView secondRecyclerView;
    private SecondAdapter secondAdapter;
    private ProgressBar progressBar; // Add this line
    private List<ItemModel> itemList;
    public Tab2Fragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        secondRecyclerView = view.findViewById(R.id.secondRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        secondRecyclerView.setLayoutManager(layoutManager);
        progressBar = view.findViewById(R.id.progressBar); // Initialize the progress bar
        // Set up RecyclerViews with LinearLayoutManager and auto-scroll
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ItemModel>> call = apiService.getData();
        progressBar.setVisibility(View.VISIBLE);


        call.enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                if (response.isSuccessful()) {
                    List<ItemModel> allItems = response.body();
                    // Filter items with the desired category "A"
                    itemList = filterItemsByCategory(allItems);
                    // Filter items with the desired category "B" for the second RecyclerView
                    List<ItemModel> secondItemList = filterItemsByCategory(allItems);
                    secondAdapter = new SecondAdapter(secondItemList, Tab2Fragment.this);
                    secondRecyclerView.setAdapter(secondAdapter);
                    progressBar.setVisibility(View.GONE);

                } else {
                    // Handle API error
                    Toast.makeText(requireContext(), "No Internet!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                // Handle network or API request failure
            }
        });
    }

    private List<ItemModel> filterItemsByCategory(List<ItemModel> items) {
        List<ItemModel> filteredItems = new ArrayList<>();

        for (ItemModel item : items) {
            if (item.getCategory().equals("tech_books")) {
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

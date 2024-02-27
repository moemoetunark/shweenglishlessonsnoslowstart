package siam.moemoetun.com.shwedailyenglish.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.constant.AppConstant;
import siam.moemoetun.com.shwedailyenglish.content.WebViewActivity;
import siam.moemoetun.com.shwedailyenglish.premium.PremiumAdapter;

public class FragmentPremium extends Fragment implements PremiumAdapter.OnItemClickListener, AppConstant.CoinBalanceObserver {

    private PremiumAdapter adapter;
    private List<String> fileNames;
    private int selectedPosition = -1;

    public FragmentPremium() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_premium, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        try {
            String[] fileNamesArray = requireContext().getAssets().list("categories/");
            fileNames = new ArrayList<>(Arrays.asList(fileNamesArray));

            adapter = new PremiumAdapter(fileNames, requireContext());
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        // Unregister the PremiumAdapter as an observer when the fragment is no longer active
        AppConstant.unregisterCoinBalanceObserver(adapter);
        super.onDestroyView();
    }

    @Override
    public void onItemClick(String fileName) {
        // Enable the selected position by updating the adapter's data
        int selectedPosition = fileNames.indexOf(fileName);
        if (selectedPosition >= 0 && selectedPosition < fileNames.size()) {
            // Save the selected position
            this.selectedPosition = selectedPosition;
            adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
        }

        Intent intent = new Intent(requireContext(), WebViewActivity.class);
        intent.putExtra("file_name", fileName);
        intent.putExtra("sourceActivity", "premium");
        startActivity(intent);
    }


    // CoinBalanceObserver method
    @Override
    public void onCoinBalanceChanged(int newBalance) {
        // Update the UI or data in the adapter based on the new coin balance
        // For example, you might refresh the adapter to reflect the changes
        adapter.notifyDataSetChanged();
    }
}

package siam.moemoetun.com.shwedailyenglish.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.adapter.MyRecyclerViewAdapter;
import siam.moemoetun.com.shwedailyenglish.webview.DetailsWebView;
import siam.moemoetun.com.shwedailyenglish.webview.VocaWebView;


public class Fragment4 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    private InterstitialAd mInterstitialAd;
    ArrayList<String> animalNames = new ArrayList<>();
    MyRecyclerViewAdapter adapter;
    //TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animalNames.add("1.Make and Do");
        animalNames.add("2.Give, keep and Break");
        animalNames.add("3.Go and Get");
        animalNames.add("4.Family Vocabulary");
        animalNames.add("5.Weather");
        animalNames.add("6.Health");
        animalNames.add("7.Animals");
        animalNames.add("8.Body's Vocabulary");
        animalNames.add("9.People's Characters");
        animalNames.add("10.Feelings and Actions");
        animalNames.add("11.Daily Routine");
        animalNames.add("12.Money");
        animalNames.add("13.Food");
        animalNames.add("14.Transportation");
        animalNames.add("15.Job အလုပ်");
        animalNames.add("16.Sports - အားကစား");
        animalNames.add("17.Movies");
        animalNames.add("18.Music");
        animalNames.add("19.Politics");
        animalNames.add("20.Crimes");
        animalNames.add("21.Pollutions and Environment");
        animalNames.add("22.Ministry in Myanmar");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tools, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //textView = view.findViewById(R.id.textView_item);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(getContext(), getString(R.string.interstial_2021),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });





    }

    @Override
    public void onItemClick(View view, int position) {
        if (mInterstitialAd !=null) {
            mInterstitialAd.show((Activity) getContext());
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                   showItemClickToast(position);
                    mInterstitialAd = null;
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                }
            });
        }else {
           showItemClickToast(position);
        }
    }

    private void showItemClickToast(int position) {
        String itemClicked = adapter.getItem(position); // Get the clicked item text
        Toast.makeText(getContext(), "Clicked: " + itemClicked, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), VocaWebView.class);
        intent.putExtra("key", position);
        intent.putExtra("clickedItemName", itemClicked);
        startActivity(intent);
    }
}

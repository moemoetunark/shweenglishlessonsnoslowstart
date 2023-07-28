package siam.moemoetun.com.shwedailyenglish.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.adapter.MyRecyclerViewAdapter;
import siam.moemoetun.com.shwedailyenglish.webview.SongLyrics;


public  class Fragment10 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
   MyRecyclerViewAdapter adapter;
   private InterstitialAd mInterstitialAd;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_song, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // data to populate the RecyclerView with
        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Cry on my shoulder");
        animalNames.add("Flashlight by Jessie J");
        animalNames.add("Forgiveness");
        animalNames.add("I Will Be Right Here Waiting For You Lyrics");
        animalNames.add("Imagine me without you");
        animalNames.add("Just Give Me a Reason Lyrics");
        animalNames.add("Locked Away");
        animalNames.add("More Than Words");
        animalNames.add("My pride lyrics");
        animalNames.add("Need you now Lyrcis");
        animalNames.add("nothing gonna change");
        animalNames.add("Passenger let her go");
        animalNames.add("See you again");
        animalNames.add("Somebody's me lyrics");
        animalNames.add("Stand by me");
        animalNames.add("Taylor Swift blank space");
        animalNames.add("Thinking out loud by EdSheeran");
        animalNames.add("Until You");
        animalNames.add("We Don't talk anymore lyrics");
        animalNames.add("Westlife if i let you go");
        animalNames.add("Why not me");
        animalNames.add("You are my sunshine");

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

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        if (mInterstitialAd !=null) {
            mInterstitialAd.show((Activity) getContext());
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    Intent intent = new Intent(getContext(), SongLyrics.class);
                    intent.putExtra("key",position);
                    startActivity(intent);
                    mInterstitialAd = null;
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                }
            });
        }else {
            Intent intent = new Intent(getContext(), SongLyrics.class);
            intent.putExtra("key",position);
            startActivity(intent);
        }
    }
}

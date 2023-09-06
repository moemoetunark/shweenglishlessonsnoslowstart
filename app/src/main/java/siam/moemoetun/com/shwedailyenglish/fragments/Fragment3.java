package siam.moemoetun.com.shwedailyenglish.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class Fragment3 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener{

   private InterstitialAd mInterstitialAd;
    MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
    TextView textView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animalNames.add("လူကလေးနဲ့ခြေအိတ်တစ်စုံ");
        animalNames.add("ခွေးနဲ့ကြောင်");
        animalNames.add("ပန်းသီးဖျော်ရည်");
        animalNames.add("ငှက်နဲ့လူကလေး");
        animalNames.add("မွေးနေ့ကိတ်မုန့်");
        animalNames.add("ယောက်ျားလေးဟာယောကျ်ားလေးပဲ");
        animalNames.add("ပန်းခြံထဲမှာ");
        animalNames.add("အလုပ်မရှိ အစားမရှိ");
        animalNames.add("ငါ့အတွက် သူငယ်ချင်းမရှိ");
        animalNames.add("အမှန်ပြောပါ");
        animalNames.add("ဝက်ဝံကလေး");
        animalNames.add("အိုဘားမား");
        animalNames.add("အီဗရာဟင် လင်ကွန်း");
        animalNames.add("မာတင်လေသာကင်း");
        animalNames.add("ဂျော့ဝါချင်တာန်");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_slideshow, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                    Intent intent = new Intent(getContext(), DetailsWebView.class);
                    String itemClicked = adapter.getItem(position);
                    Toast.makeText(getContext(), "Clicked: " + itemClicked, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key",position);
                    intent.putExtra("clickedItemName", itemClicked);
                    intent.putExtra("FRAGMENT_ID","Fragment3");
                    startActivity(intent);
                    mInterstitialAd = null;
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                }
            });
        }else {
            Intent intent = new Intent(getContext(), DetailsWebView.class);
            String itemClicked = adapter.getItem(position);
            Toast.makeText(getContext(), "Clicked: " + itemClicked, Toast.LENGTH_SHORT).show();
            intent.putExtra("key",position);
            intent.putExtra("clickedItemName", itemClicked);
            intent.putExtra("FRAGMENT_ID","Fragment3");
            startActivity(intent);
        }
    }
}

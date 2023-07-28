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
import siam.moemoetun.com.shwedailyenglish.webview.PatternWebView;

public class Fragment5 extends Fragment implements  MyRecyclerViewAdapter.ItemClickListener {

private InterstitialAd mInterstitialAd;
 MyRecyclerViewAdapter adapter;
 //TextView textView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("I have something + V1...ငါ့မွာ...တစ္ခုရွိတယ္");
        animalNames.add("I promise not to + v1 ငါ ...မဖို႕  ကတိေပးတယ္");
        animalNames.add("I should have + V3...လုပ္သင့္ခဲ့တယ္");
        animalNames.add("it's too bad that + s + o + v. ..ဒါအရမ္းဆိုးတယ္");
        animalNames.add("I don't have time to + v1...အခ်ိန္ မရွိဘူး");
        animalNames.add("I'm calling to + V1.... ဖို႕ ငါဖုန္းဆက္ေနတာ");
        animalNames.add("I'm dying to + V1. ... ငါအရမ္းဆႏၵျပင္းျပေနတယ္");
        animalNames.add("I'm having hard time + v-ing. ..ငါ့အတြက္ ခက္ခဲေနတယ္");
        animalNames.add("I'm sorry to + v1. ... ငါစိတ္မေကါင္းပါဘူး");
        animalNames.add("I'm thinking of + V ing.  စဥ္းစားေနတယ္");
        animalNames.add("I'm working on + N. ..ကၽြန္ေနာ္ ၾကိဴးစားလုပ္ေနတယ္");
        animalNames.add("I'll help you + v1. ဖို႕မင္းကိုငါ ကူညီမယ္");
        animalNames.add("Be + adjective. v... ပါေစ");
        animalNames.add("To do, to go -  အမိန္႕ေပး...ေစခိုင္း");
        animalNames.add("Gonna, gotta, wanna");
        animalNames.add("I'm here to + V1. ..ဖို႕ငါဒီမွာ ရွိေနတာ");
        animalNames.add("I'm sure to + v1. ...ဖို႕ငါေသခ်ာတယ္");
        animalNames.add("if I were you.....ငါသာမင္းေနရာမွာဆို..");
        animalNames.add("If - လွ်င္");
        animalNames.add("It's time to + V1. ...အခ်ိန္တန္ျပီ");
        animalNames.add("let O + V1. ...ပါေစ");
        animalNames.add("May I V1.  ပါရေစ");
        animalNames.add("No need to + V1.....ဖို႕မလိုအပ္ပါဘူး");
        animalNames.add("Not because, but because ");
        animalNames.add("Not only ...but also");
        animalNames.add("Nothing to + V1.  ....ဖို႕ ဘာမွ မရွိဘူး ");
        animalNames.add("Please v1. ေက်းဇူးျပဳလို႕");
        animalNames.add("Please don't + v1. ေက်ဇူး ျပဳလို႕..ငါ့ကို....မပါနဲ႕");
        animalNames.add("S + can + v1. ႏိုင္သည္");
        animalNames.add("S + can't help + V ing...မခံရပ္ႏိုင္");
        animalNames.add("S + dare to + v1. ...ဝံ့တယ္..");
        animalNames.add("S + feel/smell/sound, etc + adj");
        animalNames.add("S+ have to/has to + v1. ရမယ္ ");
        animalNames.add("S + linking verb + v1");
        animalNames.add("S + make + o + adj.  ေအာင္လုပ္သည္ ");
        animalNames.add("S + need to + v1. လိုအပ္သည္");

        animalNames.add("s + seem + adj.  ျဖစ္ဟန္ရွိသည္");
        animalNames.add("s + seem + v1. ...ဟန္ရွိသည္");
        animalNames.add("S + would like + obj + to V1. ေစခ်င္သည္");
        animalNames.add("S + be able to + v1.  ႏိုင္သည္");
        animalNames.add("S + be + going to + v1.  ေတာ့မယ္ (နီးေသာအနာဂတ္)");
        animalNames.add("S + should/had better/ought to + v1. သင့္သည္");
        animalNames.add("S + used to + v1.  ေလ့ရွိခဲ့သည္");
        animalNames.add("S + be + used to + v ing.  က်င့္သားရေနျပီ");
        animalNames.add("S + want + o + to V1. ေအာင္လုပ္သည္ (သူမ်ားကို)");
        animalNames.add("S + must + V1. ..ရမယ္ ");
        animalNames.add("S + would like to + v1. ခ်င္တယ္ ");
        animalNames.add("That's why. ဒါေၾကာင့္ပဲ");
        animalNames.add("There is something wrong with + n. တစ္ခုခုမွားေနတယ္");
        animalNames.add("S + want to + v1. ခ်င္သည္");
        animalNames.add("What to/who to/where to");
        animalNames.add("Whether or not");
        animalNames.add("Would you like + n?  မင္း ...မလား");
        animalNames.add("Would you like to + v1. ခ်င္သလား");

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
                    Intent intent = new Intent(getContext(), PatternWebView.class);
                    intent.putExtra("key",position);
                    startActivity(intent);
                    mInterstitialAd = null;
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                }
            });
        }else {
            Intent intent = new Intent(getContext(), PatternWebView.class);
            intent.putExtra("key",position);
            startActivity(intent);
        }
    }
}

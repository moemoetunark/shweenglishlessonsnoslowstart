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

public class Fragment5 extends Fragment implements  MyRecyclerViewAdapter.ItemClickListener {
private InterstitialAd mInterstitialAd;
 MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
 //TextView textView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animalNames.add("I have something + V1...ငါ့မှာ...တစ်ခုရှိတယ်");
        animalNames.add("I promise not to + v1 ငါ ...မဖို့  ကတိပေးတယ်");
        animalNames.add("I should have + V3...လုပ်သင့်ခဲ့တယ်");
        animalNames.add("it's too bad that + s + o + v. ..ဒါအရမ်းဆိုးတယ်");
        animalNames.add("I don't have time to + v1...အချိန် မရှိဘူး");
        animalNames.add("I'm calling to + V1.... ဖို့ ငါဖုန်းဆက်နေတာ");
        animalNames.add("I'm dying to + V1. ... ငါအရမ်းဆန္ဒပြင်းပြနေတယ်");
        animalNames.add("I'm having hard time + v-ing. ..ငါ့အတွက် ခက်ခဲနေတယ်");
        animalNames.add("I'm sorry to + v1. ... ငါစိတ်မကေါင်းပါဘူး");
        animalNames.add("I'm thinking of + V ing.  စဉ်းစားနေတယ်");
        animalNames.add("I'm working on + N. ..ကျွန်နော် ကြိူးစားလုပ်နေတယ်");
        animalNames.add("I'll help you + v1. ဖို့မင်းကိုငါ ကူညီမယ်");
        animalNames.add("Be + adjective. v... ပါစေ");
        animalNames.add("To do, to go -  အမိန့်ပေး...စေခိုင်း");
        animalNames.add("Gonna, gotta, wanna");
        animalNames.add("I'm here to + V1. ..ဖို့ငါဒီမှာ ရှိနေတာ");
        animalNames.add("I'm sure to + v1. ...ဖို့ငါသေချာတယ်");
        animalNames.add("if I were you.....ငါသာမင်းနေရာမှာဆို..");
        animalNames.add("If - လျှင်");
        animalNames.add("It's time to + V1. ...အချိန်တန်ပြီ");
        animalNames.add("let O + V1. ...ပါစေ");
        animalNames.add("May I V1.  ပါရစေ");
        animalNames.add("No need to + V1.....ဖို့မလိုအပ်ပါဘူး");
        animalNames.add("Not because, but because ");
        animalNames.add("Not only ...but also");
        animalNames.add("Nothing to + V1.  ....ဖို့ ဘာမှ မရှိဘူး ");
        animalNames.add("Please v1. ကျေးဇူးပြုလို့");
        animalNames.add("Please don't + v1. ကျေဇူး ပြုလို့..ငါ့ကို....မပါနဲ့");
        animalNames.add("S + can + v1. နိုင်သည်");
        animalNames.add("S + can't help + V ing...မခံရပ်နိုင်");
        animalNames.add("S + dare to + v1. ...ဝံ့တယ်..");
        animalNames.add("S + feel/smell/sound, etc + adj");
        animalNames.add("S+ have to/has to + v1. ရမယ် ");
        animalNames.add("S + linking verb + v1");
        animalNames.add("S + make + o + adj.  အောင်လုပ်သည် ");
        animalNames.add("S + need to + v1. လိုအပ်သည်");

        animalNames.add("s + seem + adj.  ဖြစ်ဟန်ရှိသည်");
        animalNames.add("s + seem + v1. ...ဟန်ရှိသည်");
        animalNames.add("S + would like + obj + to V1. စေချင်သည်");
        animalNames.add("S + be able to + v1.  နိုင်သည်");
        animalNames.add("S + be + going to + v1.  တော့မယ် (နီးသောအနာဂတ်)");
        animalNames.add("S + should/had better/ought to + v1. သင့်သည်");
        animalNames.add("S + used to + v1.  လေ့ရှိခဲ့သည်");
        animalNames.add("S + be + used to + v ing.  ကျင့်သားရနေပြီ");
        animalNames.add("S + want + o + to V1. အောင်လုပ်သည် (သူများကို)");
        animalNames.add("S + must + V1. ..ရမယ် ");
        animalNames.add("S + would like to + v1. ချင်တယ် ");
        animalNames.add("That's why. ဒါကြောင့်ပဲ");
        animalNames.add("There is something wrong with + n. တစ်ခုခုမှားနေတယ်");
        animalNames.add("S + want to + v1. ချင်သည်");
        animalNames.add("What to/who to/where to");
        animalNames.add("Whether or not");
        animalNames.add("Would you like + n?  မင်း ...မလား");
        animalNames.add("Would you like to + v1. ချင်သလား");
}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(getContext(), getString(R.string.shwe_lessons_preload),
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
                    Intent intent = new Intent(getContext(), DetailsWebView.class);
                    String itemClicked = adapter.getItem(position);
                    Toast.makeText(getContext(), "Clicked: " + itemClicked, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key",position);
                    intent.putExtra("clickedItemName", itemClicked);
                    intent.putExtra("FRAGMENT_ID","Fragment5");
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
            intent.putExtra("FRAGMENT_ID","Fragment5");
            startActivity(intent);
        }
    }
}

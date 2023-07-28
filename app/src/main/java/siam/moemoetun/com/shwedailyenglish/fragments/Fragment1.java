package siam.moemoetun.com.shwedailyenglish.fragments;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.adapter.ExpandableListAdapter;
import siam.moemoetun.com.shwedailyenglish.webview.Gram_web;

public class Fragment1 extends Fragment {


   private InterstitialAd mInterstitialAd;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireContext(),getString(R.string.interstial_2021),
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
                        String errorDomain = loadAdError.getDomain();
                        int errorCode = loadAdError.getCode();
                        AdError cause = loadAdError.getCause();
                        String errorMessage = loadAdError.getMessage();
                        ResponseInfo responseInfo = loadAdError.getResponseInfo();
                        //Toast.makeText(getContext(), getString(R.string.no_item_found), Toast.LENGTH_SHORT).show();
                        mInterstitialAd = null;
                    }
                });


        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        final int groupPosition, final int childPosition, long id) {

                if (mInterstitialAd !=null) {
                    mInterstitialAd.show((Activity) getContext());
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            Intent intent = new Intent(getContext(), Gram_web.class);
                            intent.putExtra("key",groupPosition);
                            intent.putExtra("key1",childPosition);
                            startActivity(intent);
                            mInterstitialAd = null;
                            // Called when fullscreen content is dismissed.
                            Log.d("TAG", "The ad was dismissed.");
                        }
                    });

                }else {
                    Intent intent = new Intent(getContext(), Gram_web.class);
                    intent.putExtra("key",groupPosition);
                    intent.putExtra("key1",childPosition);
                    startActivity(intent);
                }

                return false;

            }
        });


    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("ဝါစဂၤ ဂ ပါး");
        listDataHeader.add("Be - ရွိသည္။ျဖစ္သည္");
        listDataHeader.add("Verb to do (12 Tenses)");
        listDataHeader.add("Passive Voice");
        listDataHeader.add("Clause ၅ မ်ိဳး");
        listDataHeader.add("Verb to have - မွာရွိသည္");
        listDataHeader.add("Comparison - ႏႈိင္းယွန္ျခင္း");
        listDataHeader.add("Linking Vebrs");
        listDataHeader.add("Adjective + Preposition");
        listDataHeader.add("Modal Vebs");
        listDataHeader.add("V-ing ႏွင့္ V + to ကိုသုံးနည္း");


        // Adding child data
        List<String> Grammar = new ArrayList<String>();
        Grammar.add("Noun -နာမ္");
        Grammar.add("Pronoun - နာမ္စား");
        Grammar.add("Adjective - နာမဝိေသသန");
        Grammar.add("Verbs - ၾကိယာ");
        Grammar.add("Adverb - ၾကိယာဝိေသသန");
        Grammar.add("Preposition - ဝိတ္ဘတ္");
        Grammar.add("Conjunction - စကားဆက္");
        Grammar.add("Interjection - အာေမဍိတ္");


        List<String> Grammar1 = new ArrayList<String>();
        Grammar1.add("Be (am,is,are) ရွိသည္၊၊ျဖစ္သည္ (Present)");
        Grammar1.add("Be (was/were) ရွိခဲ့သည္၊ျဖစ္ခဲ့သည္ (Past)");
        Grammar1.add("Be - will be/would be - အနာဂတ္");
        Grammar1.add("Be- have been/has been - ျပီးစီးကါလ");
        Grammar1.add("Be + Adjective");
        Grammar1.add("Be + preposition");
        Grammar1.add("There + be + Noun. - ရွိသည္ (တည္ေနရာ)");
        Grammar1.add("Be + wh Questions");


        List<String> Grammar2 = new ArrayList<String>();
        Grammar2.add("Present Simple (I do - လုပ္သည္)");
        Grammar2.add("Present Continuous (I'm doing - လုပ္ေနသည္ ");
        Grammar2.add("Present Perfect - (I've done.  ျပီးျပီ");
        Grammar2.add("Present Perfect Continuous - လုပ္ျပီးေနသည္");
        Grammar2.add("Past Simple - I did - လုပ္ခဲ့သည္");
        Grammar2.add("Past Continuous - I was doing-လုပ္ေနခဲ့သည္");
        Grammar2.add("Past Perfect - I had done-လုပ္ခဲ့ျပီးျပီး");
        Grammar2.add("Past Perfect Continuous - လုပ္ျပီး ေနခဲ့");
        Grammar2.add("Future Simple - I will do-လုပ္လိမ့္မယ္");
        Grammar2.add("Future Continuous - I'll be doing - လုပ္ေနလိမ့္မည္");
        Grammar2.add("Future Perfect - I will have done -လုပ္ျပီးလိမ့္မည္");
        Grammar2.add("Future Perfect Continuous - လုပ္ျပီးျပီး ေနလိမ့္မည္");

        List<String> Grammar3 = new ArrayList<String>();
        Grammar3.add("Present Simple (S + am/is/are + V3)");
        Grammar3.add("Present Continuous (S+ am/is/are + V3)");
        Grammar3.add("Present Perfect (S + have/has been + V3");
        Grammar3.add("Present Perfect Continuous- (S + have/has been + being V3");
        Grammar3.add("Past Simple (S+ was/were + v3");
        Grammar3.add("Past Continuous (S+ was/were + being + V3");
        Grammar3.add("Past Perfect - (S + had been + v3.");
        Grammar3.add("Past Perfect Continuous (S+ had been being + v3");
        Grammar3.add("Future Simple - (S + will be + v3");
        Grammar3.add("Future Continuous - (S + will be being + v3");
        Grammar3.add("Future Perfect (S + will have beeen + v3");
        Grammar3.add("Future Perfect Continuous - S + will have been being + v3");


        List<String> Grammar4 = new ArrayList<String>();
        Grammar4.add("Noun Clause");
        Grammar4.add("Adjective Clause");
        Grammar4.add("Adverb Clause");
        Grammar4.add("V-ing Clause");
        Grammar4.add("Ve -ed Clause");


        List<String> Grammar5 = new ArrayList<String>();
        Grammar5.add("Present Tense - Positive ");
        Grammar5.add("Present tense - Negative");
        Grammar5.add("Present tense - Question");
        Grammar5.add("Present tense - Negative Question");
        Grammar5.add("Past tense ");
        Grammar5.add("have got/has got");
        Grammar5.add("There + be + Noun. - ရွိသည္ (တည္ေနရာ)");
        Grammar5.add("Be + wh Questions");


        List<String> Grammar6 = new ArrayList<String>();
        Grammar6.add("Comparative ႏွင့္ Superlative ေျပာင္းပံု");
        Grammar6.add("Comparison -ႏိႈင္းယွန္မႈ ပုစံ ၁");
        Grammar6.add("Comparison -ႏိႈင္းယွန္မႈ ပုစံ ၂");
        Grammar6.add("Comparison -ႏိႈင္းယွန္မႈ ပုစံ ၃");

        List<String> Grammar7 = new ArrayList<String>();
        Grammar7.add("Linking verb ဆိုတာဘာလဲ");
        Grammar7.add("Go");
        Grammar7.add("Come");
        Grammar7.add("Keep");
        Grammar7.add("Get");
        Grammar7.add("Appear/prove/turn out");
        Grammar7.add("Look, Sound, Smell, Taste, etc");
        Grammar7.add("Look like, feel like, smell llike, etc");

        List<String> Grammar8 = new ArrayList<String>();
        Grammar8.add("Adjective + of/to");
        Grammar8.add("Adjective + with/about");
        Grammar8.add("Adjective + of (2)");


        List<String> Grammar9 = new ArrayList<String>();
        Grammar9.add("Will -လိမ့္မည္");
        Grammar9.add("Will not/won't လိမ့္မည္မဟုတ္");
        Grammar9.add("Will you? လိမ့္မည္လား");
        Grammar9.add("Can ႏိုင္သည္");
        Grammar9.add("Can't - မႏိုင္ဘူး");
        Grammar9.add("Can you? ႏိုင္သလား");
        Grammar9.add("Should သင့္သည္");
        Grammar9.add("Shouldn't -မသင့္ဘူး");
        Grammar9.add("Should you? သင့္သလား");
        Grammar9.add("May ျဖစ္ႏိုင္သည္");
        Grammar9.add("May ...? ျဖစ္ႏိုင္လား");
        Grammar9.add("May not - မျဖစ္ႏိုင္");

        Grammar9.add("Must - ရမယ္");
        Grammar9.add("Could - ႏိုင္ခဲ့သည္");
        Grammar9.add("Shall we ......? ၾကမလားး");
        Grammar9.add("Modal Verb + Adjective");

        Grammar9.add("Modal Verbs + Preposition");
        Grammar9.add("Passive with Modal verbs");
        Grammar9.add("Wh Question with Modal Verbsး");

        List<String>Grammar10 = new ArrayList<String>();
        Grammar10.add("how to use v-ing.");
        Grammar10.add("Verb + -ing");
        Grammar10.add("prefer and would rather");
        Grammar10.add("preposition + V-ing");
        Grammar10.add("Verb + -ing or to 1");
        Grammar10.add("Verb + -ing or to 2");
        Grammar10.add("Verb + -ing or to 3");
        Grammar10.add("Verb +to..");
        Grammar10.add("verb + object + to..");
        Grammar10.add("verb + object + preposition + v-ing");


        listDataChild.put(listDataHeader.get(0), Grammar); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Grammar1);
        listDataChild.put(listDataHeader.get(2), Grammar2);
        listDataChild.put(listDataHeader.get(3), Grammar3); // Header, Child data
        listDataChild.put(listDataHeader.get(4), Grammar4);
        listDataChild.put(listDataHeader.get(5), Grammar5);
        listDataChild.put(listDataHeader.get(6), Grammar6);
        listDataChild.put(listDataHeader.get(7), Grammar7);
        listDataChild.put(listDataHeader.get(8), Grammar8);
        listDataChild.put(listDataHeader.get(9), Grammar9);
        listDataChild.put(listDataHeader.get(10),Grammar10);
    }
}

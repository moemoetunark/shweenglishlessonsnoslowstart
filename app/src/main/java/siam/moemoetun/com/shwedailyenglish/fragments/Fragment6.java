package siam.moemoetun.com.shwedailyenglish.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.adapter.ExpandableListAdapter;
import siam.moemoetun.com.shwedailyenglish.webview.DetailsWebView;

public class Fragment6 extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private InterstitialAd mInterstitialAd;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareListData();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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



        expListView = view.findViewById(R.id.lvExp);

        // preparing list data

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
                //Toast.makeText(getContext(),
                // listDataHeader.get(groupPosition) + " Expanded",
                //Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // Toast.makeText(getContext(),
                //listDataHeader.get(groupPosition) + " Collapsed",
                //Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        final int groupPosition, final int childPosition, long id) {
                // TODO Auto-generated method stub

                if (mInterstitialAd !=null) {
                    mInterstitialAd.show((Activity) getContext());
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            Intent intent = new Intent(getContext(), DetailsWebView.class);
                            String clickedItemName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                            intent.putExtra("key",groupPosition);
                            intent.putExtra("key1",childPosition);
                            intent.putExtra("clickedItemName", clickedItemName);
                            String toastMessage = "Clicked: " + clickedItemName;
                            intent.putExtra("FRAGMENT_ID","Fragment6");
                            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            mInterstitialAd = null;
                            // Called when fullscreen content is dismissed.
                            Log.d("TAG", "The ad was dismissed.");
                        }
                    });

                }else {
                    Intent intent = new Intent(getContext(), DetailsWebView.class);
                    String clickedItemName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                    intent.putExtra("key",groupPosition);
                    intent.putExtra("key1",childPosition);
                    intent.putExtra("clickedItemName", clickedItemName);
                    String toastMessage = "Clicked: " + clickedItemName;
                    intent.putExtra("FRAGMENT_ID","Fragment6");
                    Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
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
        listDataHeader.add("Interchange Level 1");
        listDataHeader.add("Interchange Level 2- Greeting");
        listDataHeader.add("Interchange Level 2(အလုပ်အကိုင်)");
        listDataHeader.add("Interchange Level 2 - Shopping");
        listDataHeader.add("Interchange Level 2 - Talking about Music, Like and Dislike");
        listDataHeader.add("Interchange Level 2- About family");
        listDataHeader.add("Interchange Level 2- Sports and Fitness Activity");
        listDataHeader.add("Interchange Level 2 - Free time and Weekend");


        // Adding child data
        List<String> Grammar = new ArrayList<String>();
        Grammar.add("It's nice to meet you.\\nတွေ့ရတာဝမ်းသာပါတယ်");
        Grammar.add("He's over there. သူဟိုမှာ");
        Grammar.add("It's ...interesting.\n စိတ်ဝင်စားစရာပဲ");
        Grammar.add("Oh, no! မဟုတ်သေးပါဘူး");
        Grammar.add("Are you from Seoul? \nနင် ဆိုးလ်မြို့ကလား");
        Grammar.add("He's cute. သူကချစ်စရာလေးနော်");
        Grammar.add("It's disaster.အရမ်းဆိုးတာပဲ");
        Grammar.add("It's really cold.\nအရမ်းအေးတယ်");

        Grammar.add("What time is it there?\nအဲ့ဒီမှာ ဘယ်အချိန် ရှိပြီလဲ");
        Grammar.add("I'm really hungry.\nငါဗိုက်အရမ်းဆာနေတယ်");
        Grammar.add("Nice Car. သိပ်ကေါင်းတဲ့ကားနော်");
        Grammar.add("I get up at noon.\nငါနေ့လည်မှ အိပ်ရာကထတယ်");
        Grammar.add("My new apartment.\nငါ့ရဲ့အခန်းသစ်");
        Grammar.add("There aren't any chairs. \nကုလားထိုင်တွေ မရှိဘူး");
        Grammar.add("He works in a hotel.\\nသူဟိုတယ်မှာ အလုပ်လုပ်တယ်");
        Grammar.add("Please be careful.\\n ကျေးဇူးပြုလို့ ဂရုစိုက်ပါ။");
        Grammar.add("How about some sandwiches?\\nအသားညှပ်ပေါင်မုန့်ဆို ဘယ်လိုလဲ");
        Grammar.add("Fish for the breakfast \\n မနက်စာအတွက် ငါး");
        Grammar.add("I love sports\\nအားကစားကို နှစ်သက်တယ်");
        Grammar.add("I can't sing. သီချင်းမဆိုတတ်ဘူး");
        Grammar.add("Birthday plan မွေးနေ့အစီစဉ်");
        Grammar.add("Valentine's Day -ချစ်သူများနေ့");
        Grammar.add("I don't feel well. \\n နေမကေါင်းဘူး");
        Grammar.add("Don't work too hard \\n သိပ်ပင်ပန်းခံပြီး အလုပ်မလုပ်နဲ့");
        Grammar.add("It's emergency အရေးပေါ်");
        Grammar.add("Is it far from here? \\n ဒီကနေဝေးလား");






        List<String> Grammar1 = new ArrayList<String>();
        Grammar1.add("Please call me Beth. \\n Beth လို့ခေါ်ပါ");
        Grammar1.add("What's Seoul like?\\n ဆိုးလ်မြိုက ဘယ်လိုလဲ");
        Grammar1.add("How's it going?\\nနေကေါင်းရဲ့လား");


        List<String> Grammar2 = new ArrayList<String>();
        Grammar2.add("Where do you work?\\nနင်ဘယ်မှာ အလုပ်လုပ်လဲ");
        Grammar2.add("I start work at five.\\n ငါးနာရီမှာ ငါစအလုပ်လုပ်တယ်");



        List<String>Grammar3 = new ArrayList<String>();
        Grammar3.add("It's really pretty. \\nဒါလေးအရမ်းလှတယ်");
        Grammar3.add("I prefer the blue one\\nအပြာရောင်တစ်ခုကို ပိုကြိုက်တယ်");

        List<String> Grammar4 = new ArrayList<String>();
        Grammar4.add("Who is your favorite singer?");
        Grammar4.add("An invitation");

        List<String> Grammar5 = new ArrayList<String>();
        Grammar5.add("Asking about Family");
        Grammar5.add("Is that typical?");



        List<String> Grammar6 = new ArrayList<String>();
        Grammar6.add("I hardly ever exercise.\\nတစ်ခါတစ်ရံ့ပဲလေ့ကျင့်ခန်းလုပ်တယ်");
        Grammar6.add("I'm a real fitness freak.\\nကျန်းမာရေးအရမ်းလိုက်စားသူ");

        List<String> Grammar7 = new ArrayList<String>();
        Grammar7.add("Did you do anything special?");
        Grammar7.add("How was your vocation? \\nနင့်အားလပ်ရက်ခရီးဘယ်လိုလဲ");





        listDataChild.put(listDataHeader.get(0), Grammar); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Grammar1);
        listDataChild.put(listDataHeader.get(2), Grammar2);
        listDataChild.put(listDataHeader.get(3), Grammar3); // Header, Child data
        listDataChild.put(listDataHeader.get(4), Grammar4);
        listDataChild.put(listDataHeader.get(5), Grammar5);
        listDataChild.put(listDataHeader.get(6), Grammar6);
        listDataChild.put(listDataHeader.get(7), Grammar7);




    }


}

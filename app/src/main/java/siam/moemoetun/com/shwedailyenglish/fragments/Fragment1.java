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
import siam.moemoetun.com.shwedailyenglish.quiz.QuizMain;
public class Fragment1 extends Fragment {
   private InterstitialAd mInterstitialAd;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareListData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireContext(),getString(R.string.shwe_lessons_preload),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });


        expListView = view.findViewById(R.id.lvExp);
        // preparing list data
        //prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
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
                            Intent intent = new Intent(getContext(), QuizMain.class);
                            String clickedItemName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                            intent.putExtra("key",groupPosition);
                            intent.putExtra("key1",childPosition);
                            intent.putExtra("clickedItemName", clickedItemName);
                            intent.putExtra("selectedCategory", clickedItemName);
                            startActivity(intent);
                            mInterstitialAd = null;
                            // Called when fullscreen content is dismissed.
                            Log.d("TAG", "The ad was dismissed.");
                        }
                    });

                }else {
                    Intent intent = new Intent(getContext(), QuizMain.class);
                    String clickedItemName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                    String toastMessage = "Clicked: " + clickedItemName;
                    Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key",groupPosition);
                    intent.putExtra("key1",childPosition);
                    intent.putExtra("clickedItemName", clickedItemName);
                    intent.putExtra("selectedCategory", clickedItemName);
                    intent.putExtra("FRAGMENT_ID","Fragment1");
                    startActivity(intent);
                }

                return false;

            }
        });


    }


    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Eight Parts of Speech -ဝါစင်္ဂ ၈ပါး");
        listDataHeader.add("Verb to be - ရှိသည်ဖြစ်သည်");
        listDataHeader.add("Verb to do (12 Tenses)");
        listDataHeader.add("Passive Voice");
        listDataHeader.add("Clause ငါးမျိုး");
        listDataHeader.add("Verb to have -မှာရှိသည်");
        listDataHeader.add("Comparison - နှိုင်းယှဥ်ခြင်း");
        listDataHeader.add("Linking Vebrs");
        listDataHeader.add("Adjective + Preposition");
        listDataHeader.add("Modal Vebs");
        listDataHeader.add("V+ing နှင့် V+to ကိုသုံးနည်း");


        // Adding child data
        List<String> Grammar = new ArrayList<>();
        Grammar.add("Nouns -နာမ်");
        Grammar.add("Pronoun - နာမ်စား");
        Grammar.add("Adjective-နာမဝိသေသန");
        Grammar.add("Verbs - ကြိယာ");
        Grammar.add("Adverb - ကြိယာဝိသေသန");
        Grammar.add("Preposition - ဝိဘတ်");
        Grammar.add("Conjunction - စကားဆက်");
        Grammar.add("Interjection - အာမေဍိတ်");


        List<String> Grammar1 = new ArrayList<>();
        Grammar1.add("Be (am,is,are) ရှိသည်၊၊ဖြစ်သည်");
        Grammar1.add("Be (was/were) ရှိခဲ့သည်၊ဖြစ်ခဲ့သည်");
        Grammar1.add("Be - will be/would be - အနာဂတ်");
        Grammar1.add("Be- have been/has been - ပြီးစီးကါလ");
        Grammar1.add("Be + Adjective");
        Grammar1.add("Be + preposition");
        Grammar1.add("There + be + Noun. - ရှိသည် (တည်နေရာ)");
        Grammar1.add("Be + wh Questions");


        List<String> Grammar2 = new ArrayList<>();
        Grammar2.add("Present Simple (I do - လုပ်သည်)");
        Grammar2.add("Present Continuous -I'm doing - လုပ်နေသည်");
        Grammar2.add("Present Perfect - (I've done.  ပြီးပြီ");
        Grammar2.add("Present Perfect Continuous - လုပ်ပြီးနေသည်");
        Grammar2.add("Past Simple - I did - လုပ်ခဲ့သည်");
        Grammar2.add("Past Continuous - I was doing-လုပ်နေခဲ့သည်");
        Grammar2.add("Past Perfect - I had done-လုပ်ခဲ့ပြီးပြီး");
        Grammar2.add("Past Perfect Continuous - လုပ်ပြီး နေခဲ့");
        Grammar2.add("Future Simple - I will do-လုပ်လိမ့်မယ်");
        Grammar2.add("Future Continuous - I'll be doing - လုပ်နေလိမ့်မည်");
        Grammar2.add("Future Perfect - I will have done -လုပ်ပြီးလိမ့်မည်");
        Grammar2.add("Future Perfect Continuous - လုပ်ပြီးပြီး နေလိမ့်မည်");

        List<String> Grammar3 = new ArrayList<>();
        Grammar3.add("Present Simple (S + am/is/are + V3)");
        Grammar3.add("Present Continuous (S+ am/is/are + V3)");
        Grammar3.add("Present Perfect -S + have/has been + V3");
        Grammar3.add("Present Perfect Continuous- (S + have/has been + being V3");
        Grammar3.add("Past Simple (S+ was/were + v3");
        Grammar3.add("Past Continuous (S+ was/were + being + V3");
        Grammar3.add("Past Perfect - (S + had been + v3.");
        Grammar3.add("Past Perfect Continuous (S+ had been being + v3");
        Grammar3.add("Future Simple - (S + will be + v3");
        Grammar3.add("Future Continuous - (S + will be being + v3");
        Grammar3.add("Future Perfect (S + will have beeen + v3");
        Grammar3.add("Future Perfect Continuous - S + will have been being + v3");
        Grammar3.add("Passive Voice အကျည်းချုပ်");
        Grammar3.add("Irregular Verbs to from Passive Voice");
        Grammar3.add("ကံလိုကြိယာနှင့် ကံမလိုကြိယာ (Transitive and Intransitive Verbs");
        Grammar3.add("Get နှင့် Passive Voice သုံးပုံ");
        Grammar3.add("Modal verb နှင့် Passive Voice သုံးပုံ");
        Grammar3.add("Passive Voice with Agent");
        Grammar3.add("Passive Voice without Agent");
        Grammar3.add("Object နှစ်လုံးပါသည့် Passive Voice ပုံစံ");


        List<String> Grammar4 = new ArrayList<>();
        Grammar4.add("Noun Clause");
        Grammar4.add("Adjective Clause");
        Grammar4.add("Adverb Clause");
        Grammar4.add("V-ing Clause");
        Grammar4.add("V-ed Clause");


        List<String> Grammar5 = new ArrayList<>();
        Grammar5.add("Present Tense - Positive");
        Grammar5.add("Present tense - Negative");
        Grammar5.add("Present tense - Question");
        Grammar5.add("Present tense - Negative Question");
        Grammar5.add("Past tense (had)");
        Grammar5.add("have got/has got");


        List<String> Grammar6 = new ArrayList<>();
        Grammar6.add("Comparative နှင့် Superlative ပြောင်းပုံ");
        Grammar6.add("Comparison -နှိုင်းယှန်မှု ပုစံ ၁");
        Grammar6.add("Comparison -နှိုင်းယှန်မှု ပုစံ ၂");
        Grammar6.add("Comparison -နှိုင်းယှန်မှု ပုစံ ၃");

        List<String> Grammar7 = new ArrayList<>();
        Grammar7.add("Linking verb ဆိုတာဘာလဲ");
        Grammar7.add("Go");
        Grammar7.add("Come");
        Grammar7.add("Keep");
        Grammar7.add("Get");
        Grammar7.add("Appear/prove/turn out");
        Grammar7.add("Look, Sound, Smell, Taste, etc");
        Grammar7.add("Look like, feel like, smell llike, etc");

        List<String> Grammar8 = new ArrayList<>();
        Grammar8.add("Adjective + of/to");
        Grammar8.add("Adjective + with/about");
        Grammar8.add("Adjective + of (2)");


        List<String> Grammar9 = new ArrayList<>();
        Grammar9.add("Will -လိမ့်မည်");
        Grammar9.add("Will not/won't လိမ့်မည်မဟုတ်");
        Grammar9.add("Will you? လိမ့်မည်လား");
        Grammar9.add("Can နိုင်သည်");
        Grammar9.add("Can't - မနိုင်ဘူး");
        Grammar9.add("Can you? နိုင်သလား");
        Grammar9.add("Should သင့်သည်");
        Grammar9.add("Shouldn't -မသင့်ဘူး");
        Grammar9.add("Should you? သင့်သလား");
        Grammar9.add("May ဖြစ်နိုင်သည်");
        Grammar9.add("May ...? ဖြစ်နိုင်လား");
        Grammar9.add("May not - မဖြစ်နိုင်");
        Grammar9.add("Must - ရမယ်");
        Grammar9.add("Could - နိုင်ခဲ့သည်");
        Grammar9.add("Shall we ......? ကြမလားး");
        Grammar9.add("Modal Verb + Adjective");
        Grammar9.add("Modal Verbs + Preposition");
        Grammar9.add("Passive with Modal verbs");
        Grammar9.add("Wh Question with Modal Verbs");

        List<String>Grammar10 = new ArrayList<>();
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

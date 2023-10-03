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
public class Fragment2 extends Fragment {
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
        return inflater.inflate(R.layout.fragment_gallery, container, false);
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


        expListView = view.findViewById(R.id.lvExp);

        // preparing list data

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
                //Toast.makeText(getContext(),
                // listDataHeader.get(groupPosition) + " Expanded",
                // Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getContext(),
                //listDataHeader.get(groupPosition) + " Collapsed",
                //Toast.LENGTH_SHORT).show();

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
                            Intent intent = new Intent(getContext(), DetailsWebView.class);
                            String clickedItemName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                            intent.putExtra("key",groupPosition);
                            intent.putExtra("key1",childPosition);
                            intent.putExtra("clickedItemName", clickedItemName);
                            String toastMessage = "Clicked: " + clickedItemName;
                            intent.putExtra("FRAGMENT_ID","Fragment2");
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
                    intent.putExtra("FRAGMENT_ID","Fragment2");
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
        listDataHeader.add("School Life - ကျောင်းသားဘဝ");
        listDataHeader.add("About Job - အလုပ်အကြောင်း");
        listDataHeader.add("Talking about House)");
        listDataHeader.add("Taking Bus - ဘတ်စ်ကားစီးခြင်း");
        listDataHeader.add("Talking about Banking");
        listDataHeader.add("Social Networking - လူမှုကွန်ယက်");
        listDataHeader.add("Shopping - ဈေးဝယ်ခြင်း");
        listDataHeader.add("Dating - ချိန်းဆိုခြင်း");
        listDataHeader.add("Health - ကျန်းမာရေး");
        listDataHeader.add("Sports - အားကစား");
        listDataHeader.add("eating out - အပြင်ထွက်စားခြင်း");
        listDataHeader.add("Voting - မေးပေးခြင်း");
        listDataHeader.add("Talking about nature");



        // Adding child data
        List<String> Grammar = new ArrayList<>();
        Grammar.add("Class Registration");
        Grammar.add("A laptop for school");
        Grammar.add("New Student");
        Grammar.add("Choosing partner");
        Grammar.add("Class Presentation");
        Grammar.add("Doing the right thing");
        Grammar.add("Eating in the class");
        Grammar.add("English dictionary");

        Grammar.add("First day of school");
        Grammar.add("Forget it at home");
        Grammar.add("Living in the dooms");
        Grammar.add("Popularity");
        Grammar.add("School and Work");
        Grammar.add("Show and Tell");
        Grammar.add("The bully");
        Grammar.add("Borrowing books from the library");


        List<String> Grammar1 = new ArrayList<>();
        Grammar1.add("Choosing a job - အလုပ်ရွေးခြင်း");
        Grammar1.add("Money and Happiness");
        Grammar1.add("A job at 16");
        Grammar1.add("Starting a business");
        Grammar1.add("You have to get a job");
        Grammar1.add("Finding a job");
        Grammar1.add("Job interview");
        Grammar1.add("Being a teachers");

        Grammar1.add("The first job");
        Grammar1.add("A bad first day of work");
        Grammar1.add("A job at 16");
        Grammar1.add("Lunch break");
        Grammar1.add("Night Shift");
        Grammar1.add("Customers are always right");
        Grammar1.add("A bad customer");
        Grammar1.add("Framed");

        Grammar1.add("Should I move?");
        Grammar1.add("Overtime");
        Grammar1.add("Night owl");


        List<String> Grammar2 = new ArrayList<>();
        Grammar2.add("Key Confusion");
        Grammar2.add("Nice Blue Color");
        Grammar2.add("Broke Window");
        Grammar2.add("Air Conditioning");
        Grammar2.add("Nails on the wall");
        Grammar2.add("Christmas Decorations");
        Grammar2.add("Outdoor Barbecue");
        Grammar2.add("Roommate");
        Grammar2.add("Pets in the house");
        Grammar2.add("Late Mortgage Payment");
        Grammar2.add("Closet Spaces");
        Grammar2.add("The big Announcement");
        Grammar2.add("Noisy Neighbor");


        List<String>Grammar3 = new ArrayList<>();
        Grammar3.add("Bus Lines");
        Grammar3.add("Bus Schedule");
        Grammar3.add("Alternate Bus Route");
        Grammar3.add("Bus Tickets");
        Grammar3.add("Mini Bus");
        Grammar3.add("Discounts");
        Grammar3.add("So Many Bus Lines");
        Grammar3.add("Bus Route");
        Grammar3.add("Buying Tickets on The Bus");
        Grammar3.add("Falling Asleep On the Bus");


        List<String> Grammar4 = new ArrayList<>();
        Grammar4.add("Different Bank Accounts");
        Grammar4.add("Opening a Bank Account");
        Grammar4.add(" ATM Card Being Declined");
        Grammar4.add("Making a Deposit");
        Grammar4.add("Making a Withdrawal");

        Grammar4.add(" Using ATM");
        Grammar4.add("Asking about Fees");
        Grammar4.add("Paying Fees");



        List<String> Grammar5 = new ArrayList<>();
        Grammar5.add("Online Accounts");
        Grammar5.add("Joining Facebook");
        Grammar5.add("Profile Picture");
        Grammar5.add("Birthdate");
        Grammar5.add("Phone Applications");
        Grammar5.add("Foreign Friends");

        Grammar5.add("Deleting Profiles");
        Grammar5.add("Blogging");
        Grammar5.add("Irresponsible");


        List<String> Grammar6 = new ArrayList<>();
        Grammar6.add("Nice Shoes");
        Grammar6.add("A sale person");
        Grammar6.add("A woman's Eyes");
        Grammar6.add("Choosing Flower");
        Grammar6.add("Picky Shopper");
        Grammar6.add("Buying A bicycle");
        Grammar6.add("Online Shopping");
        Grammar6.add("Bargaining ");
        Grammar6.add("A bad Employee");
        Grammar6.add("A hat");
        Grammar6.add("Skateboard");


        List<String> Grammar7 = new ArrayList<>();
        Grammar7.add("Should I .....?");
        Grammar7.add("Dating Two people");
        Grammar7.add("Personality");

        Grammar7.add("Forgotten Aniversary");
        Grammar7.add("Paying the restaurant Bill");
        Grammar7.add("Stood up");
        Grammar7.add("Money Lover");
        Grammar7.add("Date Locations");
        Grammar7.add("Breaking Up");
        Grammar7.add("Dating After College");
        Grammar7.add("A bad Boyfriend");
        Grammar7.add("The first Kiss");


        List<String> Grammar8 = new ArrayList<>();
        Grammar8.add("Caught A cold");
        Grammar8.add("Emergency Kit");
        Grammar8.add("Running A feverး");
        Grammar8.add("A disease");
        Grammar8.add("Face Mask");
        Grammar8.add("Swallowed Coin");
        Grammar8.add("Concussion");
        Grammar8.add("Too much coffee");
        Grammar8.add("Insomnia");
        Grammar8.add("Losing Weight");
        Grammar8.add("Broken Legs");
        Grammar8.add("Washing Hands");

        Grammar8.add("Yoga");
        Grammar8.add("Wait 30 minutes");

        List<String>Grammar9 = new ArrayList<>();
        Grammar9.add("Joining A club");
        Grammar9.add("Swimming 1");
        Grammar9.add("Swimming 2");
        Grammar9.add("Soccer Ball");
        Grammar9.add("Running");
        Grammar9.add("Basketball");
        Grammar9.add("Badminton");
        Grammar9.add("Baseball");
        Grammar9.add("Boxing");
        Grammar9.add("Track Racing");

        Grammar9.add("Cycling");
        Grammar9.add("Snowboard");
        Grammar9.add("Hockey");
        Grammar9.add("Watching A soccer game");


        List<String>Grammar10 = new ArrayList<>();

        Grammar10.add("Where to eat?");
        Grammar10.add("Spicy is the best");
        Grammar10.add("Popular Hot_dog");
        Grammar10.add("A New Item");
        Grammar10.add("Waiting outside");
        Grammar10.add("Getting Sandwich");
        Grammar10.add("The picky Eater");
        Grammar10.add("Out of Ice tea");
        Grammar10.add("Allergic");
        Grammar10.add("This chicken is so plain.");
        Grammar10.add("Food Poisoning");
        Grammar10.add("Breakfast for Dinner");


        List<String>Grammar11 = new ArrayList<>();
        Grammar11.add("Civic Cuty");
        Grammar11.add("The great Divide");
        Grammar11.add("School Election");
        Grammar11.add("Losing Stereotypes");
        Grammar11.add("Freedom of Choice");
        Grammar11.add("Obama Care");
        Grammar11.add("Rock the vote");
        Grammar11.add("Voter ID");
        Grammar11.add("Voting Age");
        Grammar11.add("Proxy Voting");


        List<String>Grammar12 = new ArrayList<>();
        Grammar12.add("Four Seasons");
        Grammar12.add("Going Camping");
        Grammar12.add("Trees");
        Grammar12.add("Flowers");
        Grammar12.add("Bees");
        Grammar12.add("Poison Ivy");
        Grammar12.add("Sun Burn");
        Grammar12.add("Waterfalls");
        Grammar12.add("Rain");
        Grammar12.add("The snow");
        Grammar12.add("Lightning");
        Grammar12.add("Thunder");


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
        listDataChild.put(listDataHeader.get(10), Grammar10);
        listDataChild.put(listDataHeader.get(11), Grammar11);
        listDataChild.put(listDataHeader.get(12), Grammar12);
        // listDataChild.put(listDataHeader.get(13), Grammar13);

    }
}

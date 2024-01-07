package siam.moemoetun.com.shwedailyenglish.webview;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.concurrent.atomic.AtomicBoolean;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.utility.GoogleMobileAdsConsentManager;
import siam.moemoetun.com.shwedailyenglish.utility.ToolbarUtils;
public class DetailsWebView extends AppCompatActivity {
public WebView webView;
    private static final String TAG = "DetailsWebView";
    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
    private GoogleMobileAdsConsentManager googleMobileAdsConsentManager;
    private AdView adView;
    private FrameLayout adContainerView;
    private AtomicBoolean initialLayoutComplete = new AtomicBoolean(false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        adContainerView = findViewById(R.id.ad_view_container);
        // Log the Mobile Ads SDK version.
        Log.d(TAG, "Google Mobile Ads SDK Version: " + MobileAds.getVersion());
        googleMobileAdsConsentManager =
                GoogleMobileAdsConsentManager.getInstance(getApplicationContext());
        googleMobileAdsConsentManager.gatherConsent(
                this,
                consentError -> {
                    if (consentError != null) {
                        // Consent not obtained in current session.
                        Log.w(
                                TAG,
                                String.format("%s: %s", consentError.getErrorCode(), consentError.getMessage()));
                    }

                    if (googleMobileAdsConsentManager.canRequestAds()) {
                        initializeMobileAdsSdk();
                    }

                    if (googleMobileAdsConsentManager.isPrivacyOptionsRequired()) {
                        // Regenerate the options menu to include a privacy setting.
                        invalidateOptionsMenu();
                    }
                });

        // This sample attempts to load ads using consent obtained in the previous session.
        if (googleMobileAdsConsentManager.canRequestAds()) {
            initializeMobileAdsSdk();
        }



        adContainerView
                .getViewTreeObserver()
                .addOnGlobalLayoutListener(
                        () -> {
                            if (!initialLayoutComplete.getAndSet(true)
                                    && googleMobileAdsConsentManager.canRequestAds()) {
                                loadBanner();
                            }
                        });


        Log.d(TAG, "Google Mobile Ads SDK Version: " + MobileAds.getVersion());
        ToolbarUtils.setupToolbarWithCustomFont(
                this,
                toolbar,
                getIntent().getStringExtra("clickedItemName"),
                "fonts/tharlon.ttf" // Replace with your font path
        );

        AdRequest InterstitialAdRequest = new AdRequest.Builder().build();
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        String fragmentId = getIntent().getStringExtra("FRAGMENT_ID");
       if (fragmentId.equals("Fragment2")){
            displayConversation();
        }else if(fragmentId.equals("Fragment3")){
            displayStory();
        }else if(fragmentId.equals("Fragment5")){
            displayPatterns();
        }else if(fragmentId.equals("Fragment6")){
            displayInterchange();
        }
    }

    private void initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {}
                });

        // Load an ad.
        if (initialLayoutComplete.get()) {
            loadBanner();
        }
    }
    private void loadBanner() {
        // Create a new ad view.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_2021));
        adView.setAdSize(getAdSize());
        Bundle extras = new Bundle();
        extras.putString("collapsible", "bottom");
        // Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);
        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
           onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            this.webView.destroy();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private void displayConversation(){

        int intExtra = getIntent().getIntExtra("key",0);
        int intExtra2 = getIntent().getIntExtra("key1", 0);
        if (intExtra == 0 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/buying textbook.html");
        }
        if (intExtra == 0 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/registration.html");
        }
        if (intExtra == 0 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/A laptop for school.html");
        }
        if (intExtra == 0 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/A new Student.html");
        }
        if (intExtra == 0 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/Choosing partners.html");
        }
        if (intExtra == 0 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/Class Presentation.html");
        }
        if (intExtra == 0 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/Doing the right thing.html");
        }
        if (intExtra == 0 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/Eating in th class.html");
        }
        if (intExtra == 0 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/English Dictionary.html");
        }
        if (intExtra == 0 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/first day of school.html");
        }
        if (intExtra == 0 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/Fortget it at home.html");
        }
        if (intExtra == 0 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/conversation/Living the dorms.html");
        }
        if (intExtra == 0 && intExtra2 == 12) {
            webView.loadUrl("file:///android_asset/conversation/Popularity.html");
        }
        if (intExtra == 0 && intExtra2 == 13) {
            webView.loadUrl("file:///android_asset/conversation/School and Work.html");
        }
        if (intExtra == 0 && intExtra2 == 14) {
            webView.loadUrl("file:///android_asset/conversation/Show and Tell.html");
        }
        if (intExtra == 0 && intExtra2 == 15) {
            webView.loadUrl("file:///android_asset/conversation/The bully.html");
        }
        if (intExtra == 0 && intExtra2 == 16) {
            webView.loadUrl("file:///android_asset/conversation/To borrow it from the libery.html");
        }
        if (intExtra == 1 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1.Choosing a Job.html");
        }
        if (intExtra == 1 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2.Money and Happiness.html");
        }
        if (intExtra == 1 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3.A job at 16.html");
        }
        if (intExtra == 1 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4.Starting a business.html");
        }
        if (intExtra == 1 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5.You have to get a job.html");
        }
        if (intExtra == 1 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.Finding a job.html");
        }
        if (intExtra == 1 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7.Job interview.html");
        }
        if (intExtra == 1 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8.Being a teacher.html");
        }
        if (intExtra == 1 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9.The first job.html");
        }
        if (intExtra == 1 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.A bad first day of work.html");
        }
        if (intExtra == 1 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/11.Lunch Break.html");
        }
        if (intExtra == 1 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/conversation/12.Night Shift.html");
        }
        if (intExtra == 1 && intExtra2 == 12) {
            webView.loadUrl("file:///android_asset/conversation/13.Customers are always right.html");
        }
        if (intExtra == 1 && intExtra2 == 13) {
            webView.loadUrl("file:///android_asset/conversation/14.A bad customer.html");
        }
        if (intExtra == 1 && intExtra2 == 14) {
            webView.loadUrl("file:///android_asset/conversation/15.Framed.html");
        }
        if (intExtra == 1 && intExtra2 == 15) {
            webView.loadUrl("file:///android_asset/conversation/16.Should I move.html");
        }
        if (intExtra == 1 && intExtra2 == 16) {
            webView.loadUrl("file:///android_asset/conversation/17.A doctor or nurse.html");
        }
        if (intExtra == 1 && intExtra2 == 17) {
            webView.loadUrl("file:///android_asset/conversation/18.Overtime.html");
        }
        if (intExtra == 1 && intExtra2 == 18) {
            webView.loadUrl("file:///android_asset/conversation/19.Asking for a raise.html");
        }
        if (intExtra == 1 && intExtra2 == 19) {
            webView.loadUrl("file:///android_asset/conversation/20.Night Owl.html");
        }
        if (intExtra == 2 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/1.key confusion.html");
        }
        if (intExtra == 2 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/2.Nice Blue Color.html");
        }
        if (intExtra == 2 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/3.Broken Window.html");
        }
        if (intExtra == 2 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/4.Air Conditioning.html");
        }
        if (intExtra == 2 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/5.Nails in the wall.html");
        }
        if (intExtra == 2 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/6. Christmas Decorations.html");
        }
        if (intExtra == 2 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/7. Outdoor Barbeque.html");
        }
        if (intExtra == 2 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/8. Roommates.html");
        }
        if (intExtra == 2 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/9. Pets in the House.html");
        }
        if (intExtra == 2 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/10. Late Mortgage Payment.html");
        }
        if (intExtra == 2 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/11. Closet Space.html");
        }
        if (intExtra == 2 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/12. The Big Announcement.html");
        }
        if (intExtra == 2 && intExtra2 == 12) {
            webView.loadUrl("file:///android_asset/13. Noisy Neighbor.html");
        }
        if (intExtra == 3 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/1.Bus Lines.html");
        }
        if (intExtra == 3 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/2.Bus Schedule.html");
        }
        if (intExtra == 3 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/3. Alternate Bus Route.html");
        }
        if (intExtra == 3 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/4. Bus Tickets.html");
        }
        if (intExtra == 3 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/5.Mini Bus.html");
        }
        if (intExtra == 3 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/6.Discounts.html");
        }
        if (intExtra == 3 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/7.So Many Bus lines.html");
        }
        if (intExtra == 3 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/8.Bus Route.html");
        }
        if (intExtra == 3 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/9.Buying Tickets on the Bus.html");
        }
        if (intExtra == 3 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/10.Falling Asleep on the Bus.html");
        }
        if (intExtra == 4 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1. Different Bank Accounts.html");
        }
        if (intExtra == 4 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2. Opening a Bank Account.html");
        }
        if (intExtra == 4 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/atm_card_declined.HTML");
        }
        if (intExtra == 4 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4. Making a Deposit.html");
        }
        if (intExtra == 4 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5. Making a Withdrawal.html");
        }
        if (intExtra == 4 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.Transferring Money.html");
        }
        if (intExtra == 4 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7.Over withdrawal.html");
        }
        if (intExtra == 4 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8.Using ATM.html");
        }
        if (intExtra == 4 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9.Asking About Fees.html");
        }
        if (intExtra == 4 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.Paying Fees.html");
        }
        if (intExtra == 4 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/9.Asking About Fees.html");
        }
        if (intExtra == 5 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1. Online Accounts.html");
        }
        if (intExtra == 5 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2. Joining Facebook.html");
        }
        if (intExtra == 5 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3. Profile Picture.html");
        }
        if (intExtra == 5 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4. Birthdate.html");
        }
        if (intExtra == 5 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5. Private Messaging.html");
        }
        if (intExtra == 5 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.Phone Application.html");
        }
        if (intExtra == 5 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7.Foreign Friends.html");
        }
        if (intExtra == 5 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8.Deleting My Profile.html");
        }
        if (intExtra == 5 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9.Blogging.html");
        }
        if (intExtra == 5 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.Irresponsible.html");
        }
        if (intExtra == 6 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1.Nice Shoes.html");
        }
        if (intExtra == 6 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2.A Saleperson.html");
        }
        if (intExtra == 6 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3.A Woman's Eye.html");
        }
        if (intExtra == 6 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4.Choosing Flower.html");
        }
        if (intExtra == 6 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5.Picky Shopper.html");
        }
        if (intExtra == 6 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.Buying a Bicycle.html");
        }
        if (intExtra == 6 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7.Online Shopping.html");
        }
        if (intExtra == 6 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8.Bargaining.html");
        }
        if (intExtra == 6 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9.A Bad Employee.html");
        }
        if (intExtra == 6 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.A Hat.html");
        }
        if (intExtra == 6 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/11.Skateboard.html");
        }
        if (intExtra == 7 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1.should I.html");
        }
        if (intExtra == 7 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2.dating two people.html");
        }
        if (intExtra == 7 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3.personality.html");
        }
        if (intExtra == 7 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4.A Forgotten Anniversary.html");
        }
        if (intExtra == 7 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5.Paying the Restaurant Bill.html");
        }
        if (intExtra == 7 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.Stood Up.html");
        }
        if (intExtra == 7 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7.Money Lover.html");
        }
        if (intExtra == 7 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8.Date Locations.html");
        }
        if (intExtra == 7 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9.Breaking Up.html");
        }
        if (intExtra == 7 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.Dating After College.html");
        }
        if (intExtra == 7 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/11.A Bad Boyfriend.html");
        }
        if (intExtra == 7 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/conversation/12.The First Kiss.html");
        }
        if (intExtra == 8 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1.Caught a Cold.html");
        }
        if (intExtra == 8 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2.Emergency Kit.html");
        }
        if (intExtra == 8 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3. Runnig a Fever.html");
        }
        if (intExtra == 8 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4. A Disease.html");
        }
        if (intExtra == 8 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5.Face Mask.html");
        }
        if (intExtra == 8 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.EmergencySwallowed Coin.html");
        }
        if (intExtra == 8 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7. EmergencyConcussion.html");
        }
        if (intExtra == 8 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8.Too Much Coffee.html");
        }
        if (intExtra == 8 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9.Insomnia.html");
        }
        if (intExtra == 8 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.Losing Weight.html");
        }
        if (intExtra == 8 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/11. Broken Legs.html");
        }
        if (intExtra == 8 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/conversation/12. Washing Hands.html");
        }
        if (intExtra == 8 && intExtra2 == 13) {
            webView.loadUrl("file:///android_asset/conversation/13.Yoga.html");
        }
        if (intExtra == 8 && intExtra2 == 14) {
            webView.loadUrl("file:///android_asset/conversation/14. Wait 30 Minutes.html");
        }
        if (intExtra == 9 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/sports/1. Joining a Club.html");
        }
        if (intExtra == 9 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/sports/2. Swimming1.html");
        }
        if (intExtra == 9 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/sports/3. Swimming2.html");
        }
        if (intExtra == 9 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/sports/4. Soccer Ball.html");
        }
        if (intExtra == 9 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/sports/5.Running.HTML");
        }
        if (intExtra == 9 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/sports/6.Basketball.HTML");
        }
        if (intExtra == 9 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/sports/7.Badminton.HTML");
        }
        if (intExtra == 9 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/sports/8.Baseball.HTML");
        }
        if (intExtra == 9 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/sports/9.Boxing.HTML");
        }
        if (intExtra == 9 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/sports/10.Track Racing.HTML");
        }
        if (intExtra == 9 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/sports/11.Cycling.HTML");
        }
        if (intExtra == 9 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/sports/12.Snowboard.HTML");
        }
        if (intExtra == 9 && intExtra2 == 12) {
            webView.loadUrl("file:///android_asset/sports/13.Hockey.HTML");
        }
        if (intExtra == 9 && intExtra2 == 13) {
            webView.loadUrl("file:///android_asset/sports/14.Watching Soccer Games.HTML");
        }
        if (intExtra == 10 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1.where_to_eat.html");
        }
        if (intExtra == 10 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2.Spicy_Is_the Best.html");
        }
        if (intExtra == 10 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3.popular_hot_dog.html");
        }
        if (intExtra == 10 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4.anew_item.html");
        }
        if (intExtra == 10 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5.thel_long_line_outside.html");
        }
        if (intExtra == 10 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.Getting a Sandwich.html");
        }
        if (intExtra == 10 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7.The Picky Eater.html");
        }
        if (intExtra == 10 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8.Out of Iced Tea.html");
        }
        if (intExtra == 10 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9.Allergic.html");
        }
        if (intExtra == 10 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.This Chicken Is So Plain.html");
        }
        if (intExtra == 10 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/11.Food Poisoning.html");
        }
        if (intExtra == 10 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/conversation/12. Breakfast for Dinner.html");
        }
        if (intExtra == 11 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1.Civic Duty.html");
        }
        if (intExtra == 11 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2. The Great Divide.html");
        }
        if (intExtra == 11 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3. School Election.html");
        }
        if (intExtra == 11 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4.Losing Stereotypes.html");
        }
        if (intExtra == 11 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5. Freedom of Choice.html");
        }
        if (intExtra == 11 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6.Obamacare.html");
        }
        if (intExtra == 11 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7. Rock the Vote.html");
        }
        if (intExtra == 11 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8. Voter ID.html");
        }
        if (intExtra == 11 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9. Voting Age.html");
        }
        if (intExtra == 11 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.Proxy Voting.html");
        }
        if (intExtra == 12 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/conversation/1. Four Seasons.html");
        }
        if (intExtra == 12 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/conversation/2. Going Camping.html");
        }
        if (intExtra == 12 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/conversation/3. Trees.html");
        }
        if (intExtra == 12 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/conversation/4. Flowers.html");
        }
        if (intExtra == 12 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/conversation/5. Bees.html");
        }
        if (intExtra == 12 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/conversation/6. Poison Ivy.html");
        }
        if (intExtra == 12 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/conversation/7. Sunburn.html");
        }
        if (intExtra == 12 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/conversation/8. Waterfalls.html");
        }
        if (intExtra == 12 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/conversation/9. Rain.html");
        }
        if (intExtra == 12 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/conversation/10.The Snow.html");
        }
        if (intExtra == 12 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/conversation/11.lightning and Thunder.html");
        }
    }


    private void displayInterchange(){

        int intExtra = getIntent().getIntExtra("key",0);
        int intExtra2 = getIntent().getIntExtra("key1", 0);
        if (intExtra == 0 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level1/1-nice-to-meet-you.html");
        }
        if (intExtra == 0 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level1/2-he's-over-there.html");
        }
        if (intExtra == 0 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/inter/level1/3-it's-interesting.html");
        }
        if (intExtra == 0 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/inter/level1/4-oh-no.html");
        }
        if (intExtra == 0 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/inter/level1/5-are-you-from-seoul.html");
        }
        if (intExtra == 0 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/inter/level1/6-he-is-cute.html");
        }
        if (intExtra == 0 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/inter/level1/7-it-is-a-disaster.html");
        }
        if (intExtra == 0 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/inter/level1/8-it's-really-cold.html");
        }
        if (intExtra == 0 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/inter/level1/9-what-time-is-it-there.html");
        }
        if (intExtra == 0 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/inter/level1/10i-am-really-hungry.html");
        }
        if (intExtra == 0 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/inter/level1/11-a-nice-car.html");
        }
        if (intExtra == 0 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/inter/level1/12-get-up-late.html");
        }
        if (intExtra == 0 && intExtra2 == 12) {
            webView.loadUrl("file:///android_asset/inter/level1/13-my-new-apartment.html");
        }
        if (intExtra == 0 && intExtra2 == 13) {
            webView.loadUrl("file:///android_asset/inter/level1/14-there-arent-any-chair.html");
        }
        if (intExtra == 0 && intExtra2 == 14) {
            webView.loadUrl("file:///android_asset/inter/level1/15-he-works-in-a-hotel.html");
        }
        if (intExtra == 0 && intExtra2 == 15) {
            webView.loadUrl("file:///android_asset/inter/level1/16-please-be-careful.html");
        }
        if (intExtra == 0 && intExtra2 == 16) {
            webView.loadUrl("file:///android_asset/inter/level1/17-how-about-some-sandwich.html");
        }
        if (intExtra == 0 && intExtra2 == 17) {
            webView.loadUrl("file:///android_asset/inter/level1/18-fish-for-breakfast.html");
        }
        if (intExtra == 0 && intExtra2 == 18) {
            webView.loadUrl("file:///android_asset/inter/level1/19-i-love-sports.html");
        }
        if (intExtra == 0 && intExtra2 == 19) {
            webView.loadUrl("file:///android_asset/inter/level1/20-i-can't-sing.html");
        }
        if (intExtra == 0 && intExtra2 == 20) {
            webView.loadUrl("file:///android_asset/inter/level1/21-birthday-plan.html");
        }
        if (intExtra == 0 && intExtra2 == 21) {
            webView.loadUrl("file:///android_asset/inter/level1/22-v-day.html");
        }
        if (intExtra == 0 && intExtra2 == 22) {
            webView.loadUrl("file:///android_asset/inter/level1/23-i-don't-feel-well.html");
        }
        if (intExtra == 0 && intExtra2 == 23) {
            webView.loadUrl("file:///android_asset/inter/level1/24-don't-work-too-hard.html");
        }
        if (intExtra == 0 && intExtra2 == 24) {
            webView.loadUrl("file:///android_asset/inter/level1/25-it-emergency.html");
        }
        if (intExtra == 0 && intExtra2 == 25) {
            webView.loadUrl("file:///android_asset/inter/level1/26-is-it-far-from-here.html");
        }
        if (intExtra == 1 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/1-please-call-me-beth.html");
        }
        if (intExtra == 1 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/2-what-seoul-like.html");
        }
        if (intExtra == 1 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/inter/level2/3-how-is-it-going.html");
        }
        if (intExtra == 2 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/4-where-do-you-work.html");
        }
        if (intExtra == 2 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/5-i-start-work-at-five.html");
        }
        if (intExtra == 3 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/6-it-is-really-pretty.html");
        }
        if (intExtra == 3 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/7-i-prefer-the-blue-one.html");
        }
        if (intExtra == 4 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/8-who-is-your-favorite-singer.html");
        }
        if (intExtra == 4 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/9-an-invitation.html");
        }
        if (intExtra == 5 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/10-askin-about-family.html");
        }
        if (intExtra == 5 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/11-is-that-typical.html");
        }
        if (intExtra == 6 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/12-i-hardly-ever-exercise.html");
        }
        if (intExtra == 6 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/13-i-am-a-real-fitness-freak.html");
        }
        if (intExtra == 7 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/12-i-hardly-ever-exercise.html");
        }
        if (intExtra == 7 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/13-i-am-a-real-fitness-freak.html");
        }


    }
    private void displayPatterns(){

        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/pattern/I-have-something_to.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/pattern/I promise not to + v1.html");
        } else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/pattern/I should have + V3.html");
        } else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/pattern/It is too bad that + s + v+o.html");
        } else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/pattern/I don't have time to + V1.html");
        } else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/pattern/I am calling to + V1.html");
        } else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/pattern/I am dying to + V1.html");
        } else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/pattern/I am having hard time + V-ing.html");
        } else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/pattern/I am sorry to + V1.html");
        } else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/pattern/I am thinking of +V-ing.html");
        } else if (intExtra == 10) {
            webView.loadUrl("file:///android_asset/pattern/I am working on + noun.html");
        } else if (intExtra == 11) {
            webView.loadUrl("file:///android_asset/pattern/I'll help you+ V1.html");
        } else if (intExtra == 12) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Be + adj.htm");
        } else if (intExtra == 13) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Giving order with V1.htm");
        } else if (intExtra == 14) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/gonna+gotta+wanna.htm");
        } else if (intExtra == 15) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/I am here to + V1.htm");
        } else if (intExtra == 16) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/I am sure to + V1.htm");
        } else if (intExtra == 17) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/If I were you.htm");
        } else if (intExtra == 18) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/If.htm");
        } else if (intExtra == 19) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/It is time to + V1.htm");
        } else if (intExtra == 20) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Let O + V1.htm");
        } else if (intExtra == 21) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/May I + V1.htm");
        } else if (intExtra == 22) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/No need to + V1.htm");
        } else if (intExtra == 23) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Not because but because.htm");
        } else if (intExtra == 24) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Not only but also.htm");
        } else if (intExtra == 25) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Nothing to + V1.htm");
        } else if (intExtra == 26) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Please + V1..htm");
        } else if (intExtra == 27) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Please don't + V1.htm");
        } else if (intExtra == 28) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + can + V1.htm");
        } else if (intExtra == 29) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/s + can't help + V-ing.htm");
        } else if (intExtra == 30) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + dare + V1.htm");
        } else if (intExtra == 31) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + feel + Adj.htm");
        } else if (intExtra == 32) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + have to + V1.htm");
        } else if (intExtra == 33) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + linking verb + adj.htm");
        } else if (intExtra == 34) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + make O + V1.htm");
        } else if (intExtra == 35) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + need to + V1.htm");
        } else if (intExtra == 36) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/s + seem + adj.htm");
        } else if (intExtra == 37) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + seem + V1.htm");
        } else if (intExtra == 38) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + would like O + to V1.htm");
        } else if (intExtra == 39) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ be able to + V1.htm");
        } else if (intExtra == 40) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ be about to + V1.htm");
        } else if (intExtra == 41) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ be going to + V1.htm");
        } else if (intExtra == 42) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ should had better ought to +v1.htm");
        } else if (intExtra == 43) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ used to + V1.htm");
        } else if (intExtra == 44) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ used to + V-ing.htm");
        } else if (intExtra == 45) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ want O to + V1.htm");
        } else if (intExtra == 46) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+make O + V1.htm");
        } else if (intExtra == 47) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/s+must + V1.htm");
        } else if (intExtra == 48) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+would like to+ V1.htm");
        } else if (intExtra == 49) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/That's why .htm");
        } else if (intExtra == 50) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/There is something wrong with.htm");
        } else if (intExtra == 51) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/want to + V1.htm");
        } else if (intExtra == 52) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/what to + V1.htm");
        } else if (intExtra == 53) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Whether or not.htm");
        } else if (intExtra == 54) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Would you like + Noun.htm");
        } else if (intExtra == 55) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Would you like to + V1.htm");
        }
    }

    private void displayStory(){

        int intExtra = getIntent().getIntExtra("key",0);
        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/easy_stories/ababy_and_asock.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/easy_stories/acat_adog.html");
        } else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/easy_stories/an_apple_pie.html");
        } else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/easy_stories/birds_and_ababy.html");
        } else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/easy_stories/birthday_cake.html");
        } else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/easy_stories/boys_willbe_boys.html");
        } else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/easy_stories/in_the_garden.html");
        } else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/easy_stories/no_food_no_job.html");
        } else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/easy_stories/no_friend_forme.html");
        } else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/easy_stories/tell_the_truth.html");
        } else if (intExtra == 10) {
            webView.loadUrl("file:///android_asset/easy_stories/the_baby_bear.html");
        } else if (intExtra == 11) {
            webView.loadUrl("file:///android_asset/easy_stories/Obama.html");
        } else if (intExtra == 12) {
            webView.loadUrl("file:///android_asset/easy_stories/Abraham Lincoln.html");
        } else if (intExtra == 13) {
            webView.loadUrl("file:///android_asset/easy_stories/Bill_gate.html");
        } else if (intExtra == 14) {
            webView.loadUrl("file:///android_asset/easy_stories/Martin Luther King Jr.html");
        } else if (intExtra == 15) {
            webView.loadUrl("file:///android_asset/easy_stories/George Washington.html");
        }

    }

    private AdSize getAdSize() {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
}

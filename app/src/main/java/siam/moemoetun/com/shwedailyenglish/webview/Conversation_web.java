package siam.moemoetun.com.shwedailyenglish.webview;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Objects;

import siam.moemoetun.com.shwedailyenglish.R;

public class Conversation_web extends AppCompatActivity {

public WebView webView;
private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable navigationIcon = toolbar.getNavigationIcon();
        // Apply color tint to the navigation icon drawable
        if (navigationIcon != null) {
            navigationIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView = new AdView(this);
        mAdView.setAdUnitId(getString(R.string.banner_2021));

        InterstitialAd.load(Conversation_web.this, getString(R.string.inter_goback),
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


        webView = (WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public void onBackPressed() {

        if (mInterstitialAd !=null) {
            mInterstitialAd.show(Conversation_web.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                }
            });
        }
        finish();
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

}

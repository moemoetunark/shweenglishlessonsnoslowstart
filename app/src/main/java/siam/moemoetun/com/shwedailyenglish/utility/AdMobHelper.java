package siam.moemoetun.com.shwedailyenglish.utility;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class AdMobHelper {
    private RewardedAd rewardedAd;
    private InterstitialAd interstitialAd;
    private final Context context;
    private String adUnitId;
    // Default constructor
    public AdMobHelper(Context context) {
        this.context = context;
        MobileAds.initialize(context);
    }
    // Parameterized constructor
    public AdMobHelper(Context context, String adUnitId) {
        this.context = context;
        this.adUnitId = adUnitId;
        MobileAds.initialize(context);
    }

    public void loadRewardedAd() {
        RewardedAd.load(context, adUnitId, new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                rewardedAd = ad;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Ad failed to load.
                // Show the alert dialog when the ad fails to load
            }
        });
    }

    public void showRewardedAd(final OnUserEarnedRewardListener listener) {
        if (rewardedAd != null) {
            rewardedAd.show((Activity) context, listener);
        } else {
            // Show the alert dialog when the rewarded ad is null
            showNoAdsAvailableDialog();
        }
    }
    public void loadBannerAd(AdView adView) {
        adView.loadAd(new AdRequest.Builder().build());
    }

    // Method to show the alert dialog
    // Method to show the alert dialog
    private void showNoAdsAvailableDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Reward Available");
        builder.setMessage("Sorry, there are no Reward available at the moment.");
        // Add a positive button and set a click listener to dismiss the dialog
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss(); // Dismiss the dialog on OK button click
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(true);
    }

    public void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(
                context,
                adUnitId,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Ad failed to load.
                        // Show the alert dialog when the ad fails to load
                    }
                }
        );
    }

    public void showInterstitialAd() {
        if (interstitialAd != null) {
            interstitialAd.show((Activity) context);
        }
    }

}

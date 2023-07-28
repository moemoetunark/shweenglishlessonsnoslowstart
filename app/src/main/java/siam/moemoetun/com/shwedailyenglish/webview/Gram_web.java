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

public class Gram_web extends AppCompatActivity {

    public WebView webView;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable navigationIcon = toolbar.getNavigationIcon();
        // Apply color tint to the navigation icon drawable
        if (navigationIcon != null) {
            navigationIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView = new AdView(this);
        mAdView.setAdUnitId(getString(R.string.banner_2021));
        InterstitialAd.load(Gram_web.this, getString(R.string.inter_goback),
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
        int intExtra = getIntent().getIntExtra("key",0);
        int intExtra2 = getIntent().getIntExtra("key1", 0);
         if (intExtra == 0 &&intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/tense/Nouns.htm");
        }
        else if (intExtra == 0 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/tense/Pronouns.htm");
        }
        else if (intExtra == 0 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/tense/types of adjectives.htm");
        }
        else if (intExtra == 0 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/tense/verb_definition.htm");
        }
        else if (intExtra == 0 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/tense/Adverb.htm");
        }
        else if (intExtra == 0 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/tense/Preposition.htm");
        }
        else if (intExtra == 0 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/tense/conjunction.htm");
        }
        else if (intExtra == 0 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/tense/interjection.htm");
        }
        else if (intExtra == 1 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/tense/verbtobepresent.htm");
        }
        else if (intExtra == 1 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/tense/verbtobepast.htm");
        }
        else if (intExtra == 1 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/tense/verbtobefuture.htm");
        }
        else if (intExtra == 1 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/tense/verbtobeperfect.htm");
        }
        else if (intExtra == 1 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/tense/verbtobeadjective.htm");
        }
        else if (intExtra == 1 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/tense/verbtobepreposition.htm");
        }
        else if (intExtra == 1 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/tense/Therebe.htm");
        }
        else if (intExtra == 1 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/tense/verbtobe_wh_question.html");
        }
        else if (intExtra == 2 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/tense/verb_to_do_simple_present.htm");
        }
        else if (intExtra == 2 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/tense/Present Continuous.htm");
        }
        else if (intExtra == 2 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/tense/Present_perfect_verbtodo.htm");
        }
        else if (intExtra == 2 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/tense/PresentPerfectContinuous.htm");
        }
        else if (intExtra == 2 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/tense/simple_past_tense.htm");
        }
        else if (intExtra == 2 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/tense/Past Continuous.htm");
        }
        else if (intExtra == 2 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/tense/Past Perfect Tense.htm");
        }
        else if (intExtra == 2 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/tense/Past Perfect Continuous.htm");
        }
        else if (intExtra == 2 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/tense/Simple Future Tense.htm");
        }
        else if (intExtra == 2 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/tense/Future Continuous Tense.htm");
        }
        else if (intExtra == 2 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/tense/Future Perfect Tense.htm");
        }
        else if (intExtra == 2 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/tense/Future Perfect Continuous Tense.htm");
        }
        else if (intExtra == 3 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/Passive_voice.html");
        }
        else if (intExtra == 3 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive Voice Present Simple.htm");
        }
        else if (intExtra == 3 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive Voice Present Continuous.htm");
        }
        else if (intExtra == 3 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive Voice Present Perfect  .htm");
        }
        else if (intExtra == 3 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive Voice Simple Past Tense.htm");
        }
        else if (intExtra == 3 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive Voice Past Continuous.htm");
        }
        else if (intExtra == 3 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive Voice Past Perfect.htm");
        }
        else if (intExtra == 3 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive Voce Simple Future.htm");
        }
        else if (intExtra == 3 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/passive_voice/irregular_verb.htm");
        }
        else if (intExtra == 3 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/passive_voice/get_passive_voice.htm");
        }
        else if (intExtra == 3 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/passive_voice/model_verb_passive_voice.htm");
        }
        else if (intExtra == 3 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/passive_voice/passive_voice_with_agent.htm");
        }
        else if (intExtra == 3 && intExtra2 == 12) {
            webView.loadUrl("file:///android_asset/passive_voice/passive_voice_without_agent.htm");
        }
        else if (intExtra == 3 && intExtra2 == 13) {
            webView.loadUrl("file:///android_asset/passive_voice/Passive_with_two_object_verb.htm");
        }
        else if (intExtra == 3 && intExtra2 == 14) {
            webView.loadUrl("file:///android_asset/passive_voice/transitive_verb_n_intransitive_verb.htm");
        }
        else if (intExtra == 4 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/clause/noun_clause.htm");
        }
        else if (intExtra == 4 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/clause/adjective_clause.htm");
        }
        else if (intExtra == 4 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/clause/adverb_clause.htm");
        }
        else if (intExtra == 4 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/clause/v-ing_clause.htm");
        }
        else if (intExtra == 4 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/clause/ed clausse.htm");
        }
        else if (intExtra == 5 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/verb_have/have_has_present-tense_positive.htm");
        }
        else if (intExtra == 5 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/verb_have/have_has_present_sentse_negative.htm");
        }
        else if (intExtra == 5 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/verb_have/have_has_present_tense_question.htm");
        }
        else if (intExtra == 5 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/verb_have/have_has_present_tense_negative_question.htm");
        }
        else if (intExtra == 5 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/verb_have/verb_tohave_past_tense.htm");
        }
        else if (intExtra == 5 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/verb_have/have_has_got.htm");
        }
        else if (intExtra == 6 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/verb_have/making-comparative-and-superlative.htm");
        }
        else if (intExtra == 6 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/verb_have/as-adj-as-comparison-in-english.htm");
        }
        else if (intExtra == 6 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/verb_have/the-sonnger-the-better.htm");
        }
        else if (intExtra == 6 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/verb_have/the-same-as.htm");
        }
        else if (intExtra == 7 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/verb_have/what-is-linking-verb.html");
        }
        else if (intExtra == 7 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/verb_have/go.htm");
        }
        else if (intExtra == 7 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/verb_have/come.htm");
        }
        else if (intExtra == 7 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/verb_have/keep.htm");
        }
        else if (intExtra == 7 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/verb_have/get.htm");
        }
        else if (intExtra == 7 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/verb_have/appear-seem-proved-turn-out.htm");
        }
        else if (intExtra == 7 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/verb_have/five-senses-verbs.htm");
        }
        else if (intExtra == 7 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/verb_have/look-like-feel-like.htm");
        }
        else if (intExtra == 8 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/verb_have/adjective+preposition-1.htm");
        }
        else if (intExtra == 8 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/verb_have/adjective+preposition-2.htm");
        }
        else if ((intExtra == 8) && (intExtra2 == 2)) {
            webView.loadUrl("file:///android_asset/verb_have/Adjective+of-2.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 0)) {
            webView.loadUrl("file:///android_asset/model_verbs/will-positive.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 1)) {
            webView.loadUrl("file:///android_asset/model_verbs/will_negative.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 2)) {
            webView.loadUrl("file:///android_asset/model_verbs/will-question.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 3)) {
            webView.loadUrl("file:///android_asset/model_verbs/can_positive.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 4)) {
            webView.loadUrl("file:///android_asset/model_verbs/can-genative.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 5)) {
            webView.loadUrl("file:///android_asset/model_verbs/can-question.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 6)) {
            webView.loadUrl("file:///android_asset/model_verbs/should-positive.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 7)) {
            webView.loadUrl("file:///android_asset/model_verbs/should-negative.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 8)) {
            webView.loadUrl("file:///android_asset/model_verbs/should-question.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 9)) {
            webView.loadUrl("file:///android_asset/model_verbs/may-positive.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 10)) {
            webView.loadUrl("file:///android_asset/model_verbs/may-question.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 11)) {
            webView.loadUrl("file:///android_asset/model_verbs/may-negative.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 12)) {
            webView.loadUrl("file:///android_asset/model_verbs/must.html");
        }
        else if ((intExtra == 9) && (intExtra2 == 13)) {
            webView.loadUrl("file:///android_asset/model_verbs/could.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 14)) {
            webView.loadUrl("file:///android_asset/model_verbs/shall.htm");
        }
        else if ((intExtra == 9) && (intExtra2 == 15)) {
            webView.loadUrl("file:///android_asset/model_verbs/modal_verb_adjective.html");
        }
        else if ((intExtra == 9) && (intExtra2 == 16)) {
            webView.loadUrl("file:///android_asset/model_verbs/modal_verb_preposition.html");
        }
        else if ((intExtra == 9) && (intExtra2 == 17)) {
            webView.loadUrl("file:///android_asset/model_verbs/model_verb_passive_voice.html");
        }
        else if ((intExtra == 9) && (intExtra2 == 18)) {
            webView.loadUrl("file:///android_asset/model_verbs/model_verb_wh_question.html");
        }else if ((intExtra == 10) && (intExtra2 == 0)) {
             webView.loadUrl("file:///android_asset/ving/how to use v-ing.html");
         }else if ((intExtra == 10) && (intExtra2 == 1)) {
             webView.loadUrl("file:///android_asset/ving/Verb-ing.html");
         }else if ((intExtra == 10) && (intExtra2 == 2)) {
             webView.loadUrl("file:///android_asset/ving/prefer and would rather.html");
         }else if ((intExtra == 10) && (intExtra2 ==3)) {
             webView.loadUrl("file:///android_asset/ving/Preposition + V-ing_new.html");
         }else if ((intExtra == 10) && (intExtra2 ==4)) {
             webView.loadUrl("file:///android_asset/ving/Verb + ing or to-1.html");
         }else if ((intExtra == 10) && (intExtra2 ==5)) {
             webView.loadUrl("file:///android_asset/ving/verb -ing or to-2.html");
         }else if ((intExtra == 10) && (intExtra2 ==6)) {
             webView.loadUrl("file:///android_asset/ving/Verb + ing or to 3.html");
         }else if ((intExtra == 10) && (intExtra2 ==7)) {
             webView.loadUrl("file:///android_asset/ving/Verb + to.html");
         }else if ((intExtra == 10) && (intExtra2 ==8)) {
             webView.loadUrl("file:///android_asset/ving/Verb object + to.html");
         }else if ((intExtra == 10) && (intExtra2 ==9)) {
             webView.loadUrl("file:///android_asset/ving/Verb +object + prep + -ving.html");
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
            mInterstitialAd.show(Gram_web.this);
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




    /* access modelse ifiers changed from: protected */
    public void onDestroy() {
        try {
            this.webView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}

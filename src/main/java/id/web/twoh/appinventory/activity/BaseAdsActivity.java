package id.web.twoh.appinventory.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import id.web.twoh.appinventory.MainApplication;
import id.web.twoh.appinventory.R;

/**
 * Created by Hafizh Herdi on 2/1/2017.
 */

public class BaseAdsActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;
    private static final String TAG = BaseAdsActivity.class.getSimpleName();
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6916955256570875~6667430151");

        initInterstitial();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);


    }

    protected void loadAdsRequest(){
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("22FFB74E3E00DEC909938864EE0B401E")
                .build();
        mAdView.loadAd(adRequest);
    }

    protected void initInterstitial(){
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ads_unit_id_interstitial));

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadInterstitial();
            }
        });

        loadInterstitial();
    }

    protected void decideToDisplay(){
        counter++;
        Log.v(TAG, "ads matdes counter display "+counter);
        if(counter==3)
        {
            Log.v(TAG, "ads matdes counter display displayed "+counter);
            displayInterstitial();
            counter = 0;
        }
    }

    protected void displayInterstitial() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            loadInterstitial();
        }
    }

    private void loadInterstitial() {

        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }
    }

    protected void readTheTutorial(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.putExtra(Intent.EXTRA_REFERRER,
                Uri.parse(Intent.URI_ANDROID_APP_SCHEME + "//" + this.getPackageName()));
        customTabsIntent.launchUrl(this, Uri.parse(url));
        Tracker tracker = ((MainApplication)getApplication()).getDefaultTracker();
        Log.i(TAG, "View screen name:  read tutor");
        tracker.setScreenName("Screen "+url );
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tracker tracker = ((MainApplication)getApplication()).getDefaultTracker();
        Log.i(TAG, "View screen name: " + this.getClass().getSimpleName());
        tracker.setScreenName("Screen " + this.getClass().getSimpleName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}

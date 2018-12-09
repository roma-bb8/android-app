package io.github.roma_bb8.myapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdActivity extends AppCompatActivity {

    private static final String TOAST_TEXT = "Test ads are being shown. To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    private static final int START_LEVEL = 1;

    private InterstitialAd interstitialAd;

    private TextView textView;
    private Button button;

    private int level = START_LEVEL;

    //########################################

    private InterstitialAd getInterstitialAdInstance() {

        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                button.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                button.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                nextInterstitialAdLevel();
            }
        });

        return interstitialAd;
    }

    private void loadInterstitialAd() {

        button.setEnabled(false);
        interstitialAd.loadAd(new AdRequest
                .Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        );
    }

    private void showInterstitialAd() {

        if ((null != interstitialAd) && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load.", Toast.LENGTH_SHORT).show();
            nextInterstitialAdLevel();
        }
    }

    private void nextInterstitialAdLevel() {

        String levelText = "Level " + (++this.level);

        textView.setText(levelText);

        interstitialAd = getInterstitialAdInstance();

        loadInterstitialAd();
    }

    //########################################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        textView = findViewById(R.id.activity_ad_level);

        button = findViewById(R.id.activity_ad_button);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitialAd();
            }
        });

        interstitialAd = getInterstitialAdInstance();

        loadInterstitialAd();

        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    //########################################

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ad_init, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.action_settings == item.getItemId()) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
